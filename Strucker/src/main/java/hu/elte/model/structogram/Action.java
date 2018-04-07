package hu.elte.model.structogram;

import hu.elte.model.Explorable;
import lombok.Getter;
import lombok.Setter;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class Action extends Structogram {
    public static final String SKIP = "SKIP";

    @Getter @Setter
    private String action;

    public Action(Explorable parent, String action) {
        super(parent);
        init(action);
    }

    public Action(String action) {
        super();
        init(action);
    }

    private void init(String action) {
        this.action = action;
    }

    @Override
    public boolean hasChilds() {
        return false;
    }

    @Override
    public List<Structogram> getChilds() {
        return new ArrayList<>();
    }

    @Override
    public TreeNode getTree() {
        return new DefaultMutableTreeNode(this);
    }

    @Override
    public String getExploredName() {
        return action;
    }

}
