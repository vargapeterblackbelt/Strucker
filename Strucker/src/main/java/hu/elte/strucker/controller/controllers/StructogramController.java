package hu.elte.strucker.controller.controllers;

import hu.elte.strucker.controller.Application;
import hu.elte.strucker.controller.operations.StructogramOperations;
import hu.elte.strucker.helper.TreeHelper;
import hu.elte.strucker.model.Explorable;
import hu.elte.strucker.model.diagram.Sequence;
import hu.elte.strucker.model.diagram.Structogram;
import hu.elte.strucker.model.diagram.StructogramType;
import hu.elte.strucker.view.dialogs.creation.CreateStructogramDialog;
import hu.elte.strucker.view.dialogs.creation.StructogramPropertiesDialog;

import javax.swing.tree.DefaultMutableTreeNode;

import static hu.elte.strucker.helper.TreeHelper.extract;
import static hu.elte.strucker.helper.TreeHelper.reload;

public class StructogramController extends Controller implements StructogramOperations {
    public StructogramController(Application app) {
        super(app);
    }

    @Override
    public void delete() {
        Structogram structogram = extract(app.getSelectedNode(), Structogram.class);
        if(structogram != null && !(structogram instanceof Sequence)) {
            Sequence sequence = extract((DefaultMutableTreeNode) app.getSelectedNode().getParent(), Sequence.class);
            sequence.remove(structogram);
            TreeHelper.remove(app.getExplorer(), app.getSelectedNode());
        }
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
    public void insert() {
        Sequence sequence = extract(app.getSelectedNode(), Sequence.class);
        if(sequence != null) {
            CreateStructogramDialog dialog = new CreateStructogramDialog(StructogramType.STATEMENT);
            Structogram product = dialog.getProduct();
            if(product != null) {
                sequence.add(product);
                TreeHelper.insert(app.getExplorer(),
                        product.getTree(),
                        app.getSelectedNode());
            }
        }
    }

    @Override
    public void insertBefore() {
        Structogram structogram = extract(app.getSelectedNode(), Structogram.class);
        Sequence parent = extract((DefaultMutableTreeNode) app.getSelectedNode().getParent(), Sequence.class);
        if(structogram != null && parent != null) {
            CreateStructogramDialog dialog = new CreateStructogramDialog(StructogramType.STATEMENT);
            Structogram product = dialog.getProduct();
            if(product != null) {
                System.out.println(parent + " " + structogram + " " + product);
                parent.addBefore(structogram, product);
                TreeHelper.insertBefore(app.getExplorer(),
                        product.getTree(),
                        app.getSelectedNode().getParent(),
                        app.getSelectedNode());
            }
        }
    }

    @Override
    public void insertAfter() {
        Structogram structogram = extract(app.getSelectedNode(), Structogram.class);
        Sequence parent = extract((DefaultMutableTreeNode) app.getSelectedNode().getParent(), Sequence.class);
        if(structogram != null && parent != null) {
            CreateStructogramDialog dialog = new CreateStructogramDialog(StructogramType.STATEMENT);
            Structogram product = dialog.getProduct();
            if(product != null) {
                parent.addAfter(structogram, product);
                TreeHelper.insertAfter(app.getExplorer(),
                        product.getTree(),
                        app.getSelectedNode().getParent(),
                        app.getSelectedNode());
            }
        }
    }

    @Override
    public void moveUp() {
        Structogram structogram = extract(app.getSelectedNode(), Structogram.class);
        Sequence parent = extract((DefaultMutableTreeNode) app.getSelectedNode().getParent(), Sequence.class);
        if(structogram != null && parent != null && parent.indexOf(structogram) > 0) {
            parent.move(structogram, parent.indexOf(structogram)-1);
            TreeHelper.move(app.getExplorer(),
                    app.getSelectedNode(),
                    app.getSelectedNode().getParent().getIndex(app.getSelectedNode())-1);
        }
    }

    @Override
    public void moveDown() {
        Structogram structogram = extract(app.getSelectedNode(), Structogram.class);
        Sequence parent = extract((DefaultMutableTreeNode) app.getSelectedNode().getParent(), Sequence.class);
        if(structogram != null && parent != null && parent.indexOf(structogram) < parent.getChilds().size()) {
            parent.move(structogram, parent.indexOf(structogram));
            TreeHelper.move(app.getExplorer(),
                    app.getSelectedNode(),
                    app.getSelectedNode().getParent().getIndex(app.getSelectedNode()));
        }
    }

    @Override
    public void showProperties() {
        Structogram structogram = extract(app.getSelectedNode(), Structogram.class);
        if(structogram != null) {
            StructogramPropertiesDialog dialog = new StructogramPropertiesDialog(structogram);
            if(!dialog.isCancelled()) {
                reload(app.getExplorer(), app.getSelectedNode());
            }
        }
    }
}
