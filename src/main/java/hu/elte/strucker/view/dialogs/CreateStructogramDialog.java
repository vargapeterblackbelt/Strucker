package hu.elte.strucker.view.dialogs;

import hu.elte.strucker.model.diagram.*;
import lombok.Getter;

import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.util.ArrayList;

import static hu.elte.strucker.model.diagram.StructogramType.*;
import static javax.swing.GroupLayout.*;
import static javax.swing.GroupLayout.Alignment.TRAILING;

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


        typeLabel.setFont(FONT);
        definitionLabel.setFont(FONT);
        warningLabel.setFont(FONT);
        definitionField.setFont(FONT);
        typeComboBox.setFont(FONT);

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

        this.add(mainPanel);

        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(typeComboBox, 0, DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(typeLabel)
                                                        .addComponent(definitionLabel))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(definitionField)
                                        .addGroup(TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 211, Short.MAX_VALUE)
                                                .addComponent(cancelButton)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(okButton)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(typeLabel)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(typeComboBox, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(definitionLabel)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(definitionField, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(cancelButton)
                                        .addComponent(okButton))
                                .addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

}
