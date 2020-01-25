package hu.elte.strucker.view.menus;

import hu.elte.strucker.application.Application;
import hu.elte.strucker.model.diagram.Diagram;
import hu.elte.strucker.model.project.Library;
import hu.elte.strucker.model.project.Project;
import hu.elte.strucker.service.ResourceManager;

import javax.swing.*;

public class EditMenu extends ApplicationMenu {

    private final JMenuItem settings;
    private final JMenuItem delete;

    public EditMenu(Application app) {
        super(app, "Szerkesztés");
        delete = new JMenuItem("Törlés", ResourceManager.getIcon("images/menu/delete.png"));
        settings = new JMenuItem("Tulajdonságok", ResourceManager.getIcon("images/menu/settings.png"));

        add(delete);
        addSeparator();
        add(settings);
    }

    @Override
    public void fireProjectSelected(Project project) {
        fireNothingSelected();
        setup(settings, e -> app.getProjectController().showProperties(project));
    }

    @Override
    public void fireDiagramSelected(Diagram diagram) {
        fireNothingSelected();
        setup(delete, e -> app.getDiagramController().delete(diagram));
        setup(settings, e -> app.getDiagramController().showProperties(diagram));

    }

    @Override
    public void fireLibrarySelected(Library library) {
        fireNothingSelected();
        setup(delete, e -> app.getLibraryController().delete(library));
        setup(settings, e -> app.getLibraryController().rename(library));
    }

    @Override
    public void fireNothingSelected() {
        disable(delete);
        disable(settings);
    }
}
