package hu.elte.strucker.model;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.swing.tree.TreeNode;
import java.util.List;


@Getter
@Setter
public abstract class AbstractExplorable<T> implements Explorable {

    public AbstractExplorable() {
        parent = null;
        label = "";
        description = "";
    }

    @JsonIgnore
    private Explorable parent;
    @JsonIgnore
    private String label;
    private String description;

    @Override
    @JsonIgnore
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

    @JsonIgnore
    public abstract List<T> getChilds();

    @JsonIgnore
    public abstract TreeNode getTree();

    @JsonIgnore
    public String getExploredName() {
        return label == null ? UNKNOWN_LABEL : label;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
