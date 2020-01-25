package hu.elte.strucker.view.menus;

import hu.elte.strucker.controller.ExplorerOperations;
import hu.elte.strucker.service.ResourceManager;

import javax.swing.*;

public class ExplorerMenu extends JPopupMenu {

    private JMenuItem closeAll;
    private JMenuItem open;
    private JMenuItem saveAll;
    private JMenuItem expand;
    private JMenuItem collapse;

    public ExplorerMenu(ExplorerOperations controller) {
        closeAll = new JMenuItem("Összes projekt bezárása", ResourceManager.getIcon("images/menu/close.png"));
        closeAll.addActionListener(e -> controller.closeAll());
        open = new JMenuItem("Projekt megnyitása", ResourceManager.getIcon("images/menu/open.png"));
        open.addActionListener(e -> controller.open());
        saveAll = new JMenuItem("Összes projekt mentése", ResourceManager.getIcon("images/menu/saveall.png"));
        saveAll.addActionListener(e -> controller.saveAll());
        expand = new JMenuItem("Kinyitás", ResourceManager.getIcon("images/explorer/expand.png"));
        expand.addActionListener(e -> controller.expand());
        collapse = new JMenuItem("Becsukás", ResourceManager.getIcon("images/explorer/collapse.png"));
        collapse.addActionListener(e -> controller.collapse());
        add(open);
        addSeparator();
        add(saveAll);
        add(closeAll);
        addSeparator();
        add(expand);
        add(collapse);
    }
}
