package hu.elte.strucker.controller.controllers;

import hu.elte.strucker.controller.Application;
import hu.elte.strucker.controller.operations.ProjectOperations;
import hu.elte.strucker.helper.TreeHelper;
import hu.elte.strucker.model.project.Project;
import hu.elte.strucker.view.dialogs.properties.ProjectPropertiesDialog;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import static hu.elte.strucker.helper.Naming.qualifiedPath;
import static hu.elte.strucker.helper.ObjectLoader.deserialize;
import static hu.elte.strucker.helper.ObjectLoader.serialize;
import static hu.elte.strucker.helper.TreeHelper.*;

public class ProjectController extends Controller implements ProjectOperations {

    public ProjectController(Application app) {
        super(app);
    }

    @Override
    public void create() {
        Project project = new Project("Új projekt");
        ProjectPropertiesDialog dialog = new ProjectPropertiesDialog(project, true);
        if (!dialog.isCancelled()) {
            app.getOpenProjects().add(project);
            insert(app.getExplorer(), project.getTree(), (DefaultMutableTreeNode) app.getExplorer().getModel().getRoot());
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
            Project project = deserialize(fileChooser.getSelectedFile().getPath(), Project.class);
            app.getOpenProjects().add(project);
            insert(app.getExplorer(), project.getTree(), (DefaultMutableTreeNode) app.getExplorer().getModel().getRoot());
        }
    }

    @Override
    public void importDiagram() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() {
        Project project = extract(app.getSelectedNode(), Project.class);
        if(project != null) {
            app.getOpenProjects().remove(project);
            remove(app.getExplorer(), app.getSelectedNode());
        }
    }

    @Override
    public void save() {
        Project project = extract(app.getSelectedNode(), Project.class);
        if(project != null) {
            if(serialize(qualifiedPath(project), project) != null) {
                System.out.println("Mentve");
            }
        }
    }

    @Override
    public void saveAs() {
        throw new UnsupportedOperationException("project.saveAs");
    }

    @Override
    public void showProperties() {
        Project project = extract(app.getSelectedNode(), Project.class);
        if(project != null) {
            ProjectPropertiesDialog dialog = new ProjectPropertiesDialog(project, false);
            if (!dialog.isCancelled()) {
                app.getExplorer().repaint();
//                reload(app.getExplorer(), project.getTree());
            }
        }

        /*Object selectedItem = app.getSelectedNode().getUserObject();
        if (selectedItem != null && selectedItem instanceof Project) {
            Project selected = (Project) selectedItem;
            ProjectPropertiesDialog dialog = new ProjectPropertiesDialog(selected, false);
            if (!dialog.isCancelled()) {
                ((DefaultTreeModel) app.getExplorer().getModel()).reload(selected.getTree());
            }
        } else {
            System.out.println("Nem project");
        }*/
    }

    @Override
    public void validate() {
        throw new UnsupportedOperationException("project.validate");
    }
}
