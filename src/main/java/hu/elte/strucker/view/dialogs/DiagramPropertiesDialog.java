package hu.elte.strucker.view.dialogs;

import hu.elte.strucker.model.diagram.Diagram;
import hu.elte.strucker.model.interpretation.Parameter;
import hu.elte.strucker.service.MessageType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import static hu.elte.strucker.service.MessageService.message;

public class DiagramPropertiesDialog extends StruckerDialog {

    private Diagram target;

    private JButton cancelButton;
    private JButton deleteParameter;
    private JTextField diagramNameField;
    private JLabel diagramNameLabel;
    private JLabel diagramTypeLabel;
    private JComboBox<String> diagramTypeBox;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel mainPanel;
    private JButton newParameter;
    private JButton okButton;
    private JTextField paramNameField;
    private JComboBox<String> paramTypeBox;
    private JPanel parametersPanel;
    private JScrollPane parametersScrollPane;
    private JTable parametersTable;

    public DiagramPropertiesDialog(Diagram target) {
        super();
        this.target = target;
        initComponents();
        diagramTypeBox.setSelectedItem(target.getType().getSimpleName());
        setLocationRelativeTo(null);
        DefaultTableModel model = (DefaultTableModel) parametersTable.getModel();
        for (Parameter parameter : target.getParameters()) {
            model.addRow(new Object[]{parameter.getName(), parameter.getType().getSimpleName()});
        }
        okButton.addActionListener(e -> ok());
        cancelButton.addActionListener(e -> cancel());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cancel();
            }
        });

        newParameter.addActionListener(e -> addParameter());
        deleteParameter.addActionListener(e -> deleteParameter());

        setModal(true);
        setVisible(true);
    }

    private void addParameter() {
        String paramName = paramNameField.getText().trim();
        String paramType = (String) paramTypeBox.getSelectedItem();
        if (paramType == null || paramName.isEmpty()) {
            message("Nincsenek kitöltve a megfelelő mezők", MessageType.ERROR);
            return;
        }
        if (!paramName.matches("[a-zA-Z][a-zA-Z0-9]*")) {
            message("Azonosító csak betükből és számokból állhat, valamint nem kezdődhet számmal", MessageType.ERROR);
            return;
        }
        if (target.getParameters().stream().anyMatch(e -> e.getName().equals(paramName))) {
            message("Már foglalt ez a paraméter név", MessageType.ERROR);
            return;
        }
        Class type = paramType.equals("Number") ? Number.class :
                (paramType.equals("String") ? String.class : (paramType.equals("Boolean") ? Boolean.class : List.class));
        Parameter parameter = new Parameter(paramName, type);
        target.getParameters().add(parameter);
        DefaultTableModel model = (DefaultTableModel) parametersTable.getModel();
        model.addRow(new Object[]{paramName, paramType});
        model.fireTableDataChanged();
        paramNameField.setText("");
    }

    private void initComponents() {
        mainPanel = new JPanel();
        diagramNameLabel = new JLabel();
        diagramNameField = new JTextField(target.getName());
        parametersPanel = new JPanel();
        parametersScrollPane = new JScrollPane();
        parametersTable = new JTable() {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        newParameter = new JButton();
        deleteParameter = new JButton();
        paramNameField = new JTextField();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        paramTypeBox = new JComboBox<>();
        diagramTypeLabel = new JLabel();
        okButton = new JButton();
        cancelButton = new JButton();
        diagramTypeBox = new JComboBox<>();

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle("Diagram tulajdonságai");
        setResizable(false);

        mainPanel.setBackground(new Color(255, 255, 255));

        diagramNameLabel.setText("Diagram neve");

        parametersPanel.setBackground(new Color(255, 255, 255));
        parametersPanel.setBorder(BorderFactory.createTitledBorder("Paraméterek"));

        parametersScrollPane.setViewportView(parametersTable);

        newParameter.setText("Hozzáadás");

        deleteParameter.setText("Paraméter törlése");

        paramNameField.setText("");

        jLabel1.setText("Paraméter neve");

        jLabel2.setText("Típusa");

        paramTypeBox.setModel(new DefaultComboBoxModel<>(new String[]{"Number", "String", "Boolean", "List"}));
        parametersTable.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "Név", "Típus"
                }
        ));
        GroupLayout parametersPanelLayout = new GroupLayout(parametersPanel);
        parametersPanel.setLayout(parametersPanelLayout);
        parametersPanelLayout.setHorizontalGroup(
                parametersPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(parametersPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(parametersScrollPane, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(parametersPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(newParameter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(deleteParameter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(paramNameField)
                                        .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2)
                                        .addComponent(paramTypeBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        parametersPanelLayout.setVerticalGroup(
                parametersPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(parametersPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(parametersPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(parametersPanelLayout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(11, 11, 11)
                                                .addComponent(paramNameField, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(paramTypeBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(newParameter)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                                .addComponent(deleteParameter))
                                        .addComponent(parametersScrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap())
        );

        diagramTypeLabel.setText("Diagram típusa");

        okButton.setText("OK");

        cancelButton.setText("Mégse");

        diagramTypeBox.setModel(new DefaultComboBoxModel<>(new String[]{"Number", "String", "Boolean", "List"}));
        diagramTypeBox.setSelectedIndex(0);

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(parametersPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addComponent(diagramNameLabel, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                                .addComponent(diagramTypeLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(361, 361, 361))
                                        .addGroup(GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(cancelButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(okButton))
                                        .addComponent(diagramTypeBox, GroupLayout.Alignment.TRAILING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(diagramNameField))
                                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(diagramNameLabel)
                                .addGap(10, 10, 10)
                                .addComponent(diagramNameField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(diagramTypeLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(diagramTypeBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(parametersPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
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

    private void deleteParameter() {
        int selectedRow = parametersTable.getSelectedRow();
        if (selectedRow != -1) {
            target.getParameters().remove(selectedRow);
            ((DefaultTableModel) parametersTable.getModel()).removeRow(selectedRow);
            ((DefaultTableModel) parametersTable.getModel()).fireTableDataChanged();
        }
    }

    @Override
    protected void ok() {
        String name = diagramNameField.getText().trim();
        if (!name.matches("[a-zA-Z][a-zA-Z0-9]*")) {
            message("Diagram neve csak angol abc betűiből és számokból állhat, valamint nem kezdődhet számmal", MessageType.ERROR);
            return;
        }
        String paramType = (String) diagramTypeBox.getSelectedItem();
        if (paramType == null) {
            message("Nincs kiválasztva visszatérési típus", MessageType.ERROR);
            return;
        }
        Class type = paramType.equals("Number") ? Number.class :
                (paramType.equals("String") ? String.class : (paramType.equals("Boolean") ? Boolean.class : List.class));
        if (name.isEmpty() /*|| target.getLibrary().getDiagrams().stream().filter(e -> e.getName().equals(name)).collect(Collectors.toList()).size()> 1*/) {
            message("Üres nevet nem lehet megadni", MessageType.ERROR);
        } else {
            target.setName(name);
            target.setType(type);
            setVisible(false);
        }
    }
}
