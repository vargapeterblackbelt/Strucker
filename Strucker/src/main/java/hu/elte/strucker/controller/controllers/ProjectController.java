package hu.elte.strucker.controller.controllers;

import hu.elte.strucker.controller.Application;
import hu.elte.strucker.controller.operations.ProjectOperations;
import hu.elte.strucker.helper.ObjectLoader;
import hu.elte.strucker.model.project.Project;
import hu.elte.strucker.view.dialogs.ProjectPropertiesDialog;
import org.codehaus.jackson.map.ObjectMapper;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.IOException;

import static hu.elte.strucker.helper.ObjectLoader.deserialize;
import static hu.elte.strucker.helper.ObjectLoader.serialize;

public class ProjectController extends Controller implements ProjectOperations {

    public ProjectController(Application app) {
        super(app);
    }

    @Override
    public void create() {
        Project project = new Project("Új projekt");
        ProjectPropertiesDialog dialog = new ProjectPropertiesDialog(project, true);
        dialog.showDialog();
        if (!dialog.isCancelled()) {
            app.getOpenProjects().add(project);
            app.addToExplorer((DefaultMutableTreeNode) app.getExplorer().getModel().getRoot(), project.getTree());
        }
    }

    @Override
    public void open() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Strucker projekt", "strucker"));
        fileChooser.setDialogTitle("Strucker projekt megnyitása");
        fileChooser.showOpenDialog(app.getWindow());
        if (fileChooser.getSelectedFile() != null) {
            Project project = (Project) deserialize(fileChooser.getSelectedFile().getPath(), Project.class);
            app.getOpenProjects().add(project);
            app.addToExplorer((DefaultMutableTreeNode) app.getExplorer().getModel().getRoot(), project.getTree());
        }
    }

    @Override
    public void close() {
        Object selectedItem = app.getSelectedNode().getUserObject();
        if (selectedItem != null && selectedItem instanceof Project) {
            Project selected = (Project) selectedItem;
            app.getOpenProjects().remove(selected);
            app.removeFromExplorer(app.getSelectedNode());
        } else {
            System.out.println("Bezárni csak projektet lehet");
        }
    }

    @Override
    public void save() {
        Object selectedItem = app.getSelectedNode().getUserObject();
        if (selectedItem != null && selectedItem instanceof Project) {
            Project project = (Project) selectedItem;
            Object saved = serialize(project.getLocation() + "/" + project.getName() + ".strucker", project);
            if (saved != null) {
                System.out.println("Sikeresen elmentve");
            }
        } else {
            System.out.println("Nincs megfelelő elem kiválasztva");
        }
    }

    @Override
    public void saveAs() {
        throw new UnsupportedOperationException("project.saveAs");
    }

    @Override
    public void showProperties() {
        Object selectedItem = app.getSelectedNode().getUserObject();
        if (selectedItem != null && selectedItem instanceof Project) {
            Project selected = (Project) selectedItem;
            ProjectPropertiesDialog dialog = new ProjectPropertiesDialog(selected, false);
            dialog.showDialog();
            if (!dialog.isCancelled()) {
                ((DefaultTreeModel) app.getExplorer().getModel()).reload(selected.getTree());
            }
        } else {
            System.out.println("Nem project");
        }
    }

    @Override
    public void validate() {
        throw new UnsupportedOperationException("project.validate");
    }
}
