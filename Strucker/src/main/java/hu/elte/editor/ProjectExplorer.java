package hu.elte.editor;

import hu.elte.model.Explorable;
import hu.elte.model.project.Diagram;
import hu.elte.model.project.Project;
import hu.elte.model.structogram.Action;
import hu.elte.model.structogram.Branch;
import hu.elte.model.structogram.Loop;
import hu.elte.model.structogram.Sequence;
import hu.elte.model.structogram.Structogram;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static hu.elte.editor.Images.*;
import static hu.elte.editor.Images.ACTION_ICON;
import static hu.elte.editor.Images.SEQUENCE_ICON;

public class ProjectExplorer extends JTree {

    private DefaultTreeModel model;
    private Project project;

    public ProjectExplorer(Project p) {
        super(new DefaultTreeModel(p.getTree()));
        this.project = p;
        model = (DefaultTreeModel) getModel();
        addMouseListener(getListener());

        setCellRenderer((tree, value, selected, expanded, leaf, row, hasFocus) -> {
            Object node = ((DefaultMutableTreeNode) value).getUserObject();
            if(node != null && node instanceof Explorable) {
                Explorable explorableNode = (Explorable) node;
                JLabel label = new JLabel(explorableNode.getExploredName());
                JPanel jPanel = new JPanel();
                jPanel.add(label);
                jPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
                if(selected) {
                    jPanel.setBackground(jPanel.getBackground().brighter());
                }
                if (explorableNode instanceof Project) {
                    label.setIcon(PROJECT_ICON);
                    return jPanel;
                }
                if (explorableNode instanceof Diagram) {
                    label.setIcon(DIAGRAM_ICON);
                    return jPanel;
                }
                if (explorableNode instanceof Loop) {
                    label.setIcon(LOOP_ICON);
                    return jPanel;
                }
                if (explorableNode instanceof Branch) {
                    label.setIcon(BRANCH_ICON);
                    return jPanel;
                }
                if (explorableNode instanceof Sequence) {
                    label.setIcon(SEQUENCE_ICON);
                    return jPanel;
                }
                if (explorableNode instanceof Action) {
                    label.setIcon(ACTION_ICON);
                    return jPanel;
                }
            }
            return new JLabel(node.toString());
        });
    }

    public void add(DefaultMutableTreeNode node, Structogram stg) {
        node.add((MutableTreeNode) stg.getTree());
        model.reload(node);
    }

    public void addAfter(DefaultMutableTreeNode node, Structogram stg) {
        if(!node.isRoot()) {
            DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
            int index = parent.getIndex(node);
            parent.insert((MutableTreeNode) stg.getTree(), index+1);
            model.reload(parent);
        }
    }

    public void addBefor(DefaultMutableTreeNode node, Structogram stg) {
        if(!node.isRoot()) {
            DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
            int index = parent.getIndex(node);
            parent.insert((MutableTreeNode) stg.getTree(), index);
            model.reload(parent);
        }
    }

    public Explorable getSelectedItem() {
        Object lastSelectedPathComponent = getLastSelectedPathComponent();
        if(lastSelectedPathComponent == null) {
            return null;
        } else {
            if(lastSelectedPathComponent instanceof Explorable) {
                return  (Explorable) lastSelectedPathComponent;
            } else {
                return null;
            }
        }
    }

    private Explorable getClickedItem(MouseEvent e) {
        TreePath pathForLocation = getPathForLocation(e.getPoint().x, e.getPoint().y);
        if(pathForLocation != null){
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) pathForLocation.getLastPathComponent();
            if(node == null || !(node.getUserObject() instanceof Explorable)) {
                return null;
            } else {
                return (Explorable) node.getUserObject();
            }
        } else {
            return null;
        }
    }

    private MouseAdapter getListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        };
    }

}
