package hu.elte.strucker.view.dialogs;

import hu.elte.strucker.service.MessageType;
import lombok.Getter;

import javax.swing.*;

import static hu.elte.strucker.service.MessageService.message;

public abstract class StruckerDialog extends JDialog {

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
