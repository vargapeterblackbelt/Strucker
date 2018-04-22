package hu.elte.strucker.view.explorer;

import hu.elte.strucker.model.diagram.Diagram;

import javax.swing.*;
import java.awt.*;

public class StructogramExplorer extends JPanel {

    private Diagram diagram;

    public StructogramExplorer(Diagram diagram) {
        super();
        this.diagram = diagram;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
