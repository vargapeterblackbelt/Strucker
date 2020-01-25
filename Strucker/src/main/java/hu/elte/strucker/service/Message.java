package hu.elte.strucker.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
@AllArgsConstructor
public class Message {
    public static final Font MSG_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
    private int alpha;
    private String msg;
    private Color color;
    private int x;
    private int y;
    private int width;
    private int height;

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(Color.white);
        drawTextLeft(msg, x, y, width, height, g);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }

    private void drawTextLeft(String txt, int xx, int yy, int width, int height, Graphics g) {
        FontMetrics metrics = g.getFontMetrics(MSG_FONT);
        int x = xx;
        int y = yy + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(MSG_FONT);
        g.drawString(txt, x, y);
    }

}
