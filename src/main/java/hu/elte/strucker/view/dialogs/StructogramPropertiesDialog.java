package hu.elte.strucker.view.dialogs;

import hu.elte.strucker.model.diagram.*;
import hu.elte.strucker.view.dialogs.StruckerDialog;

import javax.swing.*;
import java.awt.*;

import static hu.elte.strucker.model.diagram.StructogramType.*;

public class StructogramPropertiesDialog extends StruckerDialog {

    private JButton cancelButton;
    private JTextField definitionField;
    private JLabel definitionLabel;
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
        okButton.addActionListener(e -> ok());
        cancelButton.addActionListener(e -> cancel());
        warningLabel.setVisible(false);
        if(stg instanceof Loop) {
            definitionLabel.setText("Ciklus feltétele");
            definitionField.setText(((Loop)stg).getCondition());
        } else if (stg instanceof Statement) {
            definitionLabel.setText("Akció megfogalmazása");
            definitionField.setText(((Statement) stg).getStatement());
        } else if (stg instanceof Selection) {
            definitionLabel.setText("Elágazás feltétele");
            definitionField.setText(((Selection) stg).getCondition());
        } else definitionLabel.setText("Szekvencia típusának megfogalmazása");
        setLocationRelativeTo(null);
        setModal(true);
        setVisible(true);
    }

    private void initComponents() {

        mainPanel = new JPanel();
        definitionLabel = new JLabel();
        definitionField = new JTextField();
        jScrollPane1 = new JScrollPane();
        okButton = new JButton();
        cancelButton = new JButton();
        warningLabel = new JLabel();


        setTitle("Elem tulajdonságia");

        definitionLabel.setText("Definíció");

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

        if(text.isEmpty()) warningLabel.setVisible(true);
        else {
            if(type == LOOP) ((Loop) stg).setCondition(text);
            else if (type == STATEMENT) ((Statement) stg).setStatement(text);
            else if (type == SELECTION) ((Selection) stg).setCondition(text);
            setVisible(false);
            dispose();
        }
    }

}
