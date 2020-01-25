package hu.elte.strucker.view.tools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StruckerToolBarButton extends JButton {
    public StruckerToolBarButton(ImageIcon icon, String text, String toolTip) {
        super(text, icon);
        setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
        setVerticalTextPosition(BOTTOM);
        setHorizontalTextPosition(CENTER);
        setFocusPainted(false);
        setToolTipText(toolTip);
    }

    public void setup(ActionListener listener) {
        disableButton();
        setEnabled(true);
        addActionListener(listener);
    }

    public void disableButton() {
        for (ActionListener listener : getActionListeners()) {
            removeActionListener(listener);
        }
        setEnabled(false);
    }
}
