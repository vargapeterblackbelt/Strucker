package hu.elte.strucker.controller.controllers;

import hu.elte.strucker.controller.Application;
import hu.elte.strucker.controller.operations.ApplicationOperations;

public class ApplicationController extends Controller implements ApplicationOperations {
    public ApplicationController(Application app) {
        super(app);
    }

    @Override
    public void showSettings() {
        System.out.println("works!");
    }

    @Override
    public void showDocumentation() {
        System.out.println("works!");
    }

    @Override
    public void showAbout() {
        System.out.println("works!");
    }

    @Override
    public void logStatus() {
        System.out.println("works!");
    }

    @Override
    public void close() {
        System.out.println("works!");
    }
}
