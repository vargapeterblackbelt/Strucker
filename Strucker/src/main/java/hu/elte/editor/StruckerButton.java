package hu.elte.editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StruckerButton extends JButton {

    public StruckerButton(String text, String toolTip, ActionListener l) {
        super(text);
        init(l, toolTip);
    }

    public StruckerButton(ImageIcon icon, String toolTip, ActionListener l) {
        super(icon);
        if(toolTip != null) {
            setToolTipText(toolTip);
        }
        this.addActionListener(l);
        setMargin(new Insets(2, 2, 2, 2));
        setFocusPainted(false);
    }

    private void init(ActionListener l, String toolTip) {
        addActionListener(l);
        setFocusPainted(false);
        setToolTipText(toolTip);
    }

}
