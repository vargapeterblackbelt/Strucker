package hu.elte.strucker.view.explorer.node;

import java.awt.*;
import java.util.List;

public abstract class AbstractExplorerNode<T> implements ExplorerNode<T> {

    private boolean collapsed;
    private Point position;
    private T object;
    private List<ExplorerNode<T>> childs;

    public AbstractExplorerNode(T object, Point position, List<ExplorerNode<T>> childs) {
        collapsed = true;
        this.object = object;
        this.position = position;
        this.childs = childs;
    }


    @Override
    public void expand(boolean recursively) {
        collapsed = false;
        if(recursively) {
            childs.forEach(e -> e.expand(true));
        }
    }

    @Override
    public void collapse(boolean recursively) {
        collapsed = true;
        if(recursively) {
            childs.forEach(e -> e.collapse(true));
        }
    }

    @Override
    public boolean isCollapsed(boolean recursively) {
        return recursively ? childs.stream().allMatch(e -> e.isCollapsed(true)): collapsed;
    }

    @Override
    public T getContainedObject() {
        return object;
    }
}
