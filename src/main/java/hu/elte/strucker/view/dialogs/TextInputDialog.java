package hu.elte.strucker.view.dialogs;

import javax.swing.*;
import java.awt.*;

public class TextInputDialog extends JDialog {

    private static final Font FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 18);

    public static String getTextInput(String title, String labelText, String initText) {
        JTextArea textArea = new JTextArea(initText);
        TextInputDialog textInputDialog = new TextInputDialog(title, labelText, textArea);
        return textArea.getText();
    }

    private final JLabel label;
    private final JScrollPane textScrollPane;

    private final JButton okButton;
    private final JButton cancelButton;

    private final String original;

    public TextInputDialog(String title, String labelText, JTextArea textArea) {
        super();
        original = textArea.getText();
        setTitle(title);
        setLocationRelativeTo(null);
        label = new JLabel(labelText);
        label.setFont(FONT);
        textArea.setFont(FONT);
        textScrollPane = new javax.swing.JScrollPane();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        textArea.setColumns(20);
        textArea.setRows(5);
        textScrollPane.setViewportView(textArea);

        okButton.setText("OK");

        okButton.addActionListener(
                e -> {
                    this.setVisible(false);
                    this.dispose();
                }
        );

        cancelButton.setText("Mégse");
        cancelButton.addActionListener(
                e -> {
                    this.setVisible(false);
                    textArea.setText(original);
                    this.dispose();
                }
        );

        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(textScrollPane)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(label)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 198, Short.MAX_VALUE)
                                                .addComponent(cancelButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(okButton)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(okButton)
                                        .addComponent(cancelButton))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setModal(true);
        pack();

        setVisible(true);
    }

}
