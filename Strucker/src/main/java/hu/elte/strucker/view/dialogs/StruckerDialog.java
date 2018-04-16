package hu.elte.strucker.view.dialogs;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class StruckerDialog extends JDialog{

    @Getter
    private boolean cancelled = false;

    public StruckerDialog() {
        super();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                cancel();
            }
        });
    }

    public void showDialog() {
        setModal(true);
        setVisible(true);
    }

    protected abstract void ok();

    protected void cancel() {
        cancelled = true;
        setVisible(false);
        dispose();
    }

}
