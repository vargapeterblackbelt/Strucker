package hu.elte.strucker.application;

import hu.elte.strucker.controller.*;
import hu.elte.strucker.model.diagram.Diagram;
import hu.elte.strucker.model.project.Library;
import hu.elte.strucker.model.project.Project;

import javax.swing.*;

public interface Application {
    void run();
    void exit();
    ProjectOperations getProjectController();
    DiagramOperations getDiagramController();
    ExplorerOperations getExplorerController();
    LibraryOperations getLibraryController();
    JFrame getFrame();
    void fireAllProjectClosing();
    void fireAllProjectSaving();
    void fireNothingSelected();
    void fireProjectSelected(Project project);
    void fireLibrarySelected(Library library);
    void fireDiagramSelected(Diagram diagram);
    void fireProjectOpened(Project project);
    void fireProjectClosed(Project project);
    void fireProjectChanged(Project project);
    void fireLibraryInserted(Project project, Library library);
    void fireLibraryChanged(Library library);
    void fireLibraryRemoved(Library library);
    void fireDiagramInserted(Library library, Diagram diagram);
    void fireDiagramRemoved(Diagram diagram);
    void fireDiagramOpening(Diagram diagram);
    void fireDiagramClosing(Diagram diagram);
    void fireDiagramChanged(Diagram diagram);
    void fireDiagramChecked(Diagram diagram);
    void fireExplorerCollapsing();
    void fireExplorerExpanding();
    boolean isDiagramOpen(Diagram diagram);
    void fireZoomIn(Diagram diagram);
    void fireZoomOut(Diagram diagram);
    void fireCloseAllTabs();
}
