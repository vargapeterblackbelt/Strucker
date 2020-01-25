package hu.elte.strucker.view.editor;

import hu.elte.strucker.model.diagram.Loop;
import hu.elte.strucker.model.diagram.Selection;
import hu.elte.strucker.model.diagram.Statement;
import hu.elte.strucker.model.diagram.Structogram;

import java.awt.*;

public class ViewerHelper {
    public static final Font DEFAULT_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 18);
    public static int FONT_SIZE = 18;
    public static final int NATURAL_TEXT_GAP = 20;

    private static Font getFont() {
        return DEFAULT_FONT.deriveFont(Font.PLAIN, FONT_SIZE);
    }

    public static int calculateTextLength(String txt, Graphics g) {
        FontMetrics metrics = g.getFontMetrics(getFont());
        return metrics.stringWidth(txt) + NATURAL_TEXT_GAP;
    }

    public static int calculateTextHeight(Graphics g) {
        assert g != null;
        FontMetrics metrics = g.getFontMetrics(getFont());
        return metrics.getHeight();
    }

    public static void drawTextCentered(String txt, int xx, int yy, int width, int height, Graphics g) {
        FontMetrics metrics = g.getFontMetrics(getFont());
        int x = xx + (width - metrics.stringWidth(txt)) / 2;
        int y = yy + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(getFont());
        g.drawString(txt, x, y);
    }

    public static void drawTextLeft(String txt, int xx, int yy, int width, int height, Graphics g) {
        FontMetrics metrics = g.getFontMetrics(getFont());
        int x = xx;
        int y = yy + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
//        g.drawLine(x, y, x + calculateTextLength(txt, g), y);
        g.setFont(getFont());
        g.drawString(txt, x, y);
    }

}
