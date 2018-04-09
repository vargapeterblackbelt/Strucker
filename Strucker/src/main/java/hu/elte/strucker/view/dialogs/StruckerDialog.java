package hu.elte.strucker.view.dialogs;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class StruckerDialog extends JDialog{

    public StruckerDialog() {
        super();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                cancel();
            }
        });
    }

    protected abstract void ok();

    protected void cancel() {
        setVisible(false);
        dispose();
    }

}
