package hu.elte.strucker.controller.controllers;

import hu.elte.strucker.controller.Application;
import hu.elte.strucker.controller.operations.StructogramOperations;

public class StructogramController extends Controller implements StructogramOperations {
    public StructogramController(Application app) {
        super(app);
    }

    @Override
    public void delete() {
        System.out.println("works!");
    }

    @Override
    public void insert() {
        System.out.println("works!");
    }

    @Override
    public void insertBefore() {
        System.out.println("works!");
    }

    @Override
    public void insertAfter() {
        System.out.println("works!");
    }

    @Override
    public void moveUp() {
        System.out.println("works!");
    }

    @Override
    public void moveDown() {
        System.out.println("works!");
    }

    @Override
    public void showProperties() {
        System.out.println("works!");
    }
}
