package hu.elte.strucker.controller;

import hu.elte.strucker.model.project.Project;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;

public interface Application {
    void run();
    void exit();
    JTree getExplorer();
    DefaultMutableTreeNode getSelectedNode();
    List<Project> getOpenProjects();
    JFrame getWindow();
}
