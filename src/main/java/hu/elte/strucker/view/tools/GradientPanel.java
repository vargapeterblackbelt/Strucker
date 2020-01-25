package hu.elte.strucker.view.tools;

import javax.swing.*;
import java.awt.*;

public class GradientPanel extends JPanel {

    private Color from;
    private Color to;
    public GradientPanel(Color colorFrom, Color colorTo) {
        from = colorFrom;
        to = colorTo;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp = new GradientPaint(0, 0,
                from, 0, getHeight(),
                to);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}
