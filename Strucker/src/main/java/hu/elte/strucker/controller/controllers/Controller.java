package hu.elte.strucker.controller.controllers;

import hu.elte.strucker.controller.Application;

public abstract class Controller {
    protected Application app;

    public Controller(Application app) {
        this.app = app;
    }

}
