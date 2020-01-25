package hu.elte.strucker.view.menus;

import hu.elte.strucker.controller.DiagramOperations;
import hu.elte.strucker.model.diagram.Diagram;
import hu.elte.strucker.service.ResourceManager;

import javax.swing.*;

public class DiagramMenu extends JPopupMenu {

    private JMenuItem export;
    private JMenuItem exportAsImage;
    private JMenuItem delete;
    private JMenuItem properties;
    private JMenuItem check;
    private JMenuItem execute;
    private JMenuItem open;

    public DiagramMenu(Diagram diagram, DiagramOperations controller) {
        super();
        init();
        construct();

        export.addActionListener(e -> controller.export(diagram));
        exportAsImage.addActionListener(e -> controller.capture(diagram));
        delete.addActionListener(e -> controller.delete(diagram));
        properties.addActionListener(e -> controller.showProperties(diagram));
        check.addActionListener(e -> controller.check(diagram));
        execute.addActionListener(e -> controller.execute(diagram));
        open.addActionListener(e -> controller.open(diagram));
    }

    private void init() {
        export = new JMenuItem("Exportálás", ResourceManager.getIcon("images/menu/export.png"));
        exportAsImage = new JMenuItem("Exportálás képként", ResourceManager.getIcon("images/menu/capture.png"));
        delete = new JMenuItem("Törlés", ResourceManager.getIcon("images/menu/delete.png"));
        properties = new JMenuItem("Tulajdonságok", ResourceManager.getIcon("images/menu/properties.png"));
        check = new JMenuItem("Ellenőrzés", ResourceManager.getIcon("images/menu/check.png"));
        execute = new JMenuItem("Futtatás", ResourceManager.getIcon("images/menu/execute.png"));
        open = new JMenuItem("Megnyitás", ResourceManager.getIcon("images/menu/open.png"));
    }

    private void construct() {
        add(open);
        addSeparator();
        add(properties);
        addSeparator();
        add(export);
        add(exportAsImage);
        addSeparator();
        add(delete);
        addSeparator();
        add(check);
        add(execute);
    }

}
