package hu.elte.strucker.service;

import lombok.Getter;

import java.awt.*;

public enum MessageType {
    INFO(Color.blue, "Információ"), ERROR(Color.red, "Hiba"), SUCCESS(Color.green, "Siker");

    @Getter
    private Color color;
    @Getter
    private String title;

    private MessageType(Color color, String title) {
        this.color = color;
        this.title = title;
    }
}
