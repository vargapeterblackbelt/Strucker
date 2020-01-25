package hu.elte.strucker.application;

import hu.elte.strucker.model.diagram.Diagram;
import hu.elte.strucker.model.project.Library;
import hu.elte.strucker.model.project.Project;
import hu.elte.strucker.view.tools.StruckerToolBarButton;

import javax.swing.*;

import java.awt.*;

import static hu.elte.strucker.view.tools.ToolBarIcon.makeIcon;

public class StruckerToolBar extends JToolBar implements ApplicationSelection {

    private Application app;

    private final StruckerToolBarButton newProject;
    private final StruckerToolBarButton newDiagram;
    private final StruckerToolBarButton importDiagram;
    private final StruckerToolBarButton newLibrary;
    private final StruckerToolBarButton openProject;
    private final StruckerToolBarButton saveProject;
    private final StruckerToolBarButton saveAllProject;
    private final StruckerToolBarButton exportDiagram;
    private final StruckerToolBarButton captureDiagram;
    private final StruckerToolBarButton properties;
    private final StruckerToolBarButton remove;
    private final StruckerToolBarButton check;
    private final StruckerToolBarButton execute;

    public StruckerToolBar(Application app) {
        super(JToolBar.HORIZONTAL);
        this.app = app;
        newProject = new StruckerToolBarButton(makeIcon("newProject"), "Új projekt", "Új projekt létrehozása");
        newDiagram = new StruckerToolBarButton(makeIcon("newDiagram"), "Új diagram", "Új diagram létrehozása a kijelölt könyvtárban");
        importDiagram = new StruckerToolBarButton(makeIcon("importDiagram"), "Importálás", "Diagram importálása fájlból a kijelölt könyvtárba");
        newLibrary = new StruckerToolBarButton(makeIcon("newLibrary"), "Új könyvtár", "Könyvtár létrehozása a kijelölt projektben");
        openProject = new StruckerToolBarButton(makeIcon("openProject"), "Megnyitás", "Projekt megnyitása fájlból");
        saveProject = new StruckerToolBarButton(makeIcon("saveProject"), "Mentés", "A kijelölt projekt mentése");
        saveAllProject = new StruckerToolBarButton(makeIcon("saveAllProject"), "Mind mentése", "Minden megnyitott projekt mentése");
        exportDiagram = new StruckerToolBarButton(makeIcon("export"), "Exportálás", "Diagram exportálása fájlként");
        captureDiagram = new StruckerToolBarButton(makeIcon("capture"), "Kép készítése", "Diagram exportálása képként");
        properties = new StruckerToolBarButton(makeIcon("properties"), "Tulajdonságok", "Tulajdonságok megtekintése");
        remove = new StruckerToolBarButton(makeIcon("remove"), "Eltávolítás", "Eltávolítás");
        check = new StruckerToolBarButton(makeIcon("check"), "Elemzés", "Kiválasztott elemek nyelvi ellenőrzése");
        execute = new StruckerToolBarButton(makeIcon("execute"), "Végrehajtás", "Kiválasztott diagram végrehajtása");

        Dimension gap = new Dimension(2, 0);
        add(newProject);
        addSeparator(gap);
        add(newLibrary);
        addSeparator(gap);
        add(newDiagram);
        addSeparator(gap);
        add(importDiagram);
        addSeparator();
        add(openProject);
        addSeparator(gap);
        add(saveProject);
        addSeparator(gap);
        add(saveAllProject);
        addSeparator();
        add(properties);
        addSeparator(gap);
        add(remove);
        addSeparator();
        add(exportDiagram);
        addSeparator(gap);
        add(captureDiagram);
        addSeparator();
        add(check);
        addSeparator(gap);
        add(execute);
    }

    @Override
    public void fireProjectSelected(Project project) {
        newDiagram.disableButton();
        importDiagram.disableButton();
        newLibrary.setup(e -> app.getProjectController().createLibrary(project));
        saveProject.setup(e -> app.getProjectController().save(project));
        exportDiagram.disableButton();
        captureDiagram.disableButton();
        properties.setup(e -> app.getProjectController().showProperties(project));
        remove.setup(e -> app.getProjectController().close(project));
        check.disableButton();
        execute.disableButton();
    }

    @Override
    public void fireDiagramSelected(Diagram diagram) {
        newDiagram.disableButton();
        importDiagram.disableButton();
        newLibrary.disableButton();
        saveProject.setup(e -> app.getProjectController().save(diagram.getLibrary().getProject()));
        exportDiagram.setup(e -> app.getDiagramController().export(diagram));
        captureDiagram.setup(e -> app.getDiagramController().capture(diagram));
        properties.setup(e -> app.getDiagramController().showProperties(diagram));
        remove.setup(e -> app.getDiagramController().delete(diagram));
        check.setup(e -> app.getDiagramController().check(diagram));
        execute.setup(e -> app.getDiagramController().execute(diagram));
    }

    @Override
    public void fireLibrarySelected(Library library) {
        newDiagram.setup(e -> app.getLibraryController().createDiagram(library));
        importDiagram.setup(e -> app.getLibraryController().importDiagram(library));
        newLibrary.disableButton();
        saveProject.setup(e -> app.getProjectController().save(library.getProject()));
        exportDiagram.disableButton();
        captureDiagram.disableButton();
        properties.setup(e -> app.getLibraryController().rename(library));
        remove.setup(e -> app.getLibraryController().remove(library));
        check.disableButton();
        execute.disableButton();
    }

    @Override
    public void fireNothingSelected() {
        newProject.setup(e -> app.getProjectController().create());
        newDiagram.disableButton();
        importDiagram.disableButton();
        newLibrary.disableButton();
        openProject.setup(e -> app.getProjectController().open());
        saveProject.disableButton();
        saveAllProject.setup(e -> app.fireAllProjectSaving());
        exportDiagram.disableButton();
        captureDiagram.disableButton();
        properties.disableButton();
        remove.disableButton();
        check.disableButton();
        execute.disableButton();
    }
}
