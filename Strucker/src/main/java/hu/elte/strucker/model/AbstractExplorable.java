package hu.elte.strucker.model;

import lombok.Getter;
import lombok.Setter;

import javax.swing.tree.TreeNode;
import java.util.List;


@Setter
public abstract class AbstractExplorable<T> implements Explorable {

    public AbstractExplorable() {

    }

    @Getter
    private String label;
    private String description;

    @Override
    public boolean hasChilds() {
        return getChilds().size() > 0;
    }

    public String getExploredName() {
        return label == null ? UNKNOWN_LABEL : label;
    }

    @Override
    public String getDescription() {
        return description == null ? UNKNOWN_LABEL : description;
    }

    public abstract List<T> getChilds();
    public abstract TreeNode getTree();
}
