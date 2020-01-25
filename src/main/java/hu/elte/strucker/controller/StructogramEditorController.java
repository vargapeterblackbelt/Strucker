package hu.elte.strucker.controller;

import hu.elte.strucker.model.diagram.*;
import hu.elte.strucker.view.dialogs.CreateStructogramDialog;
import hu.elte.strucker.view.dialogs.DiagramPropertiesDialog;
import hu.elte.strucker.view.editor.StructogramEditor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StructogramEditorController implements StructogramEditorOperations {

    private StructogramEditor editor;

    public StructogramEditorController(StructogramEditor editor) {
        this.editor = editor;
    }


    @Override
    public void delete() {
        Structogram selectedStructogram = editor.getSelectedStructogram();
        if (selectedStructogram != null && selectedStructogram.getParent() instanceof Sequence) {
            Sequence sequence = (Sequence) selectedStructogram.getParent();
            sequence.remove(selectedStructogram);
            editor.fireStructogramChanged();
        }
    }

    @Override
    public void insertIntoLoop() {
        Structogram selectedStructogram = editor.getSelectedStructogram();
        if (selectedStructogram instanceof Loop) {
            Loop loop = (Loop) selectedStructogram;
            CreateStructogramDialog dialog = new CreateStructogramDialog(StructogramType.STATEMENT);
            Structogram product = dialog.getProduct();
            if (product != null) {
                loop.add(0, product);
                editor.fireStructogramChanged();
            }
        }
    }

    @Override
    public void insertThen() {
        Structogram selectedStructogram = editor.getSelectedStructogram();
        if (selectedStructogram instanceof Selection) {
            Selection selection = (Selection) selectedStructogram;
            CreateStructogramDialog dialog = new CreateStructogramDialog(StructogramType.STATEMENT);
            Structogram product = dialog.getProduct();
            if (product != null) {
                selection.getThenSequence().add(0, product);
                editor.fireStructogramChanged();
            }
        }
    }

    @Override
    public void insertElse() {
        Structogram selectedStructogram = editor.getSelectedStructogram();
        if (selectedStructogram instanceof Selection) {
            Selection selection = (Selection) selectedStructogram;
            CreateStructogramDialog dialog = new CreateStructogramDialog(StructogramType.STATEMENT);
            Structogram product = dialog.getProduct();
            if (product != null) {
                selection.getThenSequence().add(0, product);
                editor.fireStructogramChanged();
            }
        }
    }

    @Override
    public void insertBefore() {
        Structogram selectedStructogram = editor.getSelectedStructogram();
        if (selectedStructogram != null && selectedStructogram.getParent() instanceof Sequence) {
            Sequence sequence = (Sequence) selectedStructogram.getParent();
            CreateStructogramDialog dialog = new CreateStructogramDialog(StructogramType.STATEMENT);
            Structogram product = dialog.getProduct();
            if (product != null) {
                sequence.add(sequence.indexOf(selectedStructogram) > 0 ? sequence.indexOf(selectedStructogram) : 0, product);
                editor.fireStructogramChanged();
            }
        }
    }

    @Override
    public void insertAfter() {
        Structogram selectedStructogram = editor.getSelectedStructogram();
        if (selectedStructogram != null && selectedStructogram.getParent() instanceof Sequence) {
            Sequence sequence = (Sequence) selectedStructogram.getParent();
            CreateStructogramDialog dialog = new CreateStructogramDialog(StructogramType.STATEMENT);
            Structogram product = dialog.getProduct();
            if (product != null) {
                if (sequence.indexOf(selectedStructogram) + 1 == sequence.size()) {
                    sequence.add(product);
                } else {
                    sequence.add(sequence.indexOf(selectedStructogram) + 1, product);
                }
                editor.fireStructogramChanged();
            }
        }
    }

    @Override
    public void moveUp() {
        Structogram selectedStructogram = editor.getSelectedStructogram();
        if (selectedStructogram != null && selectedStructogram.getParent() instanceof Sequence) {
            Sequence sequence = (Sequence) selectedStructogram.getParent();
            int index = sequence.indexOf(selectedStructogram);
            if (index > 0) {
                sequence.remove(selectedStructogram);
                sequence.add(index - 1, selectedStructogram);
                editor.fireStructogramChanged();
            }
        }
    }

    @Override
    public void moveDown() {
        Structogram selectedStructogram = editor.getSelectedStructogram();
        if (selectedStructogram != null && selectedStructogram.getParent() instanceof Sequence) {
            Sequence sequence = (Sequence) selectedStructogram.getParent();
            int index = sequence.indexOf(selectedStructogram);
            if (index + 1 < sequence.size()) {
                sequence.remove(selectedStructogram);
                sequence.add(index + 1, selectedStructogram);
                editor.fireStructogramChanged();
            }
        }
    }

    @Override
    public void properties() {
        Structogram selectedStructogram = editor.getSelectedStructogram();
        if (selectedStructogram != null) {
            if(selectedStructogram instanceof Diagram) {
                DiagramPropertiesDialog dialog = new DiagramPropertiesDialog((Diagram) selectedStructogram);
                if (!dialog.isCancelled()) {
                    editor.fireStructogramChanged();
                }
            } else {
                String input;
                if(selectedStructogram instanceof Statement) {
                    Statement statement = (Statement) selectedStructogram;
                    input = JOptionPane
                            .showInputDialog((Component) editor, "Állítás megfogalmazása", (statement).getStatement());
                    if(input!= null)statement.setStatement(input);
                } else if (selectedStructogram instanceof Loop) {
                    Loop loop = (Loop) selectedStructogram;
                    input = JOptionPane
                            .showInputDialog((Component) editor, "Feltétel megfogalmazása", loop.getCondition());
                    if(input!= null)loop.setCondition(input);
                } else {
                    Selection selection = (Selection) selectedStructogram;
                    input = JOptionPane
                            .showInputDialog((Component) editor, "Feltétel megfogalmazása", selection.getCondition());
                    if(input!= null)selection.setCondition(input);
                }
                if(input != null) {
                    selectedStructogram.setErrors(new ArrayList<>());
                    editor.fireDiagramChanged();
                }
            }
        }
    }
}
