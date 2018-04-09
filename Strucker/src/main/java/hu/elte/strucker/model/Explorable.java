package hu.elte.strucker.model;

import lombok.NonNull;

import javax.swing.tree.TreeNode;
import java.util.List;

public interface Explorable<T> {
    String UNKNOWN_LABEL = "Unknown";

    Explorable getParent();
    void setParent(@NonNull Explorable parent);
    boolean hasChilds();
    List<T> getChilds();
    TreeNode getTree();
    String getExploredName();
    String getDescription();
}
