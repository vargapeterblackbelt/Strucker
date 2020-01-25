package hu.elte.strucker.controller;

import hu.elte.strucker.service.ResourceManager;

import javax.swing.*;

public interface StructogramEditorOperations {
    ImageIcon deleteIcon = ResourceManager.getIcon("images/delete.png");
    ImageIcon insertIcon = ResourceManager.getIcon("images/insert.png");
    ImageIcon upIcon = ResourceManager.getIcon("images/up.png");
    ImageIcon downIcon = ResourceManager.getIcon("images/down.png");
    ImageIcon propertiesIcon = ResourceManager.getIcon("images/properties.png");
    ImageIcon checkIcon = ResourceManager.getIcon("images/check.png");
    void delete();
    void insertIntoLoop();
    void insertThen();
    void insertElse();
    void insertBefore();
    void insertAfter();
    void moveUp();
    void moveDown();
    void properties();
}
