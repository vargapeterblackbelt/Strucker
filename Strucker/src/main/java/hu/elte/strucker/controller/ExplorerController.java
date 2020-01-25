package hu.elte.strucker.controller;

import hu.elte.strucker.application.Application;

public class ExplorerController implements ExplorerOperations {

    private Application app;

    public ExplorerController(Application app) {
        this.app = app;
    }

    @Override
    public void closeAll() {
        app.fireAllProjectClosing();
    }

    @Override
    public void saveAll() {
        app.fireAllProjectSaving();
    }

    @Override
    public void expand() {
        app.fireExplorerExpanding();
    }

    @Override
    public void collapse() {
        app.fireExplorerCollapsing();
    }

    @Override
    public void open() {
        app.getProjectController().open();
    }
}
