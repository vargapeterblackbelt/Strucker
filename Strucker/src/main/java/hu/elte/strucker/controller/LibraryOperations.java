package hu.elte.strucker.controller;

import hu.elte.strucker.model.project.Library;
import hu.elte.strucker.model.project.Project;

public interface LibraryOperations {
    void create(Project project);
    void remove(Library library);
    void rename(Library library);
    void createDiagram(Library library);
    void importDiagram(Library library);
    void delete(Library library);
}
