package hu.elte.strucker.view.editor;

import hu.elte.strucker.application.Application;
import hu.elte.strucker.model.ProjectStatus;
import hu.elte.strucker.model.diagram.Diagram;
import hu.elte.strucker.model.project.Library;
import hu.elte.strucker.model.project.Project;
import hu.elte.strucker.service.ResourceManager;
import hu.elte.strucker.view.menus.DiagramMenu;
import hu.elte.strucker.view.menus.ExplorerMenu;
import hu.elte.strucker.view.menus.LibraryMenu;
import hu.elte.strucker.view.menus.ProjectMenu;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class Explorer extends JTree {
    private static final Font LABEL_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
    private static final Font LABEL_BOLD_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 16);

    private Application app;
    private DefaultTreeModel model;
    private Map<Object, DefaultMutableTreeNode> nodes = new HashMap<>();
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Megnyitott projektek");

    public Explorer(Application app) {
        super();
        this.app = app;
        model = new DefaultTreeModel(root);
        setModel(model);
        setShowsRootHandles(true);
        addTreeSelectionListener(e -> {
            Object userObject = ((DefaultMutableTreeNode) e.getPath().getLastPathComponent()).getUserObject();
            if (userObject instanceof Project) {
                app.fireProjectSelected((Project) userObject);
            } else if (userObject instanceof Diagram) {
                app.fireDiagramSelected((Diagram) userObject);
            } else if (userObject instanceof Library) {
                app.fireLibrarySelected((Library) userObject);
            } else {
                app.fireNothingSelected();
            }
        });

        setCellRenderer((tree, node, selected, expanded, leaf, row, hasFocus) -> {
            ImageIcon projectIcon = ResourceManager.getIcon("images/explorer/project.png");
            ImageIcon diagramIcon = ResourceManager.getIcon("images/explorer/diagram.png");
            ImageIcon libraryIcon = ResourceManager.getIcon("images/explorer/library.png");
            ImageIcon mainLibraryIcon = ResourceManager.getIcon("images/explorer/mainLibrary.png");
            ImageIcon modified = ResourceManager.getIcon("images/explorer/saved.png");;
            Object value = ((DefaultMutableTreeNode) node).getUserObject();
            JPanel panel = new JPanel(new FlowLayout());
            JLabel label = new JLabel();
            label.setFont(LABEL_FONT);
            panel.add(label);

            if (value instanceof Project) {
                Project project = (Project) value;
                label.setText(project.getName() + " [" + project.getLocation() + "]");
                label.setIcon(projectIcon);
                if(project.getStatus().equals(ProjectStatus.MODIFIED)) {
                    panel.add(new JLabel(modified));
                }
                panel.setBackground(selected ? Color.lightGray : Color.white);
            }
            if (value instanceof Diagram) {
                Diagram diagram = (Diagram) value;
                label.setText(diagram.getName() + "() : " + diagram.getType().getSimpleName());
                label.setIcon(diagramIcon);
                JLabel healthCheck = new JLabel(diagram.getHealthCheck().getIcon());
                panel.add(healthCheck);
                panel.setBackground(selected ? Color.lightGray : Color.white);
            }
            if(value instanceof Library) {
                Library library = (Library) value;
                label.setText(library.getName());
                label.setIcon(library.isPermanent() ? mainLibraryIcon : libraryIcon);
                panel.setBackground(selected ? Color.lightGray : Color.white);
            }
            if (value instanceof String) {
                label.setText((String) value);
                label.setFont(LABEL_BOLD_FONT);
                panel.setBackground(Color.white);
            }
            return panel;
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                int row = getRowForLocation(mouseEvent.getX(), mouseEvent.getY());
                if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
                    if (row != -1) {
                        Object userObject = ((DefaultMutableTreeNode) getPathForRow(row).getLastPathComponent()).getUserObject();
                        if (userObject instanceof Project) {
                            Project project = (Project) userObject;
                            ProjectMenu projectMenu = new ProjectMenu(project, app.getProjectController());
                            projectMenu.show((Component) mouseEvent.getSource(), mouseEvent.getX(), mouseEvent.getY());
                        } else if (userObject instanceof Diagram) {
                            Diagram diagram = (Diagram) userObject;
                            DiagramMenu diagramMenu = new DiagramMenu(diagram, app.getDiagramController());
                            diagramMenu.show((Component) mouseEvent.getSource(), mouseEvent.getX(), mouseEvent.getY());
                        } else if (userObject instanceof Library) {
                            Library library = (Library) userObject;
                            LibraryMenu libraryMenu = new LibraryMenu(library, app);
                            libraryMenu.show((Component) mouseEvent.getSource(), mouseEvent.getX(), mouseEvent.getY());
                        } else {
                            ExplorerMenu explorerMenu = new ExplorerMenu(app.getExplorerController());
                            explorerMenu.show((Component) mouseEvent.getSource(), mouseEvent.getX(), mouseEvent.getY());
                        }
                    } else {
                        ExplorerMenu explorerMenu = new ExplorerMenu(app.getExplorerController());
                        explorerMenu.show((Component) mouseEvent.getSource(), mouseEvent.getX(), mouseEvent.getY());
                    }
                } else {
                    if (mouseEvent.getClickCount() == 2) {
                        if (row != -1) {
                            Object userObject = ((DefaultMutableTreeNode) getPathForRow(row).getLastPathComponent()).getUserObject();
                            if (userObject instanceof Diagram) {
                                app.fireDiagramOpening((Diagram) userObject);
                            }
                        }
                    } else {
                        if(row != -1) {
                            Object userObject = ((DefaultMutableTreeNode) getPathForRow(row).getLastPathComponent()).getUserObject();
                            if (userObject instanceof Diagram) {
                                Diagram diagram = (Diagram) userObject;
                                if (app.isDiagramOpen(diagram)) {
                                    app.fireDiagramOpening(diagram);
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    public void fireProjectInserted(Project project) {
        DefaultMutableTreeNode projectNode = new DefaultMutableTreeNode(project);
        nodes.put(project, projectNode);
        for (Library library : project.getLibraries()) {
            fireLibraryInserted(library);
        }
        model.insertNodeInto(projectNode, root, model.getChildCount(root));
        expandPath(new TreePath(projectNode.getPath()));
    }

    public void fireLibraryInserted(Library library) {
        DefaultMutableTreeNode libraryNode = new DefaultMutableTreeNode(library);
        nodes.put(library, libraryNode);
        for (Diagram diagram : library.getDiagrams()) {
            fireDiagramInserted(diagram);
        }
        model.insertNodeInto(libraryNode, nodes.get(library.getProject()), model.getChildCount(nodes.get(library.getProject())));
        expandPath(new TreePath(libraryNode.getPath()));
    }

    public void fireDiagramInserted(Diagram diagram) {
        DefaultMutableTreeNode diagramNode = new DefaultMutableTreeNode(diagram);
        nodes.put(diagram, diagramNode);
        DefaultMutableTreeNode libraryNode = nodes.get(diagram.getLibrary());
        model.insertNodeInto(diagramNode, libraryNode, model.getChildCount(libraryNode));
        expandPath(new TreePath(diagramNode.getPath()));
    }

    public void fireNodeRemoved(Object object) {
        DefaultMutableTreeNode node = nodes.get(object);
        if (node != null) {
            model.removeNodeFromParent(node);
            nodes.remove(object);
        }
    }

    public void fireNodeChanged(Object object) {
        DefaultMutableTreeNode node = nodes.get(object);
        if (node != null) {
            model.nodeChanged(node);
        }
    }


    public void collapse() {
        for (int i = 0; i < getRowCount(); i++) {
            TreePath path = getPathForRow(i);
            collapsePath(path);
            fireTreeCollapsed(path);
        }
    }

    private void collapseNode(Object object) {
        DefaultMutableTreeNode node = nodes.get(object);
        TreePath path = new TreePath(node.getPath());
        collapsePath(path);
        fireTreeCollapsed(path);
    }

    private void expandNode(Object object) {
        DefaultMutableTreeNode node = nodes.get(object);
        TreePath path = new TreePath(node.getPath());
        expandPath(path);
        fireTreeExpanded(path);
    }

    public void expand() {
        for (int i = 0; i < getRowCount(); i++) {
            TreePath path = getPathForRow(i);
            expandPath(path);
            fireTreeExpanded(path);
        }
    }

}
