package hu.elte.model;

import javax.swing.tree.TreeNode;
import java.util.List;

public interface Explorable<T> {
    Explorable getParent();
    void setParent(Explorable parent);
    boolean hasParent();
    boolean hasChilds();
    List<T> getChilds();
    TreeNode getTree();
    String getExploredName();
}
