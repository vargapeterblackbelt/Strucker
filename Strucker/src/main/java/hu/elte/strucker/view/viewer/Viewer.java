package hu.elte.strucker.view.viewer;

import hu.elte.strucker.controller.controllers.ViewerController;
import hu.elte.strucker.controller.operations.ViewerOperations;
import hu.elte.strucker.model.diagram.Diagram;
import hu.elte.strucker.model.diagram.Loop;
import hu.elte.strucker.model.diagram.Selection;
import hu.elte.strucker.model.diagram.Statement;
import hu.elte.strucker.view.viewer.elements.DiagramViewElement;
import hu.elte.strucker.view.viewer.elements.ViewElement;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static hu.elte.strucker.view.viewer.ViewerHelper.calculateTextHeight;

public class Viewer extends JPanel {
    @Getter
    @Setter
    private Diagram diagram;
    private DiagramViewElement diagramViewElement;
    @Getter
    private ViewElement selectedElement = null;
    private boolean viewNeedsUpdate = true;

    public Viewer(Diagram diagram, ViewerOperations controller) {
        super();
        this.diagram = diagram;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    ViewElement selected = diagramViewElement.in(e.getX(), e.getY());
                    if (selected == null || selected.equals(selectedElement)) {
                        if (selected != null) selected.select(false);
                        selectedElement = null;
                    } else {
                        if (selectedElement != null) {
                            selectedElement.select(false);
                        }
                        selected.select(true);
                        selectedElement = selected;
                    }
                    repaint();
                } else {
                    controller.showMenu();
                }
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        if(viewNeedsUpdate) {
            diagramViewElement = getViewElement(g);
            viewNeedsUpdate = false;
        }
        diagramViewElement.draw(g);
    }

    public DiagramViewElement getViewElement(Graphics g) {
        DiagramViewElement diagramViewElement = new DiagramViewElement(0, 0, 0, calculateTextHeight(g) + 10, diagram, null, g);

        int width = diagramViewElement.calculateWidth();
        int globalHeight = diagramViewElement.getHeight() + 10;
        if (width % 2 != 0) {
            width++;
        }

        diagramViewElement = new DiagramViewElement(
                getWidth() / 2 - width / 2,
                getHeight() / 2 - globalHeight / 2,
                width,
                calculateTextHeight(g) + 10,
                diagram,
                null,
                g);

        return diagramViewElement;
    }

    public void fireViewNeedsUpdate() {
        viewNeedsUpdate = true;
        selectedElement = null;
        repaint();
    }

    public static void main(String[] args) {
        Diagram diagram = new Diagram();
        diagram.add(new Statement("Ezzel kezdődik minden"));
        diagram.add(new Statement("És ezzel meg folytatódik..."));

        Loop loop = new Loop("*insert some condition here*");
        loop.add(new Statement("Réges régen ,egy messzi-messzi galaxisban."));

        Selection selection = new Selection("Ez itt egy elágazási feltétel");
        selection.getThenSequence().add(new Loop("Itt nincs ciklusmag"));
        selection.getElseSequence().add(new Statement("ELSEEEEEEE"));

        Selection selection1 = new Selection("Ez egy másik elágazás");
        selection1.getThenSequence().add(new Statement("statttt"));
        selection1.getThenSequence().add(new Statement("statttt2"));

        selection.getElseSequence().add(selection1);
        loop.add(selection);
        diagram.add(loop);

        JFrame frame = new JFrame();
        Viewer viewer = new Viewer(diagram, new ViewerController(null));
        JScrollPane scrollPane = new JScrollPane(viewer);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.getContentPane().add(scrollPane);
        scrollPane.setViewportView(viewer);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
