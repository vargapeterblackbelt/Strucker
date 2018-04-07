package hu.elte.editor.dialogs;

import hu.elte.interpreter.Parameter;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ParameterTableModel extends AbstractTableModel {

    private List<Parameter> parameters = new ArrayList<>();
    private String[] columnNames = {"Név", "Típus"};

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return parameters.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Parameter parameter = parameters.get(rowIndex);
        return columnIndex == 0 ? parameter.getName() : parameter.getType();
    }

    public void add(Parameter parameter) {
        parameters.add(parameter);
        fireTableDataChanged();
    }

    public Parameter get(int row) {
        return parameters.get(row);
    }

    public void remove(int row) {
        parameters.remove(row);
        fireTableDataChanged();
    }

    public void set(int row, Parameter parameter) {
        parameters.set(row, parameter);
        fireTableDataChanged();
    }
}
