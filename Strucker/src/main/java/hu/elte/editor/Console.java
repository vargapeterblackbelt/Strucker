package hu.elte.editor;

import javax.swing.*;
import java.awt.*;

public class Console extends JTextArea {

    public Console() {
        setEditable(false);

    }

    public void print(String msg) {
        setText(getText() + "\n" + msg);
    }

}
