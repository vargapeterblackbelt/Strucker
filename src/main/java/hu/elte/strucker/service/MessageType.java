package hu.elte.strucker.service;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public enum MessageType {
    INFO(Color.blue, "Információ", JOptionPane.INFORMATION_MESSAGE),
    ERROR(Color.red, "Hiba", JOptionPane.ERROR_MESSAGE),
    SUCCESS(Color.green, "Siker", JOptionPane.INFORMATION_MESSAGE);

    @Getter
    private Color color;

    @Getter
    private String title;

    @Getter
    private int messageType;

    private MessageType(Color color, String title, int messageType) {
        this.color = color;
        this.title = title;
        this.messageType = messageType;
    }
}
