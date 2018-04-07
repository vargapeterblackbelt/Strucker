package hu.elte.editor.dialogs;

import hu.elte.interpreter.Parameter;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java.awt.event.KeyEvent.VK_ESCAPE;
import static javax.swing.JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT;

public class CreateParameterDialog extends JDialog {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nameField;
    private JTextField typeField;

    @Getter
    private Parameter value = null;

    public CreateParameterDialog(Dialog parent, Parameter value) {
        if(parent != null) {
            setLocation(parent.getLocation());
        }
        setTitle(value == null ? "Új paraméter" : "Paraméter szerkesztése");
        if(value != null) {
            this.value = value;
            nameField.setText(value.getName());
            typeField.setText(value.getType());
        }
        setResizable(false);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(VK_ESCAPE, 0), WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        pack();
        setVisible(true);
    }

    private void onOK() {
        String type = typeField.getText();
        String name = nameField.getText();
        value = type.isEmpty() || name.isEmpty() ? null : new Parameter(name, type);
        value = type.isEmpty() || name.isEmpty() ? null : new Parameter(name, type);
        setVisible(false);
    }

    private void onCancel() {
        value = null;
        setVisible(false);
    }

    public static void main(String[] args) {
        CreateParameterDialog dialog = new CreateParameterDialog(null, null);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

}
