package hu.elte.strucker.model.diagram;

import hu.elte.strucker.model.interpreter.StructogramInterpreter;
import hu.elte.strucker.model.interpreter.StructogramValidator;
import hu.elte.strucker.model.interpreter.processed.ProcessedStructogram;
import lombok.Getter;
import lombok.Setter;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class Sequence extends Structogram {

    @Getter
    @Setter
    private List<Structogram> sequence;

    public Sequence() {
        super();
        sequence = new ArrayList<>();
    }

    @Override
    public List<Structogram> getChilds() {
        return sequence;
    }

    @Override
    public TreeNode getTree() {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(this);
        for (Structogram stg : sequence) {
            DefaultMutableTreeNode stgNode = (DefaultMutableTreeNode) stg.getTree();
            node.add(stgNode);
        }
        return node;
    }

    public void add(Structogram child) {
        sequence.add(child);
    }

    public void add(int index, Structogram child) {
        sequence.add(index, child);
    }

    public void addBefore(Structogram child, Structogram newChild) {
        sequence.add(sequence.indexOf(child), newChild);
    }

    public void addAfter(Structogram child, Structogram newChild) {
        sequence.add(sequence.indexOf(child)+1, newChild);
    }

    public int indexOf(Structogram child) {
        return sequence.indexOf(child);
    }

    public void move(Structogram child, int index) {
        if(sequence.contains(child)) {
            sequence.remove(child);
            sequence.add(index, child);
        }
    }

    public void remove(Structogram child) {
        sequence.remove(child);
    }

    public void remove(int index) {
        sequence.remove(index);
    }

    public void clear() {
        sequence.clear();
    }

    @Override
    public boolean validate(StructogramValidator validator) {
        for (Structogram structogram : sequence) {
            if(!structogram.validate(validator)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ProcessedStructogram interpret(StructogramInterpreter interpreter) {
        return null;
    }

}
