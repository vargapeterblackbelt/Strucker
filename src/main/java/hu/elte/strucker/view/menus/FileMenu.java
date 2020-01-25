package hu.elte.strucker.view.menus;

import hu.elte.strucker.application.Application;
import hu.elte.strucker.model.diagram.Diagram;
import hu.elte.strucker.model.project.Library;
import hu.elte.strucker.model.project.Project;
import hu.elte.strucker.service.ResourceManager;

import javax.swing.*;

public class FileMenu extends ApplicationMenu {

    private final JMenuItem newProject;
    private final JMenuItem open;
    private final JMenuItem close;
    private final JMenuItem importDiagram;
    private final JMenuItem save;
    private final JMenuItem export;
    private final JMenuItem capture;
    private final JMenuItem exit;
    private final JMenuItem newDiagram;
    private final JMenuItem newLibrary;
    private final JMenuItem deleteProject;

    public FileMenu(Application app) {
        super(app, "Fájl");

        newProject = new JMenuItem("Új projekt", ResourceManager.getIcon("images/menu/newProject.png"));
        newDiagram = new JMenuItem("Új diagram", ResourceManager.getIcon("images/menu/newDiagram.png"));
        newLibrary = new JMenuItem("Új könyvtár", ResourceManager.getIcon("images/menu/newLibrary.png"));
        open = new JMenuItem("Projekt megnyitása", ResourceManager.getIcon("images/menu/open.png"));
        close = new JMenuItem("Projekt bezárása", ResourceManager.getIcon("images/menu/close.png"));
        deleteProject = new JMenuItem("Projekt törlése lemezről", ResourceManager.getIcon("images/menu/deleteFromDisc.png"));
        importDiagram = new JMenuItem("Diagram importálása", ResourceManager.getIcon("images/menu/import.png"));
        export = new JMenuItem("Diagram exportálása", ResourceManager.getIcon("images/menu/export.png"));
        capture = new JMenuItem("Diagram exportálása képként", ResourceManager.getIcon("images/menu/capture.png"));
        exit = new JMenuItem("Kilépés", ResourceManager.getIcon("images/menu/exit.png"));
        save = new JMenuItem("Mentés", ResourceManager.getIcon("images/menu/save.png"));

        add(newProject);
        add(newDiagram);
        add(newLibrary);
        addSeparator();
        add(save);
        add(open);
        add(close);
        add(deleteProject);
        addSeparator();
        add(importDiagram);
        add(export);
        add(capture);
        addSeparator();
        add(exit);
    }

    @Override
    public void fireProjectSelected(Project project) {
        fireNothingSelected();

        setup(close, e -> app.getProjectController().close(project));
        setup(deleteProject, e -> app.getProjectController().delete(project));
        setup(save, e -> app.getProjectController().save(project));
    }

    @Override
    public void fireDiagramSelected(Diagram diagram) {
        fireNothingSelected();

        setup(export, e -> app.getDiagramController().export(diagram));
        setup(capture, e -> app.getDiagramController().capture(diagram));
    }

    @Override
    public void fireLibrarySelected(Library library) {
        fireNothingSelected();

        setup(newDiagram, e -> app.getLibraryController().createDiagram(library));
        setup(importDiagram, e -> app.getLibraryController().importDiagram(library));
    }

    @Override
    public void fireNothingSelected() {
        setup(newProject, e -> app.getProjectController().create());
        disable(newDiagram);
        disable(newLibrary);
        setup(open, e -> app.getProjectController().open());
        disable(close);
        disable(save);
        disable(deleteProject);
        disable(importDiagram);
        disable(export);
        disable(capture);
        setup(exit, e -> app.exit());
    }
}
