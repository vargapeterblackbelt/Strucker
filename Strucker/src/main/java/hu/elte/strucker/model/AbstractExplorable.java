package hu.elte.strucker.model;

import lombok.Setter;

import javax.swing.tree.TreeNode;
import java.util.List;

public abstract class AbstractExplorable<T> implements Explorable {

    public AbstractExplorable() {
        parent = null;
        label = null;
        description = null;
    }

    private Explorable parent;

    @Setter
    private String label;

    @Setter
    private String description;

    @Override
    public Explorable getParent() {
        return parent;
    }

    @Override
    public void setParent(Explorable parent) {
        this.parent = parent;
    }

    @Override
    public boolean hasChilds() {
        return getChilds().size() > 0;
    }

    public abstract List<T> getChilds();

    public abstract TreeNode getTree();

    public String getExploredName() {
        return label == null ? UNKNOWN_LABEL : label;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
