package hu.elte.strucker.controller;

import hu.elte.strucker.application.Application;
import hu.elte.strucker.model.ProjectStatus;
import hu.elte.strucker.model.project.Library;
import hu.elte.strucker.model.project.Project;
import hu.elte.strucker.view.dialogs.ProjectPropertiesDialog;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import static hu.elte.strucker.model.ObjectLoader.deserialize;
import static hu.elte.strucker.model.ObjectLoader.serialize;
import static hu.elte.strucker.service.MessageService.message;
import static hu.elte.strucker.service.MessageType.ERROR;
import static hu.elte.strucker.service.MessageType.SUCCESS;
import static javax.swing.JOptionPane.*;

public class ProjectController implements ProjectOperations {

    private Application app;

    public ProjectController(Application app) {
        this.app = app;
    }

    @Override
    public void create() {
        Library mainLibrary = new Library("Main", new ArrayList<>(), true);
        Project project = new Project("Projekt", "", new ArrayList<>(Collections.singletonList(mainLibrary)));
        ProjectPropertiesDialog dialog = new ProjectPropertiesDialog(project, true);
        if (!dialog.isCancelled()) {
            save(project);
            app.fireProjectOpened(project);
        }
    }

    @Override
    public void open() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Strucker projekt: .strucker", "strucker"));
        fileChooser.setDialogTitle("Strucker projekt megnyitása");
        fileChooser.showOpenDialog(app.getFrame());
        if (fileChooser.getSelectedFile() != null) {
            String path = fileChooser.getSelectedFile().getPath();
            Project project = deserialize(path, Project.class);
            if(project == null) {
                message("Hiba a projekt megnyitása közben.", ERROR);
                return;
            }
            project.setLocation(path);
            app.fireProjectOpened(project);
        }
    }

    @Override
    public void createLibrary(Project project) {
        app.getLibraryController().create(project);
    }

    @Override
    public void close(Project project) {
        if (project != null) {
            app.fireProjectClosed(project);
        }
    }

    @Override
    public void save(Project project) {
        if (serialize(project.getLocation(), project) != null) {
            project.setStatus(ProjectStatus.SAVED);
            message("Sikeresen mentve", SUCCESS);
        } else {
            message("Nem sikerült a mentés", ERROR);
        }
    }


    @Override
    public void showProperties(Project project) {
        ProjectPropertiesDialog dialog = new ProjectPropertiesDialog(project, false);
        if (!dialog.isCancelled()) {
            app.fireProjectChanged(project);
        }
    }

    @Override
    public void delete(Project project) {
        int option = JOptionPane
                .showConfirmDialog(app.getFrame(), "Törli a projektet a lemezről?", "Törlés megerősítése", YES_NO_OPTION, WARNING_MESSAGE);
        if(option == YES_OPTION) {
            File file = new File(project.getLocation());
            boolean delete = file.delete();
            if(delete) {
                message("Sikeresen törölve", SUCCESS);
                app.fireProjectClosed(project);
            } else {
                message("Hiba a törlés közben", ERROR);
            }
        }
    }

}
