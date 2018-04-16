package hu.elte.strucker.controller.controllers;

import hu.elte.strucker.controller.Application;
import hu.elte.strucker.controller.operations.DiagramOperations;
import hu.elte.strucker.controller.operations.ExplorerOperations;
import hu.elte.strucker.controller.operations.ProjectOperations;
import hu.elte.strucker.controller.operations.StructogramOperations;
import hu.elte.strucker.model.diagram.Diagram;
import hu.elte.strucker.model.project.Project;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class ExplorerController extends Controller implements ExplorerOperations {

    ProjectOperations projectController;
    DiagramOperations diagramController;
    StructogramOperations structogramController;

    public ExplorerController(Application app) {
        super(app);
        projectController = new ProjectController(app);
        diagramController = new DiagramController(app);
        structogramController = new StructogramController(app);
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
        System.out.println("works!");
    }

    @Override
    public void save() {
        DefaultMutableTreeNode selectedNode = app.getSelectedNode();
        if(selectedNode != null) {
            Object userObject = app.getSelectedNode().getUserObject();
            if(userObject instanceof Project) {
                projectController.save();
            } else {
                if(userObject instanceof Diagram) {
                    diagramController.save();
                }
            }
        }
    }

    @Override
    public void insert() {

    }

    @Override
    public void create() {

    }
}
