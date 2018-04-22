package hu.elte.strucker.view.explorer.node;

import java.awt.*;

public interface ExplorerNode<T> {
    void draw();
    boolean inside(Point position);
    void expand(boolean recursively);
    void collapse(boolean recursively);
    boolean isCollapsed(boolean recursively);
    T getContainedObject();
}
