package hu.elte.strucker;

import hu.elte.strucker.controller.Application;
import hu.elte.strucker.controller.controllers.*;
import hu.elte.strucker.controller.operations.*;
import hu.elte.strucker.model.project.Project;
import hu.elte.strucker.view.explorer.StruckerExplorer;
import hu.elte.strucker.view.tools.StruckerButton;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StruckerApplication implements Application {

    private JFrame window;
    private StruckerExplorer explorer;
    private List<Project> openProjects = new ArrayList<>();

    private ApplicationOperations applicationController;
    private ConsoleOperations consoleController;
    private DiagramOperations diagramController;
    private ProjectOperations projectController;
    private ViewerOperations explorerController;
    private StructogramOperations structogramController;
    private EditorOperations editorController;

    private JPanel mainPanel = new JPanel();
    private JPanel statusBar = new JPanel();
    private JSplitPane mainSplitPane = new JSplitPane();
    private JPanel rightPanel = new JPanel();
    private JTabbedPane tabbedPanel = new JTabbedPane();
    private JPanel containedPanel1 = new JPanel();
    private JPanel containedPanel2 = new JPanel();
    private JSplitPane sidePanel = new JSplitPane();
    private JPanel projectExplorerPanel = new JPanel();
    private JToolBar projectExplorerToolbar = new JToolBar();
    private JScrollPane projectExplorerScrollPane = new JScrollPane();
    private JPanel executePanel = new JPanel();
    private JToolBar executeToolBar = new JToolBar();
    private JScrollPane executeScrollPane = new JScrollPane();
    private JTextArea consoleField = new JTextArea();

    public StruckerApplication() {
        window = new JFrame();
        window.setTitle("Strucker");
        initControllers();
        explorer = new StruckerExplorer(this, explorerController);
        initComponents();
        initMenu();
        initButtons();
    }

    private void initControllers() {
        applicationController = new ApplicationController(this);
        consoleController = new ConsoleController(this);
        diagramController = new DiagramController(this);
        projectController = new ProjectController(this);
        explorerController = new ViewerController(this);
        structogramController = new StructogramController(this);
        editorController = new EditorController(this);
    }

    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Fájl");
        menuBar.add(fileMenu);
        addMenuItem(fileMenu, "Projekt létrehozása", e -> projectController.create());
        addMenuItem(fileMenu, "Új diagram", e -> diagramController.create());
        addMenuItem(fileMenu, "Új elem", e -> structogramController.insert());
        fileMenu.addSeparator();
        addMenuItem(fileMenu, "Megnyitás", e -> projectController.open());
        addMenuItem(fileMenu, "Mentés", e -> projectController.save());
        addMenuItem(fileMenu, "Mentés másként", e -> {
        }); // hiányos!
        addMenuItem(fileMenu, "Tulajdonságok", e -> projectController.showProperties());
        addMenuItem(fileMenu, "Bezárás", e -> projectController.close());
        fileMenu.addSeparator();
        addMenuItem(fileMenu, "Kilépés", e -> applicationController.close());

        JMenu editMenu = new JMenu("Szerkesztés");
        menuBar.add(editMenu);
        addMenuItem(editMenu, "Visszavonás", e -> editorController.undo());
        addMenuItem(editMenu, "Mégis", e -> editorController.redo());
        editMenu.addSeparator();
        addMenuItem(editMenu, "Másolás", e -> editorController.copy());
        addMenuItem(editMenu, "Kivágás", e -> editorController.cut());
        addMenuItem(editMenu, "Beillesztés", e -> editorController.paste());
        addMenuItem(editMenu, "Törlés", e -> editorController.delete());

        JMenu interpreterMenu = new JMenu("Értelmezés");
        menuBar.add(interpreterMenu);
        addMenuItem(interpreterMenu, "Ellenőrzés", e -> projectController.validate());
        addMenuItem(interpreterMenu, "Interpretáció indítása", e -> diagramController.interpret());

        JMenu appMenu = new JMenu("Egyebek");
        menuBar.add(appMenu);
        addMenuItem(appMenu, "Beállítások", e -> applicationController.showSettings());
        appMenu.addSeparator();
        addMenuItem(appMenu, "Dokumentáció", e -> applicationController.showDocumentation());
        addMenuItem(appMenu, "Az alkalmazásról", e -> applicationController.showAbout());

        window.setJMenuBar(menuBar);
    }

    private void initComponents() {
        mainPanel = new JPanel();
        statusBar = new JPanel();
        mainSplitPane = new JSplitPane();
        rightPanel = new JPanel();
        tabbedPanel = new JTabbedPane();
        containedPanel1 = new JPanel();
        containedPanel2 = new JPanel();
        sidePanel = new JSplitPane();
        projectExplorerPanel = new JPanel();
        projectExplorerToolbar = new JToolBar();
        projectExplorerScrollPane = new JScrollPane();
        executePanel = new JPanel();
        executeToolBar = new JToolBar();
        executeScrollPane = new JScrollPane();
        consoleField = new JTextArea();

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setMinimumSize(new Dimension(800, 600));

        mainPanel.setBackground(new Color(255, 255, 255));

        statusBar.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        GroupLayout statusBarLayout = new GroupLayout(statusBar);
        statusBar.setLayout(statusBarLayout);
        statusBarLayout.setHorizontalGroup(
                statusBarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        statusBarLayout.setVerticalGroup(
                statusBarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 16, Short.MAX_VALUE)
        );

        mainSplitPane.setDividerLocation(500);
        mainSplitPane.setDividerSize(5);

        tabbedPanel.setRequestFocusEnabled(false);

        GroupLayout containedPanel1Layout = new GroupLayout(containedPanel1);
        containedPanel1.setLayout(containedPanel1Layout);
        containedPanel1Layout.setHorizontalGroup(
                containedPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 513, Short.MAX_VALUE)
        );
        containedPanel1Layout.setVerticalGroup(
                containedPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 692, Short.MAX_VALUE)
        );

        tabbedPanel.addTab("tab1", containedPanel1);

        containedPanel2.setBackground(new Color(255, 255, 255));

        GroupLayout containedPanel2Layout = new GroupLayout(containedPanel2);
        containedPanel2.setLayout(containedPanel2Layout);
        containedPanel2Layout.setHorizontalGroup(
                containedPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 513, Short.MAX_VALUE)
        );
        containedPanel2Layout.setVerticalGroup(
                containedPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 692, Short.MAX_VALUE)
        );

        tabbedPanel.addTab("tab2", containedPanel2);

        GroupLayout rightPanelLayout = new GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
                rightPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(tabbedPanel)
        );
        rightPanelLayout.setVerticalGroup(
                rightPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(tabbedPanel, GroupLayout.Alignment.TRAILING)
        );

        mainSplitPane.setRightComponent(rightPanel);

        sidePanel.setDividerLocation(400);
        sidePanel.setDividerSize(5);
        sidePanel.setOrientation(JSplitPane.VERTICAL_SPLIT);

        projectExplorerToolbar.setFloatable(false);
        projectExplorerToolbar.setRollover(true);
        projectExplorerToolbar.setAlignmentX(0.0F);
        projectExplorerToolbar.setAlignmentY(0.0F);

        projectExplorerScrollPane.setViewportView(explorer);

        GroupLayout projectExplorerPanelLayout = new GroupLayout(projectExplorerPanel);
        projectExplorerPanel.setLayout(projectExplorerPanelLayout);
        projectExplorerPanelLayout.setHorizontalGroup(
                projectExplorerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(projectExplorerToolbar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(projectExplorerScrollPane, GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
        );
        projectExplorerPanelLayout.setVerticalGroup(
                projectExplorerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(projectExplorerPanelLayout.createSequentialGroup()
                                .addComponent(projectExplorerToolbar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(projectExplorerScrollPane, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))
        );

        sidePanel.setTopComponent(projectExplorerPanel);

        executeToolBar.setFloatable(false);
        executeToolBar.setRollover(true);

        consoleField.setColumns(20);
        consoleField.setRows(5);
        executeScrollPane.setViewportView(consoleField);

        GroupLayout executePanelLayout = new GroupLayout(executePanel);
        executePanel.setLayout(executePanelLayout);
        executePanelLayout.setHorizontalGroup(
                executePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(executeToolBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(executeScrollPane, GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
        );
        executePanelLayout.setVerticalGroup(
                executePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, executePanelLayout.createSequentialGroup()
                                .addComponent(executeToolBar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(executeScrollPane, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
        );

        sidePanel.setRightComponent(executePanel);

        mainSplitPane.setLeftComponent(sidePanel);

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(statusBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mainSplitPane)
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                .addComponent(mainSplitPane)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        GroupLayout layout = new GroupLayout(window.getContentPane());
        window.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        window.pack();
        window.setLocationRelativeTo(null);
    }

    private void initButtons() {
        addButton(projectExplorerToolbar, "Új diagram", e -> diagramController.create());
        addButton(projectExplorerToolbar, "Új elem", e -> diagramController.insert());
        addButton(projectExplorerToolbar, "Törlés", e -> diagramController.delete());
        projectExplorerToolbar.addSeparator();
        addButton(projectExplorerToolbar, "Tulajdonságok", e -> diagramController.showProperties());
        projectExplorerToolbar.addSeparator();
        addButton(projectExplorerToolbar, "Összecsukás", e -> explorerController.collapse());
        addButton(projectExplorerToolbar, "Kinyitás", e -> explorerController.expand());
        addButton(projectExplorerToolbar, "Beállítások", e -> explorerController.showMenu());

        addButton(executeToolBar, "Validálás", e -> projectController.validate());
        addButton(executeToolBar, "Diagram futtatása", e -> diagramController.interpret());
        executeToolBar.addSeparator();
        addButton(executeToolBar, "Konzol törlése", e -> consoleController.clear());
        executeToolBar.addSeparator();
        addButton(executeToolBar, "Elrejtés", e -> consoleController.hide());
        addButton(executeToolBar, "Megjelenítés", e -> consoleController.show());
    }

    private void initExplorer() {
//        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Megnyitott projektek");
//        for (Project project : openProjects) {
//            DefaultMutableTreeNode projectNode = new DefaultMutableTreeNode(project);
//            root.add(projectNode);
//            for (Diagram diagram : project.getDiagrams()) {
//                projectNode.add((MutableTreeNode) diagram.getTree());
//            }
//        }
//        DefaultTreeSelectionModel selectionModel = new DefaultTreeSelectionModel();
////        selectionModel.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
//        explorer = new JTree(root);
//        explorer.setSelectionModel(null);
////        explorer.addTreeSelectionListener(e -> System.out.println(e.getPath().getLastPathComponent()));
//        explorer.putClientProperty("JTree.lineStyle", "None");
//        explorer.putClientProperty("", null);
//        explorer.setShowsRootHandles(true);
//        explorer.setRootVisible(false);
//        explorer.setCellRenderer((tree, value, selected, expanded, leaf, row, hasFocus) -> {
//            Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
//            JPanel panel = new JPanel();
//            RoundedPanel labelPanel = new RoundedPanel();
//            labelPanel.setShady(true);
//            JLabel label = new JLabel();
//
//            labelPanel.add(label);
//            panel.add(labelPanel);
//            panel.setBackground(WHITE);
//            panel.add(labelPanel);
//            if (userObject instanceof Project) {
//                Project project = (Project) userObject;
//                labelPanel.setBackground(UIProperties.projectBackgroundColor);
//                label.setText(project.getName());
//            } else {
//                if (userObject instanceof Diagram) {
//                    Diagram diagram = (Diagram) userObject;
//                    labelPanel.setBackground(UIProperties.diagramBackgroundColor);
//                    label.setText(diagram.getName());
//                } else {
//                    if (userObject instanceof Loop) {
//                        Loop loop = (Loop) userObject;
//                        labelPanel.setBackground(UIProperties.loopBackgroundColor);
//                        label.setText(loop.getCondition());
//                    } else {
//                        if (userObject instanceof Selection) {
//                            Selection selection = (Selection) userObject;
//                            labelPanel.setBackground(UIProperties.selectionBackgroundColor);
//                            label.setText(selection.getCondition());
//                        } else {
//                            if (userObject instanceof Sequence) {
//                                Sequence sequence = (Sequence) userObject;
//                                labelPanel.setBackground(UIProperties.sequenceBackgroundColor);
//                                label.setText(sequence.getLabel());
//                            } else {
//                                if (userObject instanceof Statement) {
//                                    Statement statement = (Statement) userObject;
//                                    labelPanel.setBackground(UIProperties.statementBackgroundColor);
//                                    label.setText(statement.getStatement());
//                                } else {
//                                    label.setText(userObject.toString());
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//            if (value.equals(selectedNode)) {
//                labelPanel.setBackground(labelPanel.getBackground().darker());
//            }
//            return panel;
//        });
//        explorer.addTreeSelectionListener(e -> selectedNode = (DefaultMutableTreeNode) e.getPath().getLastPathComponent());
//        explorer.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                TreePath tp = explorer.getPathForLocation(e.getX(), e.getY());
//                if (e.getButton() == MouseEvent.BUTTON1) {
//                    if (tp == null || tp.getLastPathComponent() == null || (selectedNode != null && selectedNode.equals(tp.getLastPathComponent()))) {
//                        selectedNode = null;
//                    } else {
//                        selectedNode = (DefaultMutableTreeNode) tp.getLastPathComponent();
//                    }
//                    explorer.repaint();
//                }
//                if (e.getButton() == MouseEvent.BUTTON3) {
//                    if (tp != null) {
//                        selectedNode = (DefaultMutableTreeNode) tp.getLastPathComponent();
//                        explorer.repaint();
//                        JPopupMenu explorerMenu = new JPopupMenu("Műveletek");
//
//                        JMenuItem newDiagram = new JMenuItem("Új diagram");
//                        JMenuItem delete = new JMenuItem("Törlés");
//                        explorerMenu.add(newDiagram);
//                        newDiagram.addActionListener(em -> diagramController.create());
//                        explorerMenu.add(delete);
//                        if(extract(selectedNode, Project.class) == null) {
//                            delete.setVisible(false);
//                        }
//                        explorerMenu.show((Component) e.getSource(), e.getX(), e.getY());
//                    }
//                }
//            }
//        });
    }

    private void addButton(JComponent component, String text, ActionListener l) {
        StruckerButton button = new StruckerButton(text);
        button.addActionListener(l);
        component.add(button);
    }

    private void addMenuItem(JMenu menu, String text, ActionListener l) {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.addActionListener(l);
        menu.add(menuItem);
    }

    @Override
    public void run() {
        window.setVisible(true);
    }

    @Override
    public JTree getExplorer() {
        return explorer;
    }

    @Override
    public DefaultMutableTreeNode getSelectedNode() {
        return explorer.getSelectedNode();
    }

    @Override
    public List<Project> getOpenProjects() {
        return openProjects;
    }

    @Override
    public void exit() {

    }

    @Override
    public JFrame getWindow() {
        return window;
    }

    public static void main(String args[]) {
        UIManager.put("Tree.expandedIcon", new ImageIcon("icons/node_can_collapse.png"));
        UIManager.put("Tree.collapsedIcon", new ImageIcon("icons/node_can_expand.png"));
        new StruckerApplication().run();
    }
}
