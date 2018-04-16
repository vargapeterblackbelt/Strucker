package hu.elte.strucker.controller.controllers;

import hu.elte.strucker.controller.Application;
import hu.elte.strucker.controller.operations.EditorOperations;

public class EditorController extends Controller implements EditorOperations {
    public EditorController(Application app) {
        super(app);
    }

    @Override
    public void copy() {
        System.out.println("works!");
    }

    @Override
    public void cut() {
        System.out.println("works!");
    }

    @Override
    public void paste() {
        System.out.println("works!");
    }

    @Override
    public void delete() {
        System.out.println("works!");
    }

    @Override
    public void undo() {
        System.out.println("works!");
    }

    @Override
    public void redo() {
        System.out.println("works!");
    }
}
