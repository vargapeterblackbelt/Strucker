package hu.elte.strucker.application;

import hu.elte.strucker.controller.*;
import hu.elte.strucker.model.HealthCheck;
import hu.elte.strucker.model.diagram.Diagram;
import hu.elte.strucker.model.project.Library;
import hu.elte.strucker.model.project.Project;
import hu.elte.strucker.service.MessageType;
import hu.elte.strucker.service.ResourceManager;
import hu.elte.strucker.view.editor.Explorer;
import hu.elte.strucker.view.editor.Viewer;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static hu.elte.strucker.model.ProjectStatus.MODIFIED;
import static hu.elte.strucker.service.MessageService.message;
import static javax.swing.JOptionPane.*;

@Getter
public class StruckerApplication implements Application {

    private final ProjectOperations projectController;
    private final DiagramOperations diagramController;
    private final ExplorerOperations explorerController;
    private final LibraryOperations libraryController;
    private final StruckerFrame frame;
    private final List<Project> projects;
    private final Map<Diagram, Viewer> diagrams;

    public StruckerApplication() {
        projectController = new ProjectController(this);
        libraryController = new LibraryController(this);
        diagramController = new DiagramController(this);
        explorerController = new ExplorerController(this);
        frame = new StruckerFrame(this);
        frame.fireNothingSelected();
        projects = new ArrayList<>();
        diagrams = new HashMap<>();
    }

    @Override
    public void run() {
        frame.setVisible(true);
    }

    @Override
    public void exit() {
        if(projects.stream().anyMatch(e -> e.getStatus().equals(MODIFIED))) {
            int option = JOptionPane
                    .showConfirmDialog(frame, "Még vannak mentetlen projektjei. Mégis bezárja a programot?",
                            "Nem mentett projektek", YES_NO_OPTION, QUESTION_MESSAGE);
            if(option == CANCEL_OPTION || option == NO_OPTION) {
                message("Kilépés megszakítva", MessageType.INFO);
            } else {
                System.exit(0);
            }
        } else {
            int option = JOptionPane.showConfirmDialog(frame, "Biztosan kilép?", "Kilépés megerősítése", YES_NO_OPTION, QUESTION_MESSAGE);
            if(option == YES_OPTION) {
                System.exit(0);
            }
        }
    }



    @Override
    public void fireAllProjectClosing() {
        fireCloseAllTabs();
        Explorer explorer = frame.getExplorer();
        for (Project project : projects) {
            explorer.fireNodeRemoved(project);
        }
    }

    @Override
    public void fireAllProjectSaving() {
        for (Project project : projects) {
            projectController.save(project);
            frame.getExplorer().fireNodeChanged(project);
        }
    }

    @Override
    public void fireNothingSelected() {
        frame.fireNothingSelected();
    }

    @Override
    public void fireProjectSelected(Project project) {
        frame.fireProjectSelected(project);
    }

    @Override
    public void fireLibrarySelected(Library library) {
        frame.fireLibrarySelected(library);
    }

    @Override
    public void fireDiagramSelected(Diagram diagram) {
        frame.fireDiagramSelected(diagram);
    }

    @Override
    public void fireProjectOpened(Project project) {
        if (projects.stream().anyMatch(e -> e.getLocation().equals(project.getLocation()))) {
            message("Már nyitva van a megadott elérési úton megadott projekt: "+project.getLocation(), MessageType.INFO);
        } else {
            projects.add(project);
            frame.getExplorer().fireProjectInserted(project);
        }
    }

    @Override
    public void fireProjectClosed(Project project) {
        if(project.getStatus().equals(MODIFIED)) {
            int option = JOptionPane
                    .showConfirmDialog(frame, "Elmenti a változtatásokat a projekten?", project.getName(), YES_NO_CANCEL_OPTION, QUESTION_MESSAGE);
            if(option == CANCEL_OPTION) {
                return;
            }
            if(option == YES_OPTION) {
                projectController.save(project);
            }
            for (Library library : project.getLibraries()) {
                for (Diagram diagram : library.getDiagrams()) {
                    if(diagrams.containsKey(diagram)) {
                        frame.getTabbedPanel().remove(diagrams.get(diagram));
                        diagrams.remove(diagram);
                    }
                }
            }
        }
        projects.remove(project);
        frame.getExplorer().fireNodeRemoved(project);
    }

    @Override
    public void fireProjectChanged(Project project) {
        project.setStatus(MODIFIED);
        project.setHealthCheck(HealthCheck.UNKNOWN);
        frame.getExplorer().fireNodeChanged(project);
    }

    @Override
    public void fireLibraryInserted(Project project, Library library) {
        project.setStatus(MODIFIED);
        project.addLibrary(library);
        fireProjectChanged(project);
        frame.getExplorer().fireLibraryInserted(library);
    }

    @Override
    public void fireLibraryChanged(Library library) {
        frame.getExplorer().fireNodeChanged(library);
        fireProjectChanged(library.getProject());
    }

    @Override
    public void fireLibraryRemoved(Library library) {
        library.getProject().removeLibrary(library);
        frame.getExplorer().fireNodeRemoved(library);
        fireProjectChanged(library.getProject());
    }

    @Override
    public void fireDiagramInserted(Library library, Diagram diagram) {
        library.addDiagram(diagram);
        frame.getExplorer().fireDiagramInserted(diagram);
        fireProjectChanged(library.getProject());
    }

    @Override
    public void fireDiagramRemoved(Diagram diagram) {
        diagram.getLibrary().removeDiagram(diagram);
        frame.getExplorer().fireNodeRemoved(diagram);
        fireLibraryChanged(diagram.getLibrary());
    }

    @Override
    public void fireDiagramOpening(Diagram diagram) {
        if (diagrams.containsKey(diagram)) {
            frame.getTabbedPanel().setSelectedComponent(diagrams.get(diagram));
        } else {
            Viewer viewer = new Viewer(diagram, this);
            diagrams.put(diagram, viewer);
            frame.getTabbedPanel().add(diagram.getFullName(), viewer);
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            panel.setOpaque(false);
            JLabel label = new JLabel(diagram.getFullName());
            panel.add(label);
            JLabel close = new JLabel(ResourceManager.getIcon("images/close_tab.png"));
            panel.add(close);
            close.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    frame.getTabbedPanel().remove(viewer);
                    diagrams.remove(diagram);
                }
            });

            frame.getTabbedPanel().setTabComponentAt(frame.getTabbedPanel().indexOfComponent(viewer), panel);
        }
    }

    @Override
    public void fireDiagramClosing(Diagram diagram) {
        Viewer viewer = diagrams.get(diagram);
        frame.getTabbedPanel().remove(viewer);
        diagrams.remove(diagram);
    }

    @Override
    public void fireDiagramChanged(Diagram diagram) {
        frame.getExplorer().fireNodeChanged(diagram);
        diagram.setHealthCheck(HealthCheck.UNKNOWN);
        fireLibraryChanged(diagram.getLibrary());
    }

    @Override
    public void fireDiagramChecked(Diagram diagram) {
        frame.getExplorer().fireNodeChanged(diagram);
    }

    @Override
    public void fireExplorerCollapsing() {
        frame.getExplorer().collapse();
    }

    @Override
    public void fireExplorerExpanding() {
        frame.getExplorer().expand();
    }

    @Override
    public void fireZoomIn(Diagram diagram) {
        Viewer viewer = diagrams.get(diagram);
        if(viewer != null && viewer.equals(frame.getTabbedPanel().getSelectedComponent())) {
            viewer.zoomIn();
        }
    }

    @Override
    public void fireZoomOut(Diagram diagram) {
        Viewer viewer = diagrams.get(diagram);
        if(viewer != null && viewer.equals(frame.getTabbedPanel().getSelectedComponent())) {
            viewer.zoomOut();
        }
    }

    @Override
    public void fireCloseAllTabs() {
        for (Diagram diagram : diagrams.keySet()) {
            fireDiagramClosing(diagram);
        }
    }

    @Override
    public boolean isDiagramOpen(Diagram diagram) {
        return diagrams.containsKey(diagram);
    }
}
