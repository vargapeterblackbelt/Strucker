package hu.elte.editor.menus;

import hu.elte.editor.Application;

import javax.annotation.PostConstruct;
import javax.swing.*;

public class ApplicationMenuBar extends JMenuBar {

    private final Application app;

    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu viewMenu;
    private JMenu runMenu;
    private JMenu helpMenu;
    private JMenuItem newDiagramMenuItem;
    private JMenuItem newProjectMenuItem;
    private JMenuItem openDiagramMenuItem;
    private JMenuItem openProjectMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem saveAsMenuItem;
    private JMenuItem settingsMenuItem;
    private JMenuItem closeMenuItem;
    private JMenuItem exitMenuItem;
    private JMenuItem undoMenuItem;
    private JMenuItem redoMenuItem;
    private JMenuItem copyMenuItem;
    private JMenuItem cutMenuItem;
    private JMenuItem pasteMenuItem;
    private JMenuItem deleteMenuItem;
    private JMenuItem zoomInMenuItem;
    private JMenuItem zoomOutMenuItem;
    private JMenuItem zoomDefaultMenuItem;
    private JMenuItem checkMenuItem;
    private JMenuItem runMenuItem;
    private JMenuItem runDebugMenuItem;
    private JMenuItem stopMenuItem;
    private JMenuItem aboutMenuItem;

    public ApplicationMenuBar(Application app) {
        this.app = app;
        initMenuElements();
        constructMenu();
        addListeners();
    }

    private void initMenuElements() {
        fileMenu = new JMenu("Fájl");
        editMenu = new JMenu("Szerkesztés");
        viewMenu = new JMenu("Nézet");
        runMenu = new JMenu("Futtatás");
        helpMenu = new JMenu("Segítség");

        closeMenuItem = new JMenuItem("Bezárás");
        newDiagramMenuItem = new JMenuItem("Új diagram");
        newProjectMenuItem = new JMenuItem("Új projekt");
        openDiagramMenuItem = new JMenuItem("Diagram megnyitása");
        openProjectMenuItem = new JMenuItem("Project megnyitása");
        saveMenuItem = new JMenuItem("Mentés");
        saveAsMenuItem = new JMenuItem("Mentés másként");
        settingsMenuItem = new JMenuItem("Beállítások");
        exitMenuItem = new JMenuItem("Kilépés");
        undoMenuItem = new JMenuItem("Mégse");
        redoMenuItem = new JMenuItem("Mégis");
        copyMenuItem = new JMenuItem("Másolás");
        cutMenuItem = new JMenuItem("Kivágás");
        pasteMenuItem = new JMenuItem("Beillesztés");
        deleteMenuItem = new JMenuItem("Törlés");
        zoomInMenuItem = new JMenuItem("Nagyítás");
        zoomOutMenuItem = new JMenuItem("Kicsinyítés");
        zoomDefaultMenuItem = new JMenuItem("Alapértelmezett méret");
        checkMenuItem = new JMenuItem("Ellenőrzés");
        runMenuItem = new JMenuItem("Futtatás");
        runDebugMenuItem = new JMenuItem("Futtatás hibakereséssel");
        stopMenuItem = new JMenuItem("Futtatás leállítása");
        aboutMenuItem = new JMenuItem("A programról");
    }

    private void constructMenu() {
        add(fileMenu);
        add(editMenu);
        add(viewMenu);
        add(runMenu);
        add(helpMenu);

        fileMenu.add(newDiagramMenuItem);
        fileMenu.add(newProjectMenuItem);
        fileMenu.add(openDiagramMenuItem);
        fileMenu.add(openProjectMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveAsMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(settingsMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(closeMenuItem);
        fileMenu.add(exitMenuItem);

        editMenu.add(undoMenuItem);
        editMenu.add(redoMenuItem);
        editMenu.addSeparator();
        editMenu.add(copyMenuItem);
        editMenu.add(cutMenuItem);
        editMenu.add(pasteMenuItem);
        editMenu.add(deleteMenuItem);

        viewMenu.add(zoomInMenuItem);
        viewMenu.add(zoomOutMenuItem);
        viewMenu.addSeparator();
        viewMenu.add(zoomDefaultMenuItem);

        runMenu.add(checkMenuItem);
        runMenu.addSeparator();
        runMenu.add(runMenuItem);
        runMenu.add(runDebugMenuItem);
        runMenu.add(stopMenuItem);

        helpMenu.add(aboutMenuItem);
    }

    private void addListeners() {
        newDiagramMenuItem.addActionListener(e -> app.newDiagram());
        newProjectMenuItem.addActionListener(e -> app.newProject());
        openDiagramMenuItem.addActionListener(e -> app.openDiagram());
        openProjectMenuItem.addActionListener(e -> app.openProject());
        saveMenuItem.addActionListener(e -> app.save());
        saveAsMenuItem.addActionListener(e -> app.saveAsProject());
        settingsMenuItem.addActionListener(e -> app.showSettings());
        exitMenuItem.addActionListener(e -> app.exit());
        undoMenuItem.addActionListener(e -> app.undo());
        redoMenuItem.addActionListener(e -> app.redo());
        copyMenuItem.addActionListener(e -> app.copy());
        cutMenuItem.addActionListener(e -> app.cut());
        pasteMenuItem.addActionListener(e -> app.paste());
        deleteMenuItem.addActionListener(e -> app.delete());
        zoomInMenuItem.addActionListener(e -> app.zoomIn());
        zoomOutMenuItem.addActionListener(e -> app.zoomOut());
        zoomDefaultMenuItem.addActionListener(e -> app.zoomDefault());
        checkMenuItem .addActionListener(e -> app.check());
        runMenuItem.addActionListener(e -> app.run());
        runDebugMenuItem.addActionListener(e -> app.runDebug());
        stopMenuItem.addActionListener(e -> app.stop());
        aboutMenuItem.addActionListener(e -> app.about());
        closeMenuItem.addActionListener(e -> app.close());
    }
}
