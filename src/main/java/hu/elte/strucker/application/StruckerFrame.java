package hu.elte.strucker.application;

import hu.elte.strucker.model.diagram.Diagram;
import hu.elte.strucker.model.project.Library;
import hu.elte.strucker.model.project.Project;
import hu.elte.strucker.service.MessageService;
import hu.elte.strucker.service.ResourceManager;
import hu.elte.strucker.view.editor.Explorer;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Getter
public class StruckerFrame extends JFrame implements ApplicationSelection {

    private final Application app;
    private final Explorer explorer;
    private final StruckerMenuBar struckerMenuBar;
    private final JPanel mainPanel;
    private final JScrollPane scrollPane;
    private final JSplitPane splitPane;
    private final JTabbedPane tabbedPanel;
    private final StruckerToolBar toolBar;

    public StruckerFrame(Application app) {
        super("Strucker");
        MessageService.install(this);
        setIconImage(ResourceManager.getImage("strucker.png"));
        this.app = app;
        explorer = new Explorer(app);
        struckerMenuBar = new StruckerMenuBar(app);
        mainPanel = new JPanel();
        scrollPane = new JScrollPane();
        splitPane = new JSplitPane();
        tabbedPanel = new JTabbedPane();
        toolBar = new StruckerToolBar(app);
        setJMenuBar(struckerMenuBar);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                app.exit();
            }
        });

        init();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
//        MessageService.paintMessages(g);
    }

    public void init() {
        tabbedPanel.setBackground(Color.LIGHT_GRAY);
        tabbedPanel.setForeground(Color.white);
        toolBar.setFloatable(false);
        toolBar.setRollover(true);
        splitPane.setBackground(Color.white);

        splitPane.setDividerLocation(450);
        splitPane.setDividerSize(4);
        splitPane.setRightComponent(tabbedPanel);
        scrollPane.setViewportView(explorer);

        splitPane.setLeftComponent(scrollPane);

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(toolBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 898, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
    }

    @Override
    public void fireProjectSelected(Project project) {
        struckerMenuBar.fireProjectSelected(project);
        toolBar.fireProjectSelected(project);
    }

    @Override
    public void fireDiagramSelected(Diagram diagram) {
        struckerMenuBar.fireDiagramSelected(diagram);
        toolBar.fireDiagramSelected(diagram);
    }

    @Override
    public void fireLibrarySelected(Library library) {
        struckerMenuBar.fireLibrarySelected(library);
        toolBar.fireLibrarySelected(library);
    }

    @Override
    public void fireNothingSelected() {
        struckerMenuBar.fireNothingSelected();
        toolBar.fireNothingSelected();
    }
}
