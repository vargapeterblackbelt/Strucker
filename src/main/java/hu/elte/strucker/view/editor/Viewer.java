package hu.elte.strucker.view.editor;

import hu.elte.strucker.application.Application;
import hu.elte.strucker.controller.StructogramEditorController;
import hu.elte.strucker.controller.StructogramEditorOperations;
import hu.elte.strucker.model.diagram.*;
import hu.elte.strucker.service.ResourceManager;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelListener;

import static hu.elte.strucker.controller.StructogramEditorOperations.*;
import static hu.elte.strucker.view.editor.ViewProperties.*;
import static java.lang.Math.abs;

public class Viewer extends JPanel implements StructogramEditor {

    @Getter
    @Setter
    private Diagram diagram;

    private int fontSize = DEFAULT_FONT_SIZE;
    private Font font = DEFAULT_FONT.deriveFont(Font.PLAIN, fontSize);

    @Getter
    private Structogram selectedElement = null;

    Point initialClick;
    private StructogramEditorOperations controller;
    private Application app;

    private int positionX = 0;
    private int positionY = 0;

    public Viewer(Diagram diagram, Application app) {
        super();
        this.app = app;
        setBackground(DEFAULT_BACKGROUD_COLOR);
        controller = new StructogramEditorController(this);
        this.diagram = diagram;
        positionX = getWidth()/2 + diagram.widthNeeded(font)/2;
        positionY = 30;
        addMouseWheelListener(getMouseWheelListener());
        addMouseMotionListener(getMouseMotionAdapter());
        addMouseListener(getMouseAdapter());
    }

    private MouseWheelListener getMouseWheelListener() {
        return (e -> {
            if (e.getWheelRotation() > 0 && fontSize > MINIMUM_FONT_SIZE) {
                fontSize = fontSize - 4;
                font = DEFAULT_FONT.deriveFont(Font.PLAIN, fontSize);
            }
            if (e.getWheelRotation() < 0 && fontSize < MAXIMUM_FONT_SIZE) {
                fontSize += 4;
                font = DEFAULT_FONT.deriveFont(Font.PLAIN, fontSize);
            }
            repaint();
        });
    }

    private MouseMotionAdapter getMouseMotionAdapter() {
        return new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Structogram in = diagram.in(e.getX(), e.getY());
                diagram.hover(false);
                if (in != null) {
                    in.hover(true);
                }
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (diagram.in(initialClick.x, initialClick.y) != null) {
                    int x = e.getX() - initialClick.x;
                    int y = e.getY() - initialClick.y;
                    if (abs(x) > 10 || abs(y) > 10) {
                        positionX += x;
                        positionY += y;
                        initialClick.setLocation(e.getX(), e.getY());
                        repaint();
                    }
                }
            }
        };
    }

    private MouseAdapter getMouseAdapter() {
        return new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                selectedElement = diagram.in(e.getX(), e.getY());
                if (selectedElement != null) {
                    if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                        controller.properties();
                    } else {
                        if(selectedElement instanceof Diagram) {
                            getDiagramMenu().show((Component) e.getSource(),
                                    e.getX(),
                                    e.getY());
                        } else {
                            getStructogramMenu().show((Component) e.getSource(),
                                    e.getX(),
                                    e.getY());
                        }
                    }
                }
            }
        };
    }

    private JPopupMenu getDiagramMenu() {
        JPopupMenu menu = new JPopupMenu();
        JLabel label = new JLabel("Diagram");
        JPanel panel = new JPanel();
        panel.add(label);
        menu.add(panel);
        panel.setBackground(Color.lightGray);
        menu.setBorder(BorderFactory.createRaisedBevelBorder());

        JMenuItem properties = new JMenuItem("Szerkesztés", propertiesIcon);
        properties.addActionListener(e -> controller.properties());
        menu.add(properties);


        JMenuItem check = new JMenuItem("Ellenőrzés", checkIcon);
        check.addActionListener(e -> app.getDiagramController().check((Diagram) selectedElement));
        menu.add(check);

        JMenuItem execute = new JMenuItem("Futtatás", ResourceManager.getIcon("images/menu/execute.png"));
        execute.addActionListener(e -> app.getDiagramController().execute((Diagram) selectedElement));
        menu.add(execute);

        menu.addSeparator();

        JMenuItem close = new JMenuItem("Szerkesztő bezárása", ResourceManager.getIcon("images/menu/close.png"));
        close.addActionListener(e -> app.fireDiagramClosing(diagram));
        menu.add(close);

        return menu;
    }

    private JPopupMenu getStructogramMenu() {
        ImageIcon icon = new ImageIcon("icons/node_can_expand.png");
        JPopupMenu menu = new JPopupMenu();
        JLabel label = new JLabel();
        JPanel panel = new JPanel();
        panel.add(label);
        menu.add(panel);
        panel.setBackground(Color.lightGray);
        menu.setBorder(BorderFactory.createRaisedBevelBorder());

        JMenuItem properties = new JMenuItem("Szerkesztés", propertiesIcon);
        properties.addActionListener(e -> controller.properties());
        menu.add(properties);

        menu.addSeparator();

        JPanel insertPanel = new JPanel();
        insertPanel.add(new JLabel("Beszúrás"));
        menu.add(insertPanel);

        if (selectedElement instanceof Selection) {
            label.setText("Elágazás");
            JMenuItem insertThen = new JMenuItem("Igaz ágba", insertIcon);
            insertThen.addActionListener(e -> controller.insertThen());
            menu.add(insertThen);

            JMenuItem insertElse = new JMenuItem("Hamis ágba", insertIcon);
            insertElse.addActionListener(e -> controller.insertElse());
            menu.add(insertElse);
        } else {
            if (selectedElement instanceof Loop) {
                label.setText("Ciklus");
                JMenuItem insertIntoLoop = new JMenuItem("Ciklus magjába", insertIcon);
                insertIntoLoop.addActionListener(e -> controller.insertIntoLoop());
                menu.add(insertIntoLoop);
            } else {
                label.setText("Kifejezés");
            }
        }

        JMenuItem insertBefore = new JMenuItem("Elé", insertIcon);
        insertBefore.addActionListener(e -> controller.insertBefore());
        menu.add(insertBefore);

        JMenuItem insertAfter = new JMenuItem("Mögé", insertIcon);
        insertAfter.addActionListener(e -> controller.insertAfter());
        menu.add(insertAfter);

        menu.addSeparator();

        JMenuItem delete = new JMenuItem("Törlés", deleteIcon);
        delete.addActionListener(e -> controller.delete());
        menu.add(delete);

        menu.addSeparator();


        JMenuItem moveUp = new JMenuItem("Fel", upIcon);
        moveUp.addActionListener(e -> controller.moveUp());
        menu.add(moveUp);


        JMenuItem moveDown = new JMenuItem("Le", downIcon);
        moveDown.addActionListener(e -> controller.moveDown());
        menu.add(moveDown);

        Sequence sequence = selectedElement.getParentSequence();

        if(sequence == null) {
            moveUp.setEnabled(false);
            moveDown.setEnabled(false);
        } else {
            if (sequence.indexOf(selectedElement) == 0) {
                moveUp.setEnabled(false);
            }
            if(sequence.indexOf(selectedElement) == sequence.size()-1) {
                moveDown.setEnabled(false);
            }
        }

        return menu;
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        if(diagram != null) {
            diagram.draw(g, positionX, positionY, diagram.widthNeeded(font), 0, font, false);
        }
    }

    @Override
    public void fireStructogramChanged() {
        selectedElement = null;
        fireDiagramChanged();
        repaint();
    }

    @Override
    public void fireDiagramChanged() {
        selectedElement = null;
        app.fireDiagramChanged(diagram);
        repaint();
    }

    @Override
    public Application getApp() {
        return app;
    }

    @Override
    public Structogram getSelectedStructogram() {
        return selectedElement;
    }

    public int getTextWidth(String txt) {
        return getFontMetrics(getFont()).stringWidth(txt);
    }

    public void zoomIn() {
        fontSize = 48;
        repaint(100);
    }

    public void zoomOut() {
        fontSize = 16;
        repaint(100);
    }
}
