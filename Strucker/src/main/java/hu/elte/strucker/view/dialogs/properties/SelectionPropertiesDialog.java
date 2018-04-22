package hu.elte.strucker.view.dialogs.properties;

import hu.elte.strucker.model.diagram.Loop;
import hu.elte.strucker.model.diagram.Selection;
import hu.elte.strucker.view.dialogs.StruckerDialog;

import javax.swing.*;

public class SelectionPropertiesDialog extends StruckerDialog {

    private JButton cancelButton;
    private JTextField definitionField;
    private JLabel definitionLabel;
    private JTextArea descriptionField;
    private JLabel descriptionLabel;
    private JScrollPane jScrollPane1;
    private JPanel mainPanel;
    private JButton okButton;
    private JLabel warningLabel;

    private Selection target;

    public SelectionPropertiesDialog(Selection target) {
        super();
        this.target = target;
        initComponents();
        definitionField.setText(target.getCondition());
        descriptionField.setText(target.getDescription());

        okButton.addActionListener(e -> ok());
        cancelButton.addActionListener(e -> cancel());
        setModal(true);
        setVisible(true);
    }

    protected void ok() {
        String text = definitionField.getText().trim();
        String description = descriptionField.getText().trim();
        if(text.isEmpty()) {
            warningLabel.setVisible(true);
        } else {
            target.setDescription(description);
            target.setCondition(text);
            setVisible(false);
            dispose();
        }
    }

    private void initComponents() {

        mainPanel = new JPanel();
        definitionLabel = new JLabel();
        definitionField = new JTextField();
        descriptionLabel = new JLabel();
        jScrollPane1 = new JScrollPane();
        descriptionField = new JTextArea();
        okButton = new JButton();
        cancelButton = new JButton();
        warningLabel = new JLabel();

        setTitle("Elágazás tulajdonságai");


        definitionLabel.setText("Feltétel");

        descriptionLabel.setText("Megjegyzés");

        descriptionField.setColumns(20);
        descriptionField.setRows(5);
        jScrollPane1.setViewportView(descriptionField);

        okButton.setText("Rendben");

        cancelButton.setText("Mégse");

        warningLabel.setForeground(new java.awt.Color(255, 0, 51));
        warningLabel.setText("Nem lehet üres!");
        warningLabel.setVisible(false);

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(definitionField)
                                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                                        .addGroup(GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(cancelButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(okButton))
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(descriptionLabel))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addComponent(definitionLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(warningLabel)))
                                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGap(18, 18, 18)
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(definitionLabel)
                                        .addComponent(warningLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(definitionField, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(descriptionLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(okButton)
                                        .addComponent(cancelButton))
                                .addContainerGap())
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

}
