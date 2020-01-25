package hu.elte.strucker.service;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

@Getter
public class ResourceManager {

    public static Image getImage(final String path) {
        final URL url = Thread.currentThread().getContextClassLoader().getResource(path);
        return Toolkit.getDefaultToolkit().getImage(url);
    }

    public static ImageIcon getIcon(final String path) {
        return new ImageIcon(getImage(path));
    }

    private ResourceManager(){};
}
