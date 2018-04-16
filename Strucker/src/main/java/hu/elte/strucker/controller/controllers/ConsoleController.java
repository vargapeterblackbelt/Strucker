package hu.elte.strucker.controller.controllers;

import hu.elte.strucker.controller.Application;
import hu.elte.strucker.controller.operations.ConsoleOperations;

public class ConsoleController extends Controller implements ConsoleOperations {
    public ConsoleController(Application app) {
        super(app);
    }

    @Override
    public void hide() {
        System.out.println("works!");
    }

    @Override
    public void show() {
        System.out.println("works!");
    }

    @Override
    public void clear() {
        System.out.println("works!");
    }

    @Override
    public void log() {
        System.out.println("works!");
    }
}
