package hu.elte.model.structogram;

import hu.elte.model.Explorable;

import javax.swing.tree.TreeNode;
import java.util.List;

public abstract class Structogram implements Explorable<Structogram> {

    protected Explorable parent;

    public Structogram(Explorable parent) {
        this.parent = parent;
    }

    public Structogram() {
        parent = null;
    }

    @Override
    public boolean hasParent() {
        return parent != null;
    }

    @Override
    public Explorable getParent() {
        return parent;
    }

    public void setParent(Explorable parent) {
        this.parent = parent;
    }

    @Override
    public boolean hasChilds() {
        return getChilds().size() == 0;
    }

    @Override
    public abstract List<Structogram> getChilds();

    @Override
    public abstract TreeNode getTree();

    @Override
    public String toString() {
        return getExploredName();
    }
}
