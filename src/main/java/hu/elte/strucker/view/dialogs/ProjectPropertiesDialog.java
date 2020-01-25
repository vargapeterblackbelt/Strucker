package hu.elte.strucker.view.dialogs;

import hu.elte.strucker.model.project.Project;
import hu.elte.strucker.view.UIProperties;
import hu.elte.strucker.view.dialogs.StruckerDialog;
import hu.elte.strucker.view.tools.GradientPanel;
import lombok.NonNull;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class ProjectPropertiesDialog extends StruckerDialog {

    private Project project;

    private JButton browseButton;
    private JButton cancelButton;
    private JTextField locationField;
    private JLabel locationLabel;
    private JPanel mainPanel;
    private JTextField nameField;
    private JLabel nameLabel;
    private JButton okButton;

    private JFileChooser fileChooser;
    private boolean create;

    public ProjectPropertiesDialog(@NonNull Project project, boolean create) {
        super();
        this.create = create;
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Projekt tulajdonságai");
        okButton.addActionListener(e -> ok());
        cancelButton.addActionListener(e -> cancel());
        this.project = project;
        nameField.setText(project.getName());
        locationField.setText(project.getLocation());
        locationField.setEditable(false);
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Strucker projekt: .strucker", "strucker"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        browseButton.addActionListener(e -> browse());
        if(!create) {
        }
        setModal(true);
        setVisible(true);
    }

    private void browse() {
        fileChooser.showSaveDialog(this);
        File file = fileChooser.getSelectedFile();
        if(file != null) {
            locationField.setText(file.getPath()+(file.getPath().endsWith(".strucker") ? "" : ".strucker"));
        }
    }

    private void initComponents() {
        mainPanel = new GradientPanel(Color.white, UIProperties.gardientPanelColor);//new JPanel();
        nameLabel = new JLabel();
        nameField = new JTextField();
        locationLabel = new JLabel();
        locationField = new JTextField();
        browseButton = new JButton();
        okButton = new JButton();
        cancelButton = new JButton();

        setTitle("Projekt tulajdonságai");
        setBackground(new Color(255, 255, 255));

        mainPanel.setBackground(new Color(255, 255, 255));

        nameLabel.setText("Projekt neve");

        locationLabel.setText("Hely");

        browseButton.setText("Tallózás");

        okButton.setText("OK");

        cancelButton.setText("Mégse");

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(nameField)
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(nameLabel)
                                                        .addComponent(locationLabel))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addComponent(locationField)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(browseButton))
                                        .addGroup(GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
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
                                .addComponent(locationLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(browseButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(locationField))
                                .addGap(22, 22, 22)
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(okButton)
                                        .addComponent(cancelButton))
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
            project.setLocation(locationField.getText());
            setVisible(false);
            dispose();
        }
    }
}
