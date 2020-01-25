package hu.elte.strucker.view.menus;

import hu.elte.strucker.controller.ProjectOperations;
import hu.elte.strucker.model.project.Project;
import hu.elte.strucker.service.ResourceManager;

import javax.swing.*;

public class ProjectMenu extends JPopupMenu {

    public ProjectMenu(Project project, ProjectOperations projectController) {
        super();

        JMenuItem properties = new JMenuItem("Tulajdonságok", ResourceManager.getIcon("images/menu/settings.png"));
        properties.addActionListener(e -> projectController.showProperties(project));
        add(properties);

        addSeparator();

        JMenuItem save = new JMenuItem("Mentés", ResourceManager.getIcon("images/menu/save.png"));
        save.addActionListener(e -> projectController.save(project));
        add(save);

        JMenuItem close = new JMenuItem("Bezárás", ResourceManager.getIcon("images/menu/close.png"));
        close.addActionListener(e -> projectController.close(project));
        add(close);

        addSeparator();

        JMenuItem addLibrary = new JMenuItem("Könyvtár létrehozása", ResourceManager.getIcon("images/menu/newLibrary.png"));
        addLibrary.addActionListener(e -> projectController.createLibrary(project));
    }
}
