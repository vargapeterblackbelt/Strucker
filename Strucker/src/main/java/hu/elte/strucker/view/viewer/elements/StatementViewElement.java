package hu.elte.strucker.view.viewer.elements;

import hu.elte.strucker.model.diagram.Statement;

import java.awt.*;

import static hu.elte.strucker.view.viewer.ViewerHelper.calculateTextLength;
import static hu.elte.strucker.view.viewer.ViewerHelper.drawTextLeft;

public class StatementViewElement extends ViewElement<Statement> {

    public StatementViewElement(int x, int y, int width, int height, Statement element, ViewElement parent, Graphics g) {
        super(x, y, width, height, element, parent, g);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(selected ? DEFAULT_SELECTED_COLOR : DEFAULT_COLOR);
        g.fillRect(x, y, width, height);
        g.setColor(Color.black);
        g.drawRect(x, y, width, height);
        g.setColor(DEFAULT_TEXT_COLOR);
        drawTextLeft(element.getStatement(), x+5, y, width, height, g);
    }

    @Override
    public int calculateWidth() {
        return calculateTextLength(element.getStatement(), graphics);
    }

    @Override
    public int getHeight() {
        return height;
    }
}
