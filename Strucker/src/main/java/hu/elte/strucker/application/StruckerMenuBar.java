package hu.elte.strucker.application;

import hu.elte.strucker.model.diagram.Diagram;
import hu.elte.strucker.model.project.Library;
import hu.elte.strucker.model.project.Project;
import hu.elte.strucker.view.menus.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StruckerMenuBar extends JMenuBar implements ApplicationSelection {

    private List<ApplicationMenu> menus = new ArrayList<>();

    public StruckerMenuBar(Application app) {
        super();

        FileMenu fileMenu = new FileMenu(app);
        EditMenu editMenu = new EditMenu(app);
        ViewMenu viewMenu = new ViewMenu(app);
        AnalyzeMenu analyzeMenu = new AnalyzeMenu(app);

        add(fileMenu);
        add(editMenu);
        add(viewMenu);
        add(analyzeMenu);

        menus.add(fileMenu);
        menus.add(editMenu);
        menus.add(viewMenu);
        menus.add(analyzeMenu);
    }

    @Override
    public void fireProjectSelected(Project project) {
        for (ApplicationMenu menu : menus) {
            menu.fireProjectSelected(project);
        }
    }

    @Override
    public void fireDiagramSelected(Diagram diagram) {
        for (ApplicationMenu menu : menus) {
            menu.fireDiagramSelected(diagram);
        }
    }

    @Override
    public void fireLibrarySelected(Library library) {
        for (ApplicationMenu menu : menus) {
            menu.fireLibrarySelected(library);
        }
    }

    @Override
    public void fireNothingSelected() {
        for (ApplicationMenu menu : menus) {
            menu.fireNothingSelected();
        }
    }
}
