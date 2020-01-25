package hu.elte.strucker.view.menus;

import hu.elte.strucker.application.Application;
import hu.elte.strucker.controller.LibraryOperations;
import hu.elte.strucker.model.project.Library;
import hu.elte.strucker.service.ResourceManager;

import javax.swing.*;

public class LibraryMenu extends JPopupMenu {

    public LibraryMenu(Library library, Application app) {
        super();
        LibraryOperations controller = app.getLibraryController();

        JMenuItem newDiagram = new JMenuItem("Diagram létrehozása", ResourceManager.getIcon("images/menu/newDiagram.png"));
        newDiagram.addActionListener(e -> controller.createDiagram(library));

        JMenuItem importDiagram = new JMenuItem("Diagram importálása", ResourceManager.getIcon("images/menu/import.png"));
        importDiagram.addActionListener(e -> controller.importDiagram(library));

        JMenuItem rename = new JMenuItem("Átnevezés", ResourceManager.getIcon("images/menu/settings.png"));
        rename.addActionListener(e -> controller.rename(library));
        if(library.isPermanent()) {
            rename.setEnabled(false);
        }

        JMenuItem delete = new JMenuItem("Könyvtár törlése", ResourceManager.getIcon("images/menu/delete.png"));
        delete.addActionListener(e -> controller.delete(library));
        if(library.isPermanent()) {
            delete.setEnabled(false);
        }


        add(newDiagram);
        add(importDiagram);
        addSeparator();
        add(rename);
        add(delete);
        addSeparator();
    }
}
