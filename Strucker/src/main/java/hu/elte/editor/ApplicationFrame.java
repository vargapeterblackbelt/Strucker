package hu.elte.editor;

import hu.elte.editor.menus.ApplicationMenuBar;
import hu.elte.model.Explorable;
import hu.elte.model.project.Project;
import hu.elte.model.structogram.*;
import hu.elte.model.structogram.Action;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationFrame extends JFrame implements Application {

    private static final ImageIcon ADD_ICON = new ImageIcon("icons/project24.png");

    private List<Project> openProjects = new ArrayList<>();

    private JPanel containedPanel1;
    private JPanel containedPanel2;
    private JPanel mainPanel;
    private JSplitPane mainSplitPane;
    private JMenuBar menuBar;
    private ProjectExplorer projectExplorer;
    private JPanel projectExplorerPanel;
    private JScrollPane projectExplorerScrollPane;
    private JToolBar projectExplorerToolbar;
    private JPanel rightPanel;
    private JSplitPane sidePanel;
    private JPanel statusBar;
    private JTabbedPane tabbedPanel;
    private JToolBar executeToolBar;
    private JTextArea consoleField;
    private JPanel executePanel;
    private JScrollPane executeScrollPane;

    private StruckerButton newDiagramButton;
    private StruckerButton propertiesButton;
    private StruckerButton deleteButton;
    private StruckerButton addElementButton;
    private StruckerButton copyButton;
    private StruckerButton cutButton;
    private StruckerButton pasteButton;

    public ApplicationFrame() {
        initModel();
        initTree();
        initComponents();
        initButtons();
        menuBar = new ApplicationMenuBar(this);
        setJMenuBar(menuBar);
        setTitle("Strucker");
        try {
            setIconImage(ImageIO.read(new File("icons/strucker64.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initTree() {
//        DefaultMutableTreeNode mainNode = new DefaultMutableTreeNode();
//        for (Project openProject : openProjects) {
//            DefaultMutableTreeNode projectNode = (DefaultMutableTreeNode) openProject.getTree();
//            mainNode.add(projectNode);
//        }
//        projectExplorer = new Explorer(mainNode);
        projectExplorer = new ProjectExplorer(openProjects.get(0));
    }

    private void initModel() {
        openProjects.add(new Project("test", "test", "location", "én"));
        openProjects.add(new Project("test1", "test", "location", "én"));
        openProjects.add(new Project("test2", "test", "location", "én"));

    }

    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        statusBar = new javax.swing.JPanel();
        mainSplitPane = new javax.swing.JSplitPane();
        rightPanel = new javax.swing.JPanel();
        tabbedPanel = new javax.swing.JTabbedPane();
        containedPanel1 = new javax.swing.JPanel();
        containedPanel2 = new javax.swing.JPanel();
        sidePanel = new javax.swing.JSplitPane();
        projectExplorerPanel = new javax.swing.JPanel();
        projectExplorerToolbar = new javax.swing.JToolBar();
        projectExplorerScrollPane = new javax.swing.JScrollPane();
        executePanel = new javax.swing.JPanel();
        executeToolBar = new javax.swing.JToolBar();
        executeScrollPane = new javax.swing.JScrollPane();
        consoleField = new javax.swing.JTextArea();
        menuBar = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(1024, 768));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        statusBar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        javax.swing.GroupLayout statusBarLayout = new javax.swing.GroupLayout(statusBar);
        statusBar.setLayout(statusBarLayout);
        statusBarLayout.setHorizontalGroup(
                statusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        statusBarLayout.setVerticalGroup(
                statusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 16, Short.MAX_VALUE)
        );

        mainSplitPane.setDividerLocation(500);
        mainSplitPane.setDividerSize(5);

        tabbedPanel.setRequestFocusEnabled(false);

        javax.swing.GroupLayout containedPanel1Layout = new javax.swing.GroupLayout(containedPanel1);
        containedPanel1.setLayout(containedPanel1Layout);
        containedPanel1Layout.setHorizontalGroup(
                containedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 513, Short.MAX_VALUE)
        );
        containedPanel1Layout.setVerticalGroup(
                containedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 692, Short.MAX_VALUE)
        );

        tabbedPanel.addTab("tab1", containedPanel1);

        containedPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout containedPanel2Layout = new javax.swing.GroupLayout(containedPanel2);
        containedPanel2.setLayout(containedPanel2Layout);
        containedPanel2Layout.setHorizontalGroup(
                containedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 513, Short.MAX_VALUE)
        );
        containedPanel2Layout.setVerticalGroup(
                containedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 692, Short.MAX_VALUE)
        );

        tabbedPanel.addTab("tab2", containedPanel2);

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
                rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tabbedPanel)
        );
        rightPanelLayout.setVerticalGroup(
                rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tabbedPanel, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        mainSplitPane.setRightComponent(rightPanel);

        sidePanel.setDividerLocation(400);
        sidePanel.setDividerSize(5);
        sidePanel.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        projectExplorerToolbar.setFloatable(false);
        projectExplorerToolbar.setRollover(true);

        projectExplorerScrollPane.setViewportView(projectExplorer);

        javax.swing.GroupLayout projectExplorerPanelLayout = new javax.swing.GroupLayout(projectExplorerPanel);
        projectExplorerPanel.setLayout(projectExplorerPanelLayout);
        projectExplorerPanelLayout.setHorizontalGroup(
                projectExplorerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(projectExplorerToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(projectExplorerScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
        );
        projectExplorerPanelLayout.setVerticalGroup(
                projectExplorerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(projectExplorerPanelLayout.createSequentialGroup()
                                .addComponent(projectExplorerToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(projectExplorerScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE))
        );

        sidePanel.setTopComponent(projectExplorerPanel);

        executeToolBar.setFloatable(false);
        executeToolBar.setRollover(true);

        consoleField.setColumns(20);
        consoleField.setRows(5);
        executeScrollPane.setViewportView(consoleField);

        javax.swing.GroupLayout executePanelLayout = new javax.swing.GroupLayout(executePanel);
        executePanel.setLayout(executePanelLayout);
        executePanelLayout.setHorizontalGroup(
                executePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(executeToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(executeScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
        );
        executePanelLayout.setVerticalGroup(
                executePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, executePanelLayout.createSequentialGroup()
                                .addComponent(executeToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(executeScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
        );

        sidePanel.setRightComponent(executePanel);

        mainSplitPane.setLeftComponent(sidePanel);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(statusBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mainSplitPane)
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                .addComponent(mainSplitPane)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void initButtons() {
        newDiagramButton = new StruckerButton("Új diagram", "Új diagram hozzáadása a projekthez", e -> newDiagram());
        projectExplorerToolbar.add(newDiagramButton);
        addElementButton = new StruckerButton("Elem hozzáadása", "Hozzáad egy új elemet a kijelölt elemhez", e -> addElement());
        projectExplorerToolbar.add(addElementButton);
        deleteButton = new StruckerButton("Törlés", "Törli a kijelölt elemet", e -> delete());
        projectExplorerToolbar.add(deleteButton);
        projectExplorerToolbar.addSeparator();
        propertiesButton = new StruckerButton("Tulajdonságok", "A kijelölt elem tulajdonságai", e -> showProperties());
        projectExplorerToolbar.add(propertiesButton);
        projectExplorerToolbar.addSeparator();
        copyButton = new StruckerButton("Másolás", "Másolás", e -> copy());
        projectExplorerToolbar.add(copyButton);
        cutButton = new StruckerButton("Kivágás", "Vágólapra helyezi az elemet", e -> cut());
        projectExplorerToolbar.add(cutButton);
        pasteButton = new StruckerButton("Beillesztés", "Beilleszti a vágólapról az elemet", e -> paste());
        projectExplorerToolbar.add(pasteButton);
    }

    public static void main(String[] args) {
        new ApplicationFrame().setVisible(true);
    }

    @Override
    public void newDiagram() {

    }

    @Override
    public void newProject() {

    }

    @Override
    public void openDiagram() {

    }

    @Override
    public void save() {

    }

    @Override
    public void openProject() {

    }

    @Override
    public void saveAsProject() {

    }

    @Override
    public void showSettings() {

    }

    @Override
    public void exit() {

    }

    @Override
    public void close() {

    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    @Override
    public void copy() {

    }

    @Override
    public void cut() {

    }

    @Override
    public void paste() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void zoomIn() {

    }

    @Override
    public void zoomOut() {

    }

    @Override
    public void zoomDefault() {

    }

    @Override
    public void check() {

    }

    @Override
    public void run() {

    }

    @Override
    public void runDebug() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void about() {

    }

    @Override
    public void closeWindow() {

    }

    @Override
    public void showProperties() {

    }

    @Override
    public void addElement() {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) projectExplorer.getLastSelectedPathComponent();
        if(node == null) {
            printMessage("Semmi sincs kiválasztva");
        } else {
            Explorable explorable = (Explorable) node.getUserObject();
            if(explorable instanceof Sequence) {
                Sequence sequence = (Sequence) explorable;
                Loop action = new Loop("i < 0");
                sequence.add(action);
                projectExplorer.add(node, action);
            }
            if(explorable.getParent() instanceof Sequence) {
                Sequence parent = (Sequence) explorable.getParent();
                Action action1 = new Action("BEFORE");
                projectExplorer.addBefor(node, action1);
                Branch action2 = new Branch("i == 5");
                parent.addAfter((Structogram) explorable, action2);

                projectExplorer.addAfter(node, action2);
            }

        }
    }

    @Override
    public void printMessage(String msg) {
        consoleField.setText(consoleField.getText() + "\n" + msg);
    }
}
