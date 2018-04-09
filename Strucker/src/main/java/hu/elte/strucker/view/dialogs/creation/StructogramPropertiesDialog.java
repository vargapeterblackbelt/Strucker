package hu.elte.strucker.view.dialogs.creation;

import hu.elte.strucker.model.diagram.*;
import hu.elte.strucker.view.dialogs.StruckerDialog;

import javax.swing.*;
import java.awt.*;

import static hu.elte.strucker.model.diagram.StructogramType.*;

public class StructogramPropertiesDialog extends StruckerDialog {

    private JButton cancelButton;
    private JTextField definitionField;
    private JLabel definitionLabel;
    private JTextArea descriptionField;
    private JLabel descriptionLabel;
    private JScrollPane jScrollPane1;
    private JPanel mainPanel;
    private JButton okButton;
    private JLabel warningLabel;

    private StructogramType type;
    private Structogram stg;

    public StructogramPropertiesDialog(Structogram stg) {
        super();
        initComponents();
        this.stg = stg;
        type = stg.getType();
        descriptionField.setText(stg.getDescription());
        definitionField.setText(stg.getExploredName());
        okButton.addActionListener(e -> ok());
        cancelButton.addActionListener(e -> cancel());
        warningLabel.setVisible(false);
        if(type == LOOP) {
            definitionLabel.setText("Ciklus feltétele");
        } else {
            if(type == STATEMENT) {
                definitionLabel.setText("Akció megfogalmazása");
            } else {
                if(type == SELECTION) {
                    definitionLabel.setText("Elágazás feltétele");
                } else {
                    definitionLabel.setText("Szekvencia típusának megfogalmazása");
                }
            }
        }
        setModal(true);
        setVisible(true);
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


        setTitle("Elem beszúrása");

        definitionLabel.setText("Definíció");

        descriptionLabel.setText("Megjegyzés");

        descriptionField.setColumns(20);
        descriptionField.setRows(5);
        jScrollPane1.setViewportView(descriptionField);

        okButton.setText("Rendben");

        cancelButton.setText("Mégse");

        warningLabel.setForeground(new Color(255, 0, 51));
        warningLabel.setText("Nem lehet üres!");

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(definitionField)
                                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addComponent(descriptionLabel)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addComponent(definitionLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(warningLabel))
                                        .addGroup(GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(cancelButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(okButton)))
                                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(definitionLabel)
                                        .addComponent(warningLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(definitionField, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(descriptionLabel)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(cancelButton)
                                        .addComponent(okButton))
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

    protected void ok() {
        String text = definitionField.getText().trim();

        if(!descriptionField.getText().isEmpty()) {
            String desc = descriptionField.getText().trim();
            stg.setDescription(descriptionField.getText().trim());
        }
        if(text.isEmpty()) {
            warningLabel.setVisible(true);
        } else {
            if(type == LOOP) {
                ((Loop) stg).setCondition(text);
            } else {
                if(type == STATEMENT) {
                    ((Statement) stg).setStatement(text);
                } else {
                    if(type == SELECTION) {
                        ((Selection) stg).setCondition(text);
                    } else {
                        stg.setLabel(text);
                    }
                }
            }
            cancel();
        }
    }

    protected void cancel() {
        setVisible(false);
        dispose();
    }


    public static void main(String[] args) {
        Statement statement = new Statement("i := 10");
        StructogramPropertiesDialog dialog = new StructogramPropertiesDialog(statement);
        System.out.println(statement == null ? "null" : statement.getExploredName() + "\ndesc: "+statement.getDescription());
    }
}
