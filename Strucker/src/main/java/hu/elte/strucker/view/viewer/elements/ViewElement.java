package hu.elte.strucker.view.viewer.elements;

import hu.elte.strucker.model.diagram.Structogram;
import lombok.Getter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class ViewElement<T extends Structogram> {
    public static Color DEFAULT_COLOR = Color.WHITE;
    public static Color DEFAULT_SELECTED_COLOR = Color.CYAN;
    public static Color DEFAULT_TEXT_COLOR = Color.BLACK;

    protected int x;
    protected int y;
    @Getter
    protected int width;
    protected int height;

    @Getter
    protected boolean selected;

    protected ViewElement parentElement;
    protected T element;
    protected Graphics graphics;

    protected List<ViewElement> subElements;

    public ViewElement(int x, int y, int width, int height, T element, ViewElement parentElement, Graphics graphics) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.element = element;
        this.parentElement = parentElement;
        subElements = new ArrayList<>();
        selected = false;
        this.graphics = graphics;
    }

    public ViewElement in(int x, int y) {
        if(this.x < x && this.x+width > x && this.y < y && this.y + height > y) {
            return this;
        } else {
            for (ViewElement subElement : subElements) {
                ViewElement in = subElement.in(x, y);
                if(in != null) {
                    return in;
                }
            }
        }
        return null;
    }

    public void select(boolean isSelected) {
        selected = isSelected;
        for (ViewElement subElement : subElements) {
            subElement.select(isSelected);
        }
    }

    public abstract void draw(Graphics g);
    public abstract int calculateWidth();
    public abstract int getHeight();

}
