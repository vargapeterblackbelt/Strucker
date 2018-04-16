package hu.elte.strucker.view.tools;

import javax.swing.*;

public class StruckerButton extends JButton {

    public StruckerButton(String text) {
        super(text);
        setFocusPainted(false);
//        RoundedPanel panel = new RoundedPanel();
//        panel.add(new JLabel(text));
//        add(panel);
    }
}
