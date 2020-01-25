package hu.elte.strucker.view.editor;

import javax.swing.*;
import java.awt.*;

public class ViewProperties {
    public static final int DEFAULT_FONT_SIZE = 32;
    public static final Color DEFAULT_FILL_COLOR = Color.decode("#fff0b3");
    public static final Color DEFAULT_SIGNATURE_COLOR = Color.decode("#e6f7ff");
    public static final Color DEFAULT_SIGNATURE_HOVER_COLOR = Color.decode("#b3e7ff");
    public static final Color DEFAULT_BACKGROUD_COLOR = Color.white;
    public static final Color DEFAULT_ERROR_COLOR = Color.red;//Color.decode("#b30047");
    public static final Color DEFAULT_HOVER_COLOR = Color.decode("#ffdb4d");
    public static final Color DEFAULT_FONT_COLOR = Color.black;
    public static final Font DEFAULT_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, DEFAULT_FONT_SIZE);
    public static final Font DEFAULT_ERROR_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
    public static int fontSize = DEFAULT_FONT_SIZE;
    public static final int MAXIMUM_FONT_SIZE = 64;
    public static final int MINIMUM_FONT_SIZE = 16;
    public static final int GAP = 8;
    public static final int SIGNATURE_GAP = 2;

    public static Font getCurrentFont() {
        return DEFAULT_FONT.deriveFont(Font.PLAIN, fontSize);
    }

    public static int textWidth(String text, Font font) {
        return new JPanel().getFontMetrics(font).stringWidth(text) + textHeight(font);
    }

    public static int textHeight(Font font) {
        return new JPanel().getFontMetrics(font).getHeight();
    }

    public static void drawTextCentered(String txt, int x, int y, int width, int height, Graphics g, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        g.setFont(font);
        g.drawString(txt, x + (width - metrics.stringWidth(txt)) / 2, y + ((height - metrics.getHeight()) / 2) + metrics.getAscent());
    }

    public static void drawTextLeft(String txt, int xx, int yy, int width, int height, Graphics g, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = xx + GAP;
        int y = yy + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(txt, x, y);
    }

//    public static int errorTextHeight() {
//        return new JPanel().getFontMetrics(DEFAULT_ERROR_FONT).getHeight();
//    }
//
//    public static int errorTextWidth(String text) {
//        return new JPanel().getFontMetrics(DEFAULT_ERROR_FONT).stringWidth(text) + textHeight(DEFAULT_ERROR_FONT);
//    }

    public static void drawErrorText(String txt, int xx, int yy, int width, int height, Graphics g) {
        FontMetrics metrics = g.getFontMetrics(DEFAULT_ERROR_FONT);
        int x = xx + GAP;
        int y = yy + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(DEFAULT_ERROR_FONT);
        g.drawString(txt, x, y);
    }
}
