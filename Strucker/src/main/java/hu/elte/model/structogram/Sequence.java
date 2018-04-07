package hu.elte.model.structogram;

import hu.elte.model.Explorable;
import lombok.Setter;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class Sequence extends Structogram {

    protected List<Structogram> sequence;

    @Setter
    private String sequenceType = "seqence";

    public Sequence(Explorable parent) {
        super(parent);
        init();
    }

    public Sequence() {
        super();
        init();
    }

    private void init() {
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

    @Override
    public String getExploredName() {
        return sequenceType;
    }

    public void add(Structogram child) {
        sequence.add((Structogram) child);
        child.setParent(this);
    }

    public void add(int index, Structogram child) {
        sequence.add(index, child);
        child.setParent(this);
    }

    public void addBefore(Structogram child, Structogram newChild) {
        sequence.add(sequence.indexOf(child), newChild);
        newChild.setParent(this);
    }

    public void addAfter(Structogram child, Structogram newChild) {
        sequence.add(sequence.indexOf(child)+1, newChild);
        newChild.setParent(this);
    }

    public void remove(Structogram child) {
        sequence.remove(child);
    }

    public void remove(int index) {
        sequence.remove(index);
    }

    public void setChilds(List<Structogram> childs) {
        for (Structogram child : childs) {
            child.setParent(this);
        }
        this.sequence = childs;
    }

    public void clear() {
        sequence.clear();
    }
}
