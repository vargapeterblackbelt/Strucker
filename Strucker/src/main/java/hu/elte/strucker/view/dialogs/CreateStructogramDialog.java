package hu.elte.strucker.view.dialogs;

import hu.elte.strucker.model.diagram.*;
import lombok.Getter;

import javax.swing.*;
import java.awt.Color;
import java.util.ArrayList;

import static hu.elte.strucker.model.diagram.StructogramType.*;

public class CreateStructogramDialog extends StruckerDialog {

    private JButton cancelButton;
    private JTextField definitionField;
    private JLabel definitionLabel;
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
                product = new Loop(text, new ArrayList<>());
            }
            if (typeComboBox.getModel().getSelectedItem().equals(SELECTION)) {
                product = new Selection(text, new Sequence(new ArrayList<>()), new Sequence(new ArrayList<>()));
            }
            setVisible(false);
            dispose();
        }
    }

    private void initComponents() {

        mainPanel = new JPanel();
        typeLabel = new JLabel();
        typeComboBox = new JComboBox<>();
        definitionLabel = new JLabel();
        definitionField = new JTextField();
        okButton = new JButton();
        cancelButton = new JButton();
        warningLabel = new JLabel();

        setTitle("Elem beszúrása");

        typeLabel.setText("Beszúrandó elem típusa");

        typeComboBox.setModel(new DefaultComboBoxModel<>(new StructogramType[] {STATEMENT, LOOP, SELECTION}));
        typeComboBox.addItemListener(e -> {
            if(typeComboBox.getModel().getSelectedItem().equals(STATEMENT)) {
                definitionLabel.setText("Kifejezés megfogalmazása");
            }
            if(typeComboBox.getModel().getSelectedItem().equals(LOOP)) {
                definitionLabel.setText("Ciklus feltétele");
            }
            if(typeComboBox.getModel().getSelectedItem().equals(SELECTION)) {
                definitionLabel.setText("Elágazás feltétele");
            }
        });

        definitionLabel.setText("Definíció");

        okButton.setText("Rendben");

        cancelButton.setText("Mégse");

        warningLabel.setForeground(new Color(255, 0, 51));
        warningLabel.setText("Nem lehet üres!");
        warningLabel.setVisible(false);

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(typeComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(definitionField)
                                        .addGroup(GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(cancelButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(okButton))
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(typeLabel))
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
                                .addComponent(typeLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(typeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(definitionLabel)
                                        .addComponent(warningLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(definitionField, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
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
