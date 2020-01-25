package hu.elte.strucker.view.menus;

import hu.elte.strucker.application.Application;
import hu.elte.strucker.model.diagram.Diagram;
import hu.elte.strucker.model.project.Library;
import hu.elte.strucker.model.project.Project;
import hu.elte.strucker.service.ResourceManager;

import javax.swing.*;

public class AnalyzeMenu extends ApplicationMenu {

    private JMenuItem checkDiagramMenuItem;
    private JMenuItem executeMenuItem;

    public AnalyzeMenu(Application app) {
        super(app, "Elemzés");

        checkDiagramMenuItem = new JMenuItem("Diagram ellenőrzése", ResourceManager.getIcon("images/menu/check.png"));
        executeMenuItem = new JMenuItem("Diagram végrehajtása", ResourceManager.getIcon("images/menu/execute.png"));

        add(checkDiagramMenuItem);
        add(executeMenuItem);
    }

    public void fireProjectSelected(Project project) {
        fireNothingSelected();
    }

    @Override
    public void fireDiagramSelected(Diagram diagram) {
        fireNothingSelected();
        setup(checkDiagramMenuItem, e -> app.getDiagramController().check(diagram));
        setup(executeMenuItem, e -> app.getDiagramController().execute(diagram));
    }

    @Override
    public void fireLibrarySelected(Library library) {
        fireNothingSelected();
    }

    @Override
    public void fireNothingSelected() {
        disable(checkDiagramMenuItem);
        disable(executeMenuItem);
    }
}
