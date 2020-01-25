package hu.elte.strucker.view.editor;

import hu.elte.strucker.application.Application;
import hu.elte.strucker.model.diagram.Structogram;

public interface StructogramEditor {
    void fireStructogramChanged();
    void fireDiagramChanged();
    Application getApp();
    Structogram getSelectedStructogram();
}
