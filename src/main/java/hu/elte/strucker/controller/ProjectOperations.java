package hu.elte.strucker.controller;

import hu.elte.strucker.model.project.Project;

public interface ProjectOperations {
    void create();
    void open();
    void createLibrary(Project project);
    void close(Project project);
    void save(Project project);
    void showProperties(Project project);
    void delete(Project project);
}
