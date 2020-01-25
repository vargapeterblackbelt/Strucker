package hu.elte.strucker.model;

import lombok.Getter;

import javax.swing.*;

public enum HealthCheck {
    OK(new ImageIcon("icons/explorer/ok.png")),
    UNKNOWN(new ImageIcon("icons/explorer/unknown.png")),
    ERROR(new ImageIcon("icons/explorer/error.png")),
    REDUNDANT(new ImageIcon("icons/explorer/redundant.png"));

    @Getter
    private ImageIcon icon;

    private HealthCheck(ImageIcon icon) {
        this.icon = icon;
    }
}
