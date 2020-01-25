package hu.elte.strucker.controller;

import hu.elte.strucker.application.Application;
import hu.elte.strucker.model.diagram.Diagram;
import hu.elte.strucker.model.project.Library;
import hu.elte.strucker.model.project.Project;
import hu.elte.strucker.service.MessageType;
import hu.elte.strucker.view.dialogs.DiagramPropertiesDialog;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;

import static hu.elte.strucker.model.ObjectLoader.deserialize;
import static hu.elte.strucker.service.MessageService.message;

public class LibraryController implements LibraryOperations {

    private Application app;

    public LibraryController(Application app) {
        this.app = app;
    }

    @Override
    public void create(Project project) {
        String name = JOptionPane.showInputDialog(app.getFrame(), "Könyvtár neve:", "");
        if(name == null) {
            return;
        }
        if(!name.matches("[a-zA-Z][a-zA-Z0-9]*")) {
            message("Csak angol ábécé betűi illetve számok lehetnek a könyvtár nevében.", MessageType.ERROR);
            return;
        }
        if(project.getLibraries().stream().anyMatch(e -> e.getName().equals(name))) {
            JOptionPane.showMessageDialog(app.getFrame(), "Van már ilyen nevű könyvtár a projektben.");
        } else {
            Library library = new Library(name, new ArrayList<>(), false);
            app.fireLibraryInserted(project, library);
        }
    }

    @Override
    public void remove(Library library) {
        app.fireLibraryRemoved(library);
    }

    @Override
    public void rename(Library library) {
        if(library.isPermanent()) {
            message("A főkönyvtárat nem lehet megváltoztatni.", MessageType.INFO);
            return;
        }
        String name = JOptionPane.showInputDialog(app.getFrame(), "Könyvtár neve:", library.getName());
        if(name == null) {
            return;
        }
        if(!name.matches("[a-zA-Z][a-zA-Z0-9]*")) {
            message("Csak angol ábécé betűi illetve számok lehetnek a könyvtár nevében.", MessageType.ERROR);
            return;
        }
        if(library.getProject().getLibraries().stream().anyMatch(e -> e.getName().equals(name))) {
            message( "Van már ilyen nevű könyvtár a projektben.", MessageType.ERROR);
        } else {
            library.setName(name);
            app.fireLibraryChanged(library);
        }

    }

    @Override
    public void createDiagram(Library library) {
        Diagram diagram = new Diagram("Diagram", Object.class, new ArrayList<>(), new ArrayList<>());
        diagram.setLibrary(library);
        do {
            boolean exists;
            DiagramPropertiesDialog dialog = new DiagramPropertiesDialog(diagram);
            if(dialog.isCancelled()) {
                library.removeDiagram(diagram);
                return;
            }
            exists = library.getDiagrams().stream().anyMatch(e -> e.getName().equals(diagram.getName()));
            if(exists) {
                message("Nevezze át a diagramot, mivel már van egy ilyen nevű a könyvtárban.", MessageType.INFO);
            }
        } while(library.getDiagrams().stream().anyMatch(e -> e.getName().equals(diagram.getName())));
        app.fireDiagramInserted(library, diagram);
    }

    @Override
    public void importDiagram(Library library) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Diagram fájlok: .diagram", "diagram"));
        fileChooser.showOpenDialog(app.getFrame());
        File file = fileChooser.getSelectedFile();
        if(file != null) {
            Diagram diagram = deserialize(file.getPath(), Diagram.class);
            if(diagram == null) {
                message("A diagramfájl hibás vagy nem megnyitható", MessageType.ERROR);
                return;
            }
            boolean exists;
            do {
                DiagramPropertiesDialog dialog = new DiagramPropertiesDialog(diagram);
                if(dialog.isCancelled()) {
                    return;
                }
                exists = library.getDiagrams().stream().anyMatch(e -> e.getName().equals(diagram.getName()));
                if(exists) {
                    message("Nevezze át a diagramot, mivel már van egy ilyen nevű a könyvtárban.", MessageType.INFO);
                }
            } while(exists);
            app.fireDiagramInserted(library, diagram);
        }
    }

    @Override
    public void delete(Library library) {
        if(library.isPermanent()) {
            message("Főkönyvtár nem törölhető", MessageType.INFO);
        }
        int option = JOptionPane
                .showConfirmDialog(app.getFrame(), "Biztosan törli?", "Könyvtár törlése", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(option == JOptionPane.YES_OPTION) {
            app.fireLibraryRemoved(library);
        }
    }
}
