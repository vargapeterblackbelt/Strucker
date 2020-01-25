package hu.elte.strucker.view.dialogs;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public abstract class StruckerDialog extends JDialog {

    protected static final Font FONT = new Font("Lucida", Font.PLAIN, 18);

    @Getter
    private boolean cancelled = false;

    public StruckerDialog() {
        super();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    protected abstract void ok();

    protected void cancel() {
//        message("Megszakítva", MessageType.INFO);
        cancelled = true;
        setVisible(false);
        dispose();
    }

}
