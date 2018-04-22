package hu.elte.strucker.view.viewer.elements;

import hu.elte.strucker.model.diagram.Loop;
import hu.elte.strucker.model.diagram.Structogram;
import hu.elte.strucker.view.viewer.ViewerHelper;

import java.awt.*;

import static hu.elte.strucker.view.viewer.ViewerHelper.calculateTextLength;
import static hu.elte.strucker.view.viewer.ViewerHelper.drawTextLeft;

public class LoopViewElement extends ViewElement<Loop> {
    public static final int LOOP_GAP = 10;

    public LoopViewElement(int x, int y, int width, int height, Loop element, ViewElement parentElement, Graphics graphics) {
        super(x, y, width, height, element, parentElement, graphics);
//        checkWidth();
        int heightNeeded = height;
        for (int i = 0; i < element.getChilds().size(); i++) {
            Structogram stg = element.getChilds().get(i);
            ViewElement viewElement = ViewerHelper.makeStructogramViewElement(x + LOOP_GAP, y, this.width - LOOP_GAP, height, stg, this, graphics, heightNeeded);
            subElements.add(viewElement);
            heightNeeded += viewElement.getHeight();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(selected ? DEFAULT_SELECTED_COLOR : DEFAULT_COLOR);
        g.fillRect(x, y, width, getHeight());
        g.setColor(Color.black);
        g.drawRect(x, y, width, getHeight());
        g.setColor(DEFAULT_TEXT_COLOR);
        drawTextLeft(element.getCondition(), x+LOOP_GAP, y, width, height, g);
        for (ViewElement subElement : subElements) {
            subElement.draw(g);
        }
    }

    @Override
    public int calculateWidth() {
        int max = calculateTextLength(element.getCondition(), graphics);
        for (ViewElement subElement : subElements) {
            int w = subElement.calculateWidth() - LOOP_GAP;
            if(w > max) {
                max = w;
            }
        }
        return max;
    }

    @Override
    public int getHeight() {
        int h = height;
        for (ViewElement subElement : subElements) {
            h += subElement.getHeight();
        }
        return h;
    }
}
