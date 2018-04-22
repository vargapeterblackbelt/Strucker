package hu.elte.strucker.controller.controllers;

import hu.elte.strucker.controller.Application;
import hu.elte.strucker.controller.operations.DiagramOperations;
import hu.elte.strucker.controller.operations.ViewerOperations;
import hu.elte.strucker.controller.operations.ProjectOperations;
import hu.elte.strucker.controller.operations.StructogramOperations;
import hu.elte.strucker.model.diagram.Diagram;
import hu.elte.strucker.model.diagram.Sequence;
import hu.elte.strucker.model.diagram.Structogram;
import hu.elte.strucker.model.project.Project;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import static hu.elte.strucker.helper.TreeHelper.canExtract;

public class ViewerController extends Controller implements ViewerOperations {

    ProjectOperations projectController;
    DiagramOperations diagramController;
    StructogramOperations structogramController;

    public ViewerController(Application app) {
        super(app);
        projectController = new ProjectController(app);
        diagramController = new DiagramController(app);
        structogramController = new StructogramController(app);
    }

    private JPopupMenu menuForProject() {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem newDiagram = new JMenuItem("Új diagram");
        newDiagram.addActionListener(e -> diagramController.create());
        menu.add(newDiagram);
        menu.addSeparator();
        JMenuItem properties = new JMenuItem("Tulajdonságok");
        properties.addActionListener(e -> projectController.showProperties());
        menu.add(properties);
        JMenuItem importDiagram = new JMenuItem("Importálás");
        importDiagram.addActionListener(e -> projectController.importDiagram());
        menu.add(importDiagram);
        JMenuItem save = new JMenuItem("Mentés");
        save.addActionListener(e -> projectController.save());
        menu.add(save);
        JMenuItem saveAs = new JMenuItem("Mentés másként");
        saveAs.addActionListener(e -> projectController.saveAs());
        menu.add(saveAs);
        JMenuItem validate = new JMenuItem("Ellenőrzés");
        validate.addActionListener(e -> projectController.validate());
        menu.add(validate);
        JMenuItem close = new JMenuItem("Bezárás");
        close.addActionListener(e -> projectController.close());
        menu.addSeparator();
        menu.add(close);
        return menu;
    }

    private JPopupMenu menuForDiagram() {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem insert = new JMenuItem("Új elem");
        insert.addActionListener(e -> diagramController.insert());
        menu.add(insert);
        JMenuItem properties = new JMenuItem("Tulajdonságok");
        properties.addActionListener(e -> diagramController.showProperties());
        menu.add(properties);
        menu.addSeparator();
        JMenuItem save = new JMenuItem("Mentés");
        save.addActionListener(e -> diagramController.save());
        menu.add(save);
        JMenuItem export = new JMenuItem("Exportálás");
        export.addActionListener(e -> diagramController.export());
        menu.add(export);
        JMenuItem exportAsImage = new JMenuItem("Exportálás képként");
        exportAsImage.addActionListener(e -> diagramController.exportAsImage());
        menu.add(exportAsImage);
        menu.addSeparator();
        JMenuItem copy = new JMenuItem("Másolás");
        JMenuItem cut = new JMenuItem("Kivágás");
        JMenuItem paste = new JMenuItem("Beillesztés");
        JMenuItem delete = new JMenuItem("Törlés");
        copy.addActionListener(e -> diagramController.copy());
        cut.addActionListener(e -> diagramController.cut());
        paste.addActionListener(e -> diagramController.paste());
        delete.addActionListener(e -> diagramController.delete());
        menu.add(copy);
        menu.add(cut);
        menu.add(paste);
        menu.add(delete);
        menu.addSeparator();
        JMenuItem validate = new JMenuItem("Ellenőrzés");
        validate.addActionListener(e -> diagramController.validate());
        menu.add(validate);
        return menu;
    }

    private JPopupMenu menuForStg() {
        JPopupMenu menu = new JPopupMenu();

        if(!canExtract(app.getSelectedNode(), Sequence.class)) {

            JMenuItem insertBefore = new JMenuItem("Beszúrás elé");
            insertBefore.addActionListener(e -> structogramController.insertBefore());
            menu.add(insertBefore);
            JMenuItem insertAfter = new JMenuItem("Beszúrás mögé");
            insertAfter.addActionListener(e -> structogramController.insertAfter());
            menu.add(insertAfter);
        } else {

            JMenuItem insert = new JMenuItem("Új elem beszúrása");
            insert.addActionListener(e -> structogramController.insert());
            menu.add(insert);
        }
        JMenuItem properties = new JMenuItem("Tulajdonságok");
        properties.addActionListener(e -> structogramController.showProperties());
        menu.add(properties);
        menu.addSeparator();

        JMenuItem copy = new JMenuItem("Másolás");
        JMenuItem cut = new JMenuItem("Kivágás");
        JMenuItem paste = new JMenuItem("Beillesztés");
        JMenuItem delete = new JMenuItem("Törlés");
        copy.addActionListener(e -> structogramController.copy());
        cut.addActionListener(e -> structogramController.cut());
        paste.addActionListener(e -> structogramController.paste());
        delete.addActionListener(e -> structogramController.delete());
        menu.add(copy);
        menu.add(cut);
        menu.add(paste);
        menu.add(delete);
        menu.addSeparator();
        JMenuItem moveUp = new JMenuItem("Fel");
        JMenuItem moveDown = new JMenuItem("Le");
        moveUp.addActionListener(e -> structogramController.moveUp());
        moveDown.addActionListener(e -> structogramController.moveDown());
        menu.add(moveUp);
        menu.add(moveDown);
        return menu;
    }

    @Override
    public void expand() {
//        TreePath rootPath = new TreePath(((DefaultMutableTreeNode)app.getExplorer().getModel().getRoot()).getPath());
//        DefaultMutableTreeNode selectedNode = app.getSelectedNode();
//        app.getExplorer().expandPath(rootPath);
    }

    @Override
    public void collapse() {
//        app.getExplorer().collapsePath((TreePath) app.getExplorer().getModel().getRoot());
    }

    @Override
    public void showMenu() {
        DefaultMutableTreeNode node = app.getSelectedNode();
        if (node != null) {
            JPopupMenu menu = null;
            if (canExtract(node, Project.class)) {
                menu = menuForProject();
            } else {
                if (canExtract(node, Diagram.class)) {
                    menu = menuForDiagram();
                } else {
                    if (canExtract(node, Structogram.class)) {
                        menu = menuForStg();
                    }
                }
            }
            if (menu != null) {
                menu.show(app.getWindow(), app.getWindow().getMousePosition().x, app.getWindow().getMousePosition().y);
            }
        }
    }

    @Override
    public void save() {
        DefaultMutableTreeNode selectedNode = app.getSelectedNode();
        if (selectedNode != null) {
            Object userObject = app.getSelectedNode().getUserObject();
            if (userObject instanceof Project) {
                projectController.save();
            } else {
                if (userObject instanceof Diagram) {
                    diagramController.save();
                }
            }
        }
    }

    @Override
    public void insert() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create() {
        throw new UnsupportedOperationException();
    }
}
