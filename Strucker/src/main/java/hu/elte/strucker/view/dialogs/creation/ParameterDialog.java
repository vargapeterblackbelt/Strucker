package hu.elte.strucker.view.dialogs.creation;

import hu.elte.strucker.model.interpreter.Parameter;
import hu.elte.strucker.view.dialogs.StruckerDialog;
import lombok.Getter;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java.awt.event.KeyEvent.VK_ESCAPE;
import static javax.swing.JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT;

public class ParameterDialog extends StruckerDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nameField;
    private JTextField typeField;

    @Getter
    private Parameter value = null;

    public ParameterDialog(JDialog parent, Parameter value) {
        setLocationRelativeTo(null);
        setTitle(value == null ? "Új paraméter" : "Paraméter szerkesztése");
        if (value != null) {
            this.value = value;
            nameField.setText(value.getName());
            typeField.setText(value.getType());
        }
        setResizable(false);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        buttonOK.addActionListener(e -> ok());
        buttonCancel.addActionListener(e -> cancel());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                cancel();
            }
        });
        contentPane.registerKeyboardAction(e -> cancel(), KeyStroke.getKeyStroke(VK_ESCAPE, 0), WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        ParameterDialog dialog = new ParameterDialog(null, null);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    @Override
    protected void ok() {
        String type = typeField.getText();
        String name = nameField.getText();
        value = type.isEmpty() || name.isEmpty() ? null : new Parameter(name, type);
        value = type.isEmpty() || name.isEmpty() ? null : new Parameter(name, type);
        setVisible(false);
    }
}
