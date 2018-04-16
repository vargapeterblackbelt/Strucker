package hu.elte.strucker.view.dialogs;

import hu.elte.strucker.model.diagram.Diagram;
import hu.elte.strucker.model.diagram.Statement;
import hu.elte.strucker.model.interpreter.Parameter;
import hu.elte.strucker.model.project.Project;
import hu.elte.strucker.view.UIProperties;
import hu.elte.strucker.view.tools.GradientPanel;
import lombok.NonNull;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;

public class ProjectPropertiesDialog extends StruckerDialog {

    private Project project;

    private JButton browseButton;
    private JButton cancelButton;
    private JTextArea descriptionField;
    private JLabel descriptionLabel;
    private JCheckBox initCheckBox;
    private JScrollPane jScrollPane1;
    private JTextField locationField;
    private JLabel locationLabel;
    private JPanel mainPanel;
    private JTextField nameField;
    private JLabel nameLabel;
    private JButton okButton;

    private JFileChooser fileChooser;

    public ProjectPropertiesDialog(@NonNull Project project, boolean create) {
        super();
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Projekt tulajdonságai");
        okButton.addActionListener(e -> ok());
        cancelButton.addActionListener(e -> cancel());
        this.project = project;
        nameField.setText(project.getName());
        descriptionField.setText(project.getDescription());
        locationField.setText(project.getLocation());
        locationField.setEditable(false);
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        browseButton.addActionListener(e -> browse());
        initCheckBox.setBorderPaintedFlat(true);
        if(!create) {
            initCheckBox.setSelected(false);
            initCheckBox.setVisible(false);
        }
    }

    private void browse() {
        fileChooser.showOpenDialog(this);
        locationField.setText(fileChooser.getSelectedFile().getPath());
    }

    private void initComponents() {
        mainPanel = new GradientPanel(Color.white, UIProperties.gardientPanelColor);//new JPanel();
        nameLabel = new JLabel();
        nameField = new JTextField();
        descriptionLabel = new JLabel();
        jScrollPane1 = new JScrollPane();
        descriptionField = new JTextArea();
        locationLabel = new JLabel();
        locationField = new JTextField();
        browseButton = new JButton();
        okButton = new JButton();
        cancelButton = new JButton();
        initCheckBox = new JCheckBox();

        setTitle("Projekt tulajdonságai");
        setBackground(new Color(255, 255, 255));

        mainPanel.setBackground(new Color(255, 255, 255));

        nameLabel.setText("Projekt neve");

        descriptionLabel.setText("Projekt leírása");

        descriptionField.setColumns(20);
        descriptionField.setRows(5);
        jScrollPane1.setViewportView(descriptionField);

        locationLabel.setText("Hely");

        browseButton.setText("Tallózás");

        okButton.setText("OK");

        cancelButton.setText("Mégse");

        initCheckBox.setBackground(new Color(255, 255, 255));
        initCheckBox.setOpaque(true);
        initCheckBox.setSelected(true);
        initCheckBox.setText("Inicializálás");

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(nameField)
                                        .addComponent(jScrollPane1)
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(nameLabel)
                                                        .addComponent(descriptionLabel)
                                                        .addComponent(locationLabel))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addComponent(locationField)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(browseButton))
                                        .addGroup(GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                                .addComponent(initCheckBox)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                                                .addComponent(cancelButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(okButton)))
                                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(nameLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(descriptionLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(locationLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(browseButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(locationField))
                                .addGap(22, 22, 22)
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(okButton)
                                        .addComponent(cancelButton)
                                        .addComponent(initCheckBox))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    @Override
    protected void ok() {
        if (nameField.getText().isEmpty() || locationField.getText().isEmpty()) {
            System.out.println("Nem lehetnek üresek a fieldek");
        } else {
            project.setName(nameField.getText());
            project.setDescription(descriptionField.getText());
            project.setLocation(locationField.getText());
            if(initCheckBox.isSelected()) {
                Diagram diagram = new Diagram("Main", "void",
                        Collections.singletonList(new Parameter("input", "String")));
                diagram.add(new Statement("SKIP"));
                project.addDiagram(diagram);
            }
            setVisible(false);
            dispose();
        }
    }
}
