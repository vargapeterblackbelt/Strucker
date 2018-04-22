package hu.elte.strucker.view.explorer;

import hu.elte.strucker.controller.Application;
import hu.elte.strucker.controller.operations.ViewerOperations;
import hu.elte.strucker.model.diagram.*;
import hu.elte.strucker.model.project.Project;
import hu.elte.strucker.view.UIProperties;
import hu.elte.strucker.view.tools.RoundedPanel;
import lombok.Getter;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.Color.WHITE;

public class StruckerExplorer extends JTree {

    private Application app;
    private ViewerOperations explorerController;
    @Getter
    private DefaultMutableTreeNode selectedNode = null;

    public StruckerExplorer(Application app, ViewerOperations explorerController) {
        super(new DefaultMutableTreeNode("Nincsenek megnyitva projektek"));
        this.explorerController = explorerController;
        this.app = app;
        setOpaque(true);
        openProjects();
        setSelectionModel(null);
        putClientProperty("JTree.lineStyle", "None");
        putClientProperty("", null);
        setShowsRootHandles(true);
        setRootVisible(false);
        setCellRenderer(getRenderer());
        addMouseListener(getMouseListener());
    }

    private void openProjects() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Megnyitott projektek");
        for (Project project : app.getOpenProjects()) {
            DefaultMutableTreeNode projectNode = new DefaultMutableTreeNode(project);
            root.add(projectNode);
            for (Diagram diagram : project.getDiagrams()) {
                projectNode.add((MutableTreeNode) diagram.getTree());
            }
        }
        setModel(new DefaultTreeModel(root));
    }

    private TreeCellRenderer getRenderer() {
        return (tree, value, selected, expanded, leaf, row, hasFocus) -> {
            Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
            JPanel panel = new JPanel();
            RoundedPanel labelPanel = new RoundedPanel();
            labelPanel.setShady(true);
            JLabel label = new JLabel();

            labelPanel.add(label);
            panel.add(labelPanel);
            panel.setBackground(WHITE);
            panel.add(labelPanel);
            if (userObject instanceof Project) {
                Project project = (Project) userObject;
                labelPanel.setBackground(UIProperties.projectBackgroundColor);
                label.setText(project.getName());
            } else {
                if (userObject instanceof Diagram) {
                    Diagram diagram = (Diagram) userObject;
                    labelPanel.setBackground(UIProperties.diagramBackgroundColor);
                    label.setText(diagram.getName());
                } else {
                    if (userObject instanceof Loop) {
                        Loop loop = (Loop) userObject;
                        labelPanel.setBackground(UIProperties.loopBackgroundColor);
                        label.setText(loop.getCondition());
                    } else {
                        if (userObject instanceof Selection) {
                            Selection selection = (Selection) userObject;
                            labelPanel.setBackground(UIProperties.selectionBackgroundColor);
                            label.setText(selection.getCondition());
                        } else {
                            if (userObject instanceof Sequence) {
                                Sequence sequence = (Sequence) userObject;
                                labelPanel.setBackground(UIProperties.sequenceBackgroundColor);
                                label.setText(sequence.getLabel());
                            } else {
                                if (userObject instanceof Statement) {
                                    Statement statement = (Statement) userObject;
                                    labelPanel.setBackground(UIProperties.statementBackgroundColor);
                                    label.setText(statement.getStatement());
                                } else {
                                    label.setText(userObject.toString());
                                }
                            }
                        }
                    }
                }
            }

            if (value.equals(app.getSelectedNode())) {
                labelPanel.setBackground(labelPanel.getBackground().darker());
            }
            return panel;
        };
    }

    private MouseAdapter getMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TreePath tp = getPathForLocation(e.getX(), e.getY());
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (tp == null || tp.getLastPathComponent() == null || (selectedNode != null && selectedNode.equals(tp.getLastPathComponent()))) {
                        selectedNode = null;
                    } else {
                        selectedNode = (DefaultMutableTreeNode) tp.getLastPathComponent();
                    }
                    repaint();
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    if (tp != null) {
                        selectedNode = (DefaultMutableTreeNode) tp.getLastPathComponent();
                        repaint();
                        explorerController.showMenu();
                    }
                }
            }
        };
    }

}
