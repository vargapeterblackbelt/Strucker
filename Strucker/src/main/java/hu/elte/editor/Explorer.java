package hu.elte.editor;

import hu.elte.model.Explorable;
import hu.elte.model.project.Diagram;
import hu.elte.model.project.Project;
import hu.elte.model.structogram.Action;
import hu.elte.model.structogram.Branch;
import hu.elte.model.structogram.Loop;
import hu.elte.model.structogram.Sequence;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;

import static hu.elte.editor.Images.*;

public class Explorer extends JTree {

    private DefaultTreeModel model;

    public Explorer(TreeNode root) {
        super(new DefaultTreeModel(root));
        model = (DefaultTreeModel) getModel();
        setRootVisible(false);
        getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        setCellRenderer((tree, value, selected, expanded, leaf, row, hasFocus) -> {
            Object node = ((DefaultMutableTreeNode) value).getUserObject();
            if(node != null && node instanceof Explorable) {
                Explorable explorableNode = (Explorable) node;
                JLabel label = new JLabel(explorableNode.getExploredName());
                if(selected) {
                    label.setForeground(Color.BLUE);
                }
                if (explorableNode instanceof Project) {
                    label.setIcon(PROJECT_ICON);
                    return label;
                }
                if (explorableNode instanceof Diagram) {
                    label.setIcon(DIAGRAM_ICON);
                    return label;
                }
                if (explorableNode instanceof Loop) {
                    label.setIcon(LOOP_ICON);
                    return label;
                }
                if (explorableNode instanceof Branch) {
                    label.setIcon(BRANCH_ICON);
                    return label;
                }
                if (explorableNode instanceof Sequence) {
                    label.setIcon(SEQUENCE_ICON);
                    return label;
                }
                if (explorableNode instanceof Action) {
                    label.setIcon(ACTION_ICON);
                    return label;
                }
            }
            return new JLabel(node.toString());
        });
    }

    public void reload() {
        model.reload();
    }


}
