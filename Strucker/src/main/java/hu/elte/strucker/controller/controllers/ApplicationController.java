package hu.elte.strucker.controller.controllers;

import hu.elte.strucker.controller.Application;
import hu.elte.strucker.controller.operations.ApplicationOperations;

public class ApplicationController extends Controller implements ApplicationOperations {
    public ApplicationController(Application app) {
        super(app);
    }

    @Override
    public void showSettings() {

    }

    @Override
    public void showDocumentation() {

    }

    @Override
    public void showAbout() {

    }

    @Override
    public void logStatus() {

    }

    @Override
    public void close() {

    }
}
