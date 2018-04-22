package hu.elte.strucker.view.viewer.elements;

import hu.elte.strucker.model.diagram.Selection;
import hu.elte.strucker.model.diagram.Structogram;
import lombok.AllArgsConstructor;

import java.awt.*;

import static hu.elte.strucker.view.viewer.ViewerHelper.*;

public class SelectionViewElement extends ViewElement<Selection> {
    public static final int LINE_GAP = 10;

    private int globalHeight;
    private FillerView filler = null;

    public SelectionViewElement(int x, int y, int width, int height, Selection element, ViewElement parentElement, Graphics graphics) {
        super(x, y, width, height, element, parentElement, graphics);
        int heightNeeded = height;
        for (int i = 0; i < element.getThenSequence().getChilds().size(); i++) {
            Structogram stg = element.getThenSequence().getChilds().get(i);
            ViewElement viewElement = makeStructogramViewElement(x, y, this.width/2, height, stg, this, graphics, heightNeeded);
            subElements.add(viewElement);
            heightNeeded += viewElement.getHeight();
        }
        int elseHeightNeeded = height;
        for (int i = 0; i < element.getElseSequence().getChilds().size(); i++) {
            Structogram stg = element.getElseSequence().getChilds().get(i);
            ViewElement viewElement = makeStructogramViewElement(x+this.width/2, y, this.width/2, height, stg, this, graphics, elseHeightNeeded);
            subElements.add(viewElement);
            elseHeightNeeded += viewElement.getHeight();
        }
        if(elseHeightNeeded != heightNeeded) {
            if(elseHeightNeeded > heightNeeded) {
                filler = new FillerView(x, y + heightNeeded, elseHeightNeeded - heightNeeded, this.width / 2);
            } else {
                filler = new FillerView(x+this.width/2, y + elseHeightNeeded, heightNeeded - elseHeightNeeded, this.width / 2);
            }
        }
        if(elseHeightNeeded > heightNeeded) {
            heightNeeded = elseHeightNeeded;
        }
        globalHeight = heightNeeded;
    }

    @AllArgsConstructor
    private class FillerView {
        private int x;
        private int y;
        private int height;
        private int width;
        public void draw(Graphics g) {
            g.drawLine(x, y, x + width, y + height);
            g.drawLine(x + width, y, x, y + height);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(selected ? DEFAULT_SELECTED_COLOR : DEFAULT_COLOR);
        g.fillRect(x, y, width, globalHeight);
        g.setColor(Color.black);
        g.drawRect(x, y, width, globalHeight);
        g.drawRect(x, y, width, height);
        g.setColor(DEFAULT_TEXT_COLOR);
        g.drawLine(x, y+(height/2), x+LINE_GAP, y+height);
        g.drawLine(x+width, y+(height/2), x+width-LINE_GAP, y+height);
        if(filler != null) {
            filler.draw(g);
        }
        drawTextCentered(element.getCondition(), x, y, width, height, g);
        for (ViewElement subElement : subElements) {
            subElement.draw(g);
        }
    }

    @Override
    public int calculateWidth() {
        int max = calculateTextLength(element.getCondition(), graphics);
        for (ViewElement subElement : subElements) {
            int w = subElement.calculateWidth() / 2;
            if(w > max) {
                max = w;
            }
        }
        return max;
    }

    @Override
    public int getHeight() {
        return globalHeight;
    }

}
