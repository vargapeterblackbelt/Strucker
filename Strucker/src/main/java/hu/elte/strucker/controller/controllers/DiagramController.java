package hu.elte.strucker.controller.controllers;

import hu.elte.strucker.controller.Application;
import hu.elte.strucker.controller.operations.DiagramOperations;
import hu.elte.strucker.helper.TreeHelper;
import hu.elte.strucker.model.diagram.Diagram;
import hu.elte.strucker.model.diagram.Structogram;
import hu.elte.strucker.model.diagram.StructogramType;
import hu.elte.strucker.model.project.Project;
import hu.elte.strucker.view.dialogs.creation.CreateStructogramDialog;
import hu.elte.strucker.view.dialogs.properties.DiagramPropertiesDialog;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import static hu.elte.strucker.helper.Naming.qualifiedPath;
import static hu.elte.strucker.helper.ObjectLoader.serialize;
import static hu.elte.strucker.helper.TreeHelper.extract;
import static hu.elte.strucker.helper.TreeHelper.reload;

public class DiagramController extends Controller implements DiagramOperations {

    public DiagramController(Application app) {
        super(app);
    }

    @Override
    public void create() {
        Project project = extract(app.getSelectedNode(), Project.class);
        if(project != null) {
            Diagram newDiagram = new Diagram();
            DiagramPropertiesDialog dialog = new DiagramPropertiesDialog(newDiagram);
            if(!dialog.isCancelled()) {
                project.addDiagram(newDiagram);
                TreeHelper.insert(app.getExplorer(), newDiagram.getTree(), app.getSelectedNode());
            }
        }
    }

    @Override
    public void save() {
        Project project = extract((DefaultMutableTreeNode) app.getSelectedNode().getParent(), Project.class);
        if(project != null) {
            serialize(qualifiedPath(project), project);
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
    public void copy() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void cut() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void paste() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete() {
        Diagram diagram = extract(app.getSelectedNode(), Diagram.class);
        if(diagram != null) {
            Project project = extract((DefaultMutableTreeNode) app.getSelectedNode().getParent(), Project.class);
            project.removeDiagram(diagram);
            TreeHelper.remove(app.getExplorer(), app.getSelectedNode());
        }
    }

    @Override
    public void showProperties() {
        Diagram diagram = extract(app.getSelectedNode(), Diagram.class);
        if(diagram != null) {
            DiagramPropertiesDialog dialog = new DiagramPropertiesDialog(diagram);
            if(!dialog.isCancelled()) {
                reload(app.getExplorer(), app.getSelectedNode());
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
        Diagram diagram = extract(app.getSelectedNode(), Diagram.class);
        if(diagram != null) {
            CreateStructogramDialog dialog = new CreateStructogramDialog(StructogramType.STATEMENT);
            Structogram product = dialog.getProduct();
            if(product != null) {
                TreeHelper.insert(app.getExplorer(), (DefaultMutableTreeNode) product.getTree(), app.getSelectedNode());
            }
        }
    }
}
