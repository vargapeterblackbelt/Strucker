package hu.elte.strucker.view.viewer.elements;

import hu.elte.strucker.model.diagram.Diagram;
import hu.elte.strucker.model.diagram.Structogram;

import java.awt.*;

import static hu.elte.strucker.view.viewer.ViewerHelper.makeStructogramViewElement;

public class DiagramViewElement extends ViewElement<Diagram> {

    public DiagramViewElement(int x, int y, int width, int height, Diagram element, ViewElement parentElement, Graphics graphics) {
        super(x, y, width, height, element, parentElement, graphics);
        int heightNeeded = 0;
        for (int i = 0; i < element.getChilds().size(); i++) {
            Structogram stg = element.getChilds().get(i);
            ViewElement viewElement = makeStructogramViewElement(x, y, this.width, height, stg, this, graphics, heightNeeded);
            subElements.add(viewElement);
            heightNeeded += viewElement.getHeight();
        }
    }

    @Override
    public void draw(Graphics g) {
        for (ViewElement subElement : subElements) {
            subElement.draw(g);
        }
    }

    @Override
    public ViewElement in(int x, int y) {
        for (ViewElement subElement : subElements) {
            ViewElement in = subElement.in(x, y);
            if (in != null) {
                return in;
            }
        }
        return null;
    }

    @Override
    public int calculateWidth() {
        int max = 0;
        for (ViewElement subElement : subElements) {
            int w = subElement.calculateWidth();
            if(w > max) {
                max = w;
            }
        }
        return max;
    }

    @Override
    public int getHeight() {
        int h = 0;
        for (ViewElement subElement : subElements) {
            h += subElement.getHeight();
        }
        return h;
    }

}
