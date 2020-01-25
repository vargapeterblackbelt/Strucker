package hu.elte.strucker.application;

import hu.elte.strucker.model.diagram.Diagram;
import hu.elte.strucker.model.project.Library;
import hu.elte.strucker.model.project.Project;

public interface ApplicationSelection {
    void fireProjectSelected(Project project);
    void fireDiagramSelected(Diagram diagram);
    void fireLibrarySelected(Library library);
    void fireNothingSelected();
}
