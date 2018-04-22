package hu.elte.strucker.view.dialogs.creation;

import hu.elte.strucker.model.diagram.*;
import hu.elte.strucker.view.dialogs.StruckerDialog;
import lombok.Getter;

import javax.swing.*;

import static hu.elte.strucker.model.diagram.StructogramType.*;

public class CreateStructogramDialog extends StruckerDialog {

    private JButton cancelButton;
    private JTextField definitionField;
    private JLabel definitionLabel;
    private JTextArea descriptionField;
    private JLabel descriptionLabel;
    private JScrollPane jScrollPane1;
    private JPanel mainPanel;
    private JButton okButton;
    private JComboBox<StructogramType> typeComboBox;
    private JLabel typeLabel;
    private JLabel warningLabel;

    @Getter
    private Structogram product;

    public CreateStructogramDialog(StructogramType type) {
        super();
        product = null;
        initComponents();
        setLocationRelativeTo(null);
        typeComboBox.getModel().setSelectedItem(type);
        okButton.addActionListener(e -> ok());
        cancelButton.addActionListener(e -> cancel());
        setModal(true);
        setVisible(true);
    }

    protected void ok() {
        String text = definitionField.getText().trim();
        if(text.isEmpty()) {
            warningLabel.setVisible(true);
        } else {
            if (typeComboBox.getModel().getSelectedItem().equals(STATEMENT)) {
                product = new Statement(text);
            }
            if (typeComboBox.getModel().getSelectedItem().equals(LOOP)) {
                product = new Loop(text);
            }
            if (typeComboBox.getModel().getSelectedItem().equals(SELECTION)) {
                product = new Selection(text);
            }
            String description = descriptionField.getText().trim();
            if(!description.isEmpty()) {
                product.setDescription(description);
            }
            cancel();
        }
    }

    private void initComponents() {

        mainPanel = new JPanel();
        typeLabel = new JLabel();
        typeComboBox = new JComboBox<>();
        definitionLabel = new JLabel();
        definitionField = new JTextField();
        descriptionLabel = new JLabel();
        jScrollPane1 = new JScrollPane();
        descriptionField = new JTextArea();
        okButton = new JButton();
        cancelButton = new JButton();
        warningLabel = new JLabel();

        setTitle("Elem beszúrása");

        typeLabel.setText("Beszúrandó elem típusa");

        typeComboBox.setModel(new DefaultComboBoxModel<>(new StructogramType[] {STATEMENT, LOOP, SELECTION}));
        typeComboBox.addItemListener(e -> {
            if(typeComboBox.getModel().getSelectedItem().equals(STATEMENT)) {
                definitionLabel.setText("Akció megfogalmazása");
            }
            if(typeComboBox.getModel().getSelectedItem().equals(LOOP)) {
                definitionLabel.setText("Ciklus feltétele");
            }
            if(typeComboBox.getModel().getSelectedItem().equals(SELECTION)) {
                definitionLabel.setText("Elágazás feltétele");
            }
        });

        definitionLabel.setText("Definíció");

        descriptionLabel.setText("Megjegyzés");

        descriptionField.setColumns(20);
        descriptionField.setRows(5);
        jScrollPane1.setViewportView(descriptionField);

        okButton.setText("Rendben");

        cancelButton.setText("Mégse");

        warningLabel.setForeground(new java.awt.Color(255, 0, 51));
        warningLabel.setText("Nem lehet üres!");
        warningLabel.setVisible(false);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(typeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(definitionField)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(cancelButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(okButton))
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(typeLabel)
                                                        .addComponent(descriptionLabel))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addComponent(definitionLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(warningLabel)))
                                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(typeLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(definitionLabel)
                                        .addComponent(warningLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(definitionField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(descriptionLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(okButton)
                                        .addComponent(cancelButton))
                                .addContainerGap())
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

    public static void main(String[] args) {
        CreateStructogramDialog dialog = new CreateStructogramDialog(STATEMENT);
        Structogram stg = dialog.getProduct();
        System.out.println(stg == null ? "null" : stg.getExploredName());
    }
}
