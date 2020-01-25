package hu.elte.strucker.model.diagram;

import java.awt.*;

public interface StructogramView {
    void draw(Graphics g, int x, int y, int width, int balanceHeight, Font font, boolean capture);
    int widthNeeded(Font font);
    void hover(boolean isHovered);
    int heightNeeded(Font font);
    int countLeafs();
    Structogram in(int x, int y);
}
