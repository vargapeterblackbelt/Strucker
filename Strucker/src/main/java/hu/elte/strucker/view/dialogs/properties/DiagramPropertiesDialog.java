package hu.elte.strucker.view.dialogs.properties;

import hu.elte.strucker.model.diagram.Diagram;
import hu.elte.strucker.model.interpreter.Parameter;
import hu.elte.strucker.view.UIProperties;
import hu.elte.strucker.view.dialogs.StruckerDialog;
import hu.elte.strucker.view.tools.GradientPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DiagramPropertiesDialog extends StruckerDialog {

    private Diagram target;

    private JButton cancelButton;
    private JButton deleteParameter;
    private JTextArea descriptionField;
    private JLabel descriptionLabel;
    private JScrollPane descriptionScroll;
    private JTextField diagramNameField;
    private JLabel diagramNameLabel;
    private JTextField diagramTypeField;
    private JLabel diagramTypeLabel;
    private JPanel mainPanel;
    private JButton newParameter;
    private JButton okButton;
    private JPanel parametersPanel;
    private JScrollPane parametersScrollPane;
    private JTable parametersTable;

    public DiagramPropertiesDialog(Diagram target) {
        super();
        this.target = target;
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        initComponents();
        setLocationRelativeTo(null);
        descriptionField.setText(target.getDescription());
        diagramTypeField.setText(target.getReturnType());
        diagramNameField.setText(target.getName());
        parametersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        for (Parameter parameter : target.getParameters()) {
            ((DefaultTableModel)parametersTable.getModel())
                    .addRow(new String[]{parameter.getName(), parameter.getType()});
        }
        ((DefaultTableModel)parametersTable.getModel()).fireTableDataChanged();
        newParameter.addActionListener(e -> newParameter());
        okButton.addActionListener(e -> ok());
        cancelButton.addActionListener(e -> cancel());
        deleteParameter.addActionListener(e -> deleteParameter());

        setModal(true);
        setVisible(true);
    }

    private void initComponents() {

        mainPanel = new GradientPanel(Color.white, UIProperties.gardientPanelColor);
        diagramNameLabel = new JLabel();
        diagramNameField = new JTextField();
        parametersPanel = new JPanel();
        parametersScrollPane = new JScrollPane();
        parametersTable = new JTable();
        newParameter = new JButton();
        deleteParameter = new JButton();
        diagramTypeLabel = new JLabel();
        diagramTypeField = new JTextField();
        okButton = new JButton();
        cancelButton = new JButton();
        descriptionLabel = new JLabel();
        descriptionScroll = new JScrollPane();
        descriptionField = new JTextArea();

        setTitle("Diagram tulajdonságai");
        setResizable(false);

        mainPanel.setBackground(new Color(255, 255, 255));
        parametersPanel.setOpaque(true);

        diagramNameLabel.setText("Diagram neve");

        parametersPanel.setBackground(new Color(255, 255, 255));
        parametersPanel.setBorder(BorderFactory.createTitledBorder("Paraméterek"));

        parametersTable.setModel(new DefaultTableModel(
                new String [][] {
                },
                new String [] {
                        "Név", "Típus"
                }
        ));

        parametersScrollPane.setViewportView(parametersTable);

        newParameter.setText("Új paraméter");

        deleteParameter.setText("Paraméter törlése");

        GroupLayout parametersPanelLayout = new GroupLayout(parametersPanel);
        parametersPanel.setLayout(parametersPanelLayout);
        parametersPanelLayout.setHorizontalGroup(
                parametersPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(parametersPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(parametersScrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(parametersPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(deleteParameter, GroupLayout.Alignment.TRAILING)
                                        .addComponent(newParameter, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        parametersPanelLayout.setVerticalGroup(
                parametersPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(parametersPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(parametersPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(parametersPanelLayout.createSequentialGroup()
                                                .addComponent(newParameter)
                                                .addGap(18, 18, 18)
                                                .addComponent(deleteParameter)
                                                .addGap(0, 50, Short.MAX_VALUE))
                                        .addComponent(parametersScrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap())
        );

        diagramTypeLabel.setText("Diagram típusa");

        okButton.setText("OK");

        cancelButton.setText("Mégse");

        descriptionLabel.setText("Megjegyzés");

        descriptionField.setColumns(20);
        descriptionField.setRows(5);
        descriptionScroll.setViewportView(descriptionField);

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(parametersPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(diagramNameField)
                                        .addComponent(diagramTypeField)
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(diagramNameLabel)
                                                        .addComponent(diagramTypeLabel)
                                                        .addComponent(descriptionLabel))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(descriptionScroll, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                                        .addGroup(GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(cancelButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(okButton)))
                                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(diagramNameLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(diagramNameField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(diagramTypeLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(diagramTypeField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(parametersPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(descriptionLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(descriptionScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(okButton)
                                        .addComponent(cancelButton))
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

    private void newParameter() {
        ((DefaultTableModel)parametersTable.getModel()).addRow(new String[]{"Új paraméter neve", "Új paraméter típusa"});
        ((DefaultTableModel)parametersTable.getModel()).fireTableDataChanged();
    }

    private void deleteParameter() {
        int selectedRow = parametersTable.getSelectedRow();
        if(selectedRow != -1) {
            ((DefaultTableModel) parametersTable.getModel()).removeRow(selectedRow);
            ((DefaultTableModel)parametersTable.getModel()).fireTableDataChanged();
        }
    }

    @Override
    protected void ok() {
        String name = diagramNameField.getText().trim();
        String description = descriptionField.getText().trim();
        String type = diagramTypeField.getText().trim();
        List<Parameter> parameters = new ArrayList<>();
        if(name.isEmpty() || type.isEmpty()) {
            System.out.println("Név és típus nem lehet üres!");
        } else {
            target.setName(name);
            target.setDescription(description);
            target.setReturnType(type);
            for(int i = 0; i < parametersTable.getModel().getRowCount(); i++) {
                parameters.add(new Parameter(
                        (String) parametersTable.getModel().getValueAt(i, 0),
                        (String) parametersTable.getModel().getValueAt(i, 1)
                ));
            }
            target.setParameters(parameters);
            setVisible(false);
        }
    }
}
