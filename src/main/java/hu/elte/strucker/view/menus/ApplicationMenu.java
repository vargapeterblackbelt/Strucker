package hu.elte.strucker.view.menus;

import hu.elte.strucker.application.Application;
import hu.elte.strucker.model.diagram.Diagram;
import hu.elte.strucker.model.project.Library;
import hu.elte.strucker.model.project.Project;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class ApplicationMenu extends JMenu {
    protected Application app;

    public ApplicationMenu(Application app, String title) {
        super(title);
        this.app = app;
    }

    public abstract void fireProjectSelected(Project project);
    public abstract void fireDiagramSelected(Diagram diagram);
    public abstract void fireLibrarySelected(Library library);
    public abstract void fireNothingSelected();

    protected void disable(JMenuItem menuItem) {
        for (ActionListener actionListener : menuItem.getActionListeners()) {
            menuItem.removeActionListener(actionListener);
        }
        menuItem.setEnabled(false);
    }

    protected void setup(JMenuItem menuItem, ActionListener listener) {
        for (ActionListener actionListener : menuItem.getActionListeners()) {
            menuItem.removeActionListener(actionListener);
        }
        menuItem.addActionListener(listener);
        menuItem.setEnabled(true);
    }

}
