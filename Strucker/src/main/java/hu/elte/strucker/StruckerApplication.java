package hu.elte.strucker;

import hu.elte.strucker.controller.Application;
import hu.elte.strucker.controller.controllers.*;
import hu.elte.strucker.controller.operations.*;
import hu.elte.strucker.model.project.Project;
import lombok.Getter;

import javax.swing.*;
import java.awt.event.ActionListener;

public class StruckerApplication implements Application {

    private JFrame window;

    @Getter
    private Project openProject;

    private ApplicationOperations applicationController;
    private ConsoleOperations consoleController;
    private DiagramOperations diagramController;
    private ProjectOperations projectController;
    private ExplorerOperations explorerController;
    private StructogramOperations structogramController;
    private EditorOperations editorController;


    public StruckerApplication() {
        window = new JFrame();
        initControllers();
        initMenu();
    }

    private void initControllers() {
        applicationController = new ApplicationController(this);
        consoleController = new ConsoleController(this);
        diagramController = new DiagramController(this);
        projectController = new ProjectController(this);
        explorerController = new ExplorerController(this);
        structogramController = new StructogramController(this);
        editorController = new EditorController(this);
    }

    private void addMenuItem(JMenu menu, String text, ActionListener l) {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.addActionListener(l);
        menu.add(menuItem);
    }

    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Fájl");
        menuBar.add(fileMenu);
        addMenuItem(fileMenu, "Projekt létrehozása", e -> projectController.create());
        addMenuItem(fileMenu, "Új diagram", e -> diagramController.create());
        addMenuItem(fileMenu, "Új elem", e -> structogramController.insert());
        fileMenu.addSeparator();
        addMenuItem(fileMenu, "Megnyitás", e -> projectController.open());
        addMenuItem(fileMenu, "Mentés", e -> projectController.save());
        addMenuItem(fileMenu, "Mentés másként", e -> {}); // hiányos!
        addMenuItem(fileMenu, "Tulajdonságok", e -> projectController.showProperties());
        addMenuItem(fileMenu, "Bezárás", e -> projectController.close());
        fileMenu.addSeparator();
        addMenuItem(fileMenu, "Kilépés", e -> applicationController.close());

        JMenu editMenu = new JMenu("Szerkesztés");
        menuBar.add(editMenu);
        addMenuItem(editMenu, "Visszavonás", e ->  editorController.undo());
        addMenuItem(editMenu, "Mégis", e -> editorController.redo());
        editMenu.addSeparator();
        addMenuItem(editMenu, "Másolás", e -> editorController.copy());
        addMenuItem(editMenu, "Kivágás", e -> editorController.cut());
        addMenuItem(editMenu, "Beillesztés", e -> editorController.paste());
        addMenuItem(editMenu, "Törlés", e -> editorController.delete());

        JMenu interpreterMenu = new JMenu("Értelmezés");
        menuBar.add(interpreterMenu);
        addMenuItem(interpreterMenu, "Ellenőrzés", e -> projectController.validate());
        addMenuItem(interpreterMenu, "Interpretáció indítása", e -> diagramController.interpret());

        JMenu appMenu = new JMenu("Egyebek");
        menuBar.add(appMenu);
        addMenuItem(appMenu, "Beállítások", e -> applicationController.showSettings());
        addMenuItem(appMenu, "Dokumentáció", e -> applicationController.showDocumentation());
        addMenuItem(appMenu, "Az alkalmazásról", e -> applicationController.showAbout());

        window.setJMenuBar(menuBar);
    }



}
