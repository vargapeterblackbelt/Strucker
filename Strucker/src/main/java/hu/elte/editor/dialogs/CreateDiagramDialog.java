package hu.elte.editor.dialogs;

import hu.elte.interpreter.Parameter;
import hu.elte.model.project.Diagram;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java.awt.event.KeyEvent.VK_ESCAPE;
import static javax.swing.JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT;
import static javax.swing.JFileChooser.APPROVE_OPTION;
import static javax.swing.ListSelectionModel.SINGLE_SELECTION;

public class CreateDiagramDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nameField;
    private JTextField typeField;
    private JTextField locationField;
    private JButton browseButton;
    private JTextArea descriptionField;
    private JButton addParameterButton;
    private JButton deleteParameterButton;
    private JLabel nameLabel;
    private JLabel typeLabel;
    private JLabel locationLabel;
    private JTable parameterTable;
    private ParameterTableModel parameters = new ParameterTableModel();
    private JScrollPane parameterScroller;
    private JButton editButton;

    private JFileChooser directoryChooser = new JFileChooser();

    @Getter
    private Diagram diagram;

    public CreateDiagramDialog() {
        directoryChooser.setAcceptAllFileFilterUsed(false);
        directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        parameterTable.setModel(parameters);
        parameterTable.setSelectionMode(SINGLE_SELECTION);
        setContentPane(contentPane);
        contentPane.setPreferredSize(new Dimension(400, 400));
        setResizable(false);
        setModal(true);
        setTitle("Új diagram létrehozása");
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());
        browseButton.addActionListener(e -> browseDirectory());
        addParameterButton.addActionListener(e -> addParameter());
        deleteParameterButton.addActionListener(e -> deleteParameter());
        editButton.addActionListener(e -> editParameter());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(VK_ESCAPE, 0), WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        String name = nameField.getText();
        String type = typeField.getText();
        String description = descriptionField.getText();
        String location = locationField.getText();

        diagram = new Diagram(name, type, null);
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    private void browseDirectory() {
        if(directoryChooser.showOpenDialog(this) == APPROVE_OPTION) {
            locationField.setText(directoryChooser.getSelectedFile().getPath());
        }
    }

    private void addParameter() {
        CreateParameterDialog dialog = new CreateParameterDialog(this, null);
        Parameter parameter = dialog.getValue();

        if(parameter != null) {
            parameters.add(parameter);
        }
    }

    private void editParameter() {
        int row = parameterTable.getSelectedRow();
        if(row == -1) {
            return;
        }
        Parameter parameter = parameters.get(row);
        CreateParameterDialog dialog = new CreateParameterDialog(this, parameter);
        parameter = dialog.getValue();

        if(parameter != null) {
            parameters.set(row, parameter);
        }
    }

    private void deleteParameter() {
        int row = parameterTable.getSelectedRow();
        if(row != -1) {
            parameters.remove(row);
        }
    }

    public static void main(String[] args) {
        CreateDiagramDialog dialog = new CreateDiagramDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

}
