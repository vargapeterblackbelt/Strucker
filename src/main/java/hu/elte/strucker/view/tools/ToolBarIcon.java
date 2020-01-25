package hu.elte.strucker.view.tools;

import hu.elte.strucker.service.ResourceManager;

import javax.swing.*;

public class ToolBarIcon extends ImageIcon{
    private static final String PATH = "images/toolbar/";
    private static final String EXTENSION = ".png";

    private ToolBarIcon(String fileName) {
        super(ResourceManager.getImage(PATH + fileName + EXTENSION));
    }

    public static ToolBarIcon makeIcon(String filename) {
        return new ToolBarIcon(filename);
    }
}
