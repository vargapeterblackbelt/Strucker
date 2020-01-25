package hu.elte.strucker.view.menus;

import hu.elte.strucker.application.Application;
import hu.elte.strucker.model.diagram.Diagram;
import hu.elte.strucker.model.project.Library;
import hu.elte.strucker.model.project.Project;
import hu.elte.strucker.service.ResourceManager;

import javax.swing.*;

public class ViewMenu extends ApplicationMenu {

    private JMenuItem zoomIn;
    private JMenuItem zoomOut;
    private JMenuItem closeTabs;

    public ViewMenu(Application app) {
        super(app, "Nézet");

        zoomIn = new JMenuItem("Nagyítás", ResourceManager.getIcon("images/menu/zoomin.png"));
        zoomOut = new JMenuItem("Kicsinyítés", ResourceManager.getIcon("images/menu/zoomout.png"));
        closeTabs = new JMenuItem("Tabok bezárása", ResourceManager.getIcon("images/menu/closetabs.png"));

        add(zoomIn);
        add(zoomOut);
        addSeparator();
        add(closeTabs);
    }

    @Override
    public void fireProjectSelected(Project project) {
        disable(zoomIn);
        disable(zoomOut);
    }

    @Override
    public void fireDiagramSelected(Diagram diagram) {
        setup(zoomIn, e -> app.fireZoomIn(diagram));
        setup(zoomOut, e -> app.fireZoomOut(diagram));
    }

    @Override
    public void fireLibrarySelected(Library library) {
        disable(zoomIn);
        disable(zoomOut);
    }

    @Override
    public void fireNothingSelected() {
        disable(zoomIn);
        disable(zoomOut);
        setup(closeTabs, e -> app.fireCloseAllTabs());
    }
}
