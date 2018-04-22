package hu.elte.strucker.view.viewer;

import hu.elte.strucker.model.diagram.Loop;
import hu.elte.strucker.model.diagram.Selection;
import hu.elte.strucker.model.diagram.Statement;
import hu.elte.strucker.model.diagram.Structogram;
import hu.elte.strucker.view.viewer.elements.LoopViewElement;
import hu.elte.strucker.view.viewer.elements.SelectionViewElement;
import hu.elte.strucker.view.viewer.elements.StatementViewElement;
import hu.elte.strucker.view.viewer.elements.ViewElement;

import java.awt.*;
import java.io.File;

public class ViewerHelper {
    public static final Font DEFAULT_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 18);
    public static int FONT_SIZE = 18;

    public static Font CONSOLAS;

    static {
        File fontFile = new File("fonts/Consolas.ttf");
        try {
            CONSOLAS = Font.createFont(Font.TRUETYPE_FONT,fontFile);
        } catch (Exception e) {
            e.printStackTrace();
            CONSOLAS = new Font(Font.MONOSPACED, Font.PLAIN, 18);
        }
    }

    private static Font getFont() {
        return DEFAULT_FONT.deriveFont(Font.PLAIN, FONT_SIZE);
    }

    public static int calculateTextLength(String txt, Graphics g) {
        FontMetrics metrics = g.getFontMetrics(DEFAULT_FONT);
        return metrics.stringWidth(txt) + DEFAULT_FONT.getSize()*3;
    }

    public static int calculateTextHeight(Graphics g) {
        assert g != null;
        FontMetrics metrics = g.getFontMetrics(DEFAULT_FONT);
        return metrics.getHeight();
    }

    public static void drawTextCentered(String txt, int xx, int yy, int width, int height, Graphics g) {
        FontMetrics metrics = g.getFontMetrics(getFont());
        int x = xx + (width - metrics.stringWidth(txt)) / 2;
        int y = yy + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(DEFAULT_FONT);
        g.drawString(txt, x, y);
    }

    public static void drawTextLeft(String txt, int xx, int yy, int width, int height, Graphics g) {
        FontMetrics metrics = g.getFontMetrics(getFont());
        int x = xx;
        int y = yy + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(DEFAULT_FONT);
        g.drawString(txt, x, y);
    }

    public static ViewElement makeStructogramViewElement
            (int x, int y, int width, int height, Structogram stg, ViewElement parentElement, Graphics graphics, int heightNeeded) {
        ViewElement viewElement = null;
        if(stg instanceof Statement) {
            viewElement = new StatementViewElement(x, y + heightNeeded, width, height, (Statement) stg, parentElement, graphics);
        } else {
            if(stg instanceof Loop) {
                viewElement = new LoopViewElement(x, y + heightNeeded, width, height, (Loop) stg, parentElement, graphics);
            } else {
                if(stg instanceof Selection) {
                    viewElement = new SelectionViewElement(x, y + heightNeeded, width, height, (Selection) stg, parentElement, graphics);
                }
            }
        }
        assert viewElement != null;
        return viewElement;
    }
}
