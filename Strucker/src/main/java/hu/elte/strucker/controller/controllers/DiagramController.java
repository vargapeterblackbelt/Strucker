package hu.elte.strucker.controller.controllers;

import hu.elte.strucker.controller.Application;
import hu.elte.strucker.controller.operations.DiagramOperations;
import hu.elte.strucker.helper.Naming;
import hu.elte.strucker.model.diagram.Diagram;
import hu.elte.strucker.model.project.Project;
import hu.elte.strucker.view.dialogs.DiagramPropertiesDialog;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import static hu.elte.strucker.helper.Naming.qualifiedPath;
import static hu.elte.strucker.helper.ObjectLoader.serialize;

public class DiagramController extends Controller implements DiagramOperations {

    public DiagramController(Application app) {
        super(app);
    }

    @Override
    public void create() {
        DefaultMutableTreeNode selectedNode = app.getSelectedNode();
        if(selectedNode != null) {
            Object userObject = app.getSelectedNode().getUserObject();
            if(userObject instanceof Project) {
                Project project = (Project) userObject;
                Diagram newDiagram = new Diagram();
                DiagramPropertiesDialog dialog = new DiagramPropertiesDialog(newDiagram);
                if(!dialog.isCancelled()) {
                    project.addDiagram(newDiagram);
                    app.addToExplorer(selectedNode, (DefaultMutableTreeNode) newDiagram.getTree());
                }
            }
        }
    }

    @Override
    public void save() {
        DefaultMutableTreeNode selectedNode = app.getSelectedNode();
        if(selectedNode != null) {
            Object userObject = app.getSelectedNode().getUserObject();
            if(userObject instanceof Diagram) {
                Diagram diagram = (Diagram) userObject;
                Project project = (Project) diagram.getParent();
                serialize(qualifiedPath(project), project);
            }
        }
    }

    @Override
    public void export() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void exportAsImage() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete() {
        System.out.println("works!");
    }

    @Override
    public void showProperties() {
        DefaultMutableTreeNode selectedNode = app.getSelectedNode();
        if(selectedNode != null) {
            Object userObject = app.getSelectedNode().getUserObject();
            if(userObject instanceof Diagram) {
                Diagram diagram = (Diagram) userObject;
                DiagramPropertiesDialog dialog = new DiagramPropertiesDialog(diagram);
                if(!dialog.isCancelled()) {
                    ((DefaultTreeModel)app.getExplorer().getModel()).reload(selectedNode);
                }
            }
        }
    }

    @Override
    public void validate() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void interpret() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insert() {
        System.out.println("works!");
    }
}
