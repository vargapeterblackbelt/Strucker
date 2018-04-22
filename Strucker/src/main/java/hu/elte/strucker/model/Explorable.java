package hu.elte.strucker.model;

import javax.swing.tree.TreeNode;
import java.util.List;

public interface Explorable<T> {
    String UNKNOWN_LABEL = "Unknown";

    boolean hasChilds();
    List<T> getChilds();
    TreeNode getTree();
    String getExploredName();
    String getDescription();
}
