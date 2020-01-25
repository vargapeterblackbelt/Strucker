package hu.elte.strucker.service;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static hu.elte.strucker.service.Message.MSG_FONT;

public class MessageService {
    private static MessageService service = null;
    private static final int HEIGHT = 64;

    public static void install(JFrame frame) {
        service = new MessageService(frame);
    }

//    public static void paintMessages(Graphics graphics) {
//        service.paint(graphics);
//    }

    public static void message(String msg, MessageType type) {
        service.createMessage(msg, type);
    }


    @Getter
    private JFrame frame;

//    private List<Message> msgs = new ArrayList<>();

    private MessageService(JFrame frame) {
        this.frame = frame;
//        Thread t = new Thread(() ->{
//            while(true) {
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                List<Message> toDelete = new ArrayList<>();
//                for (Message msg : msgs) {
//                    if(msg.getAlpha() == 0) {
//                        if(msg.getY() > frame.getLocation().y+frame.getHeight())
//                            toDelete.add(msg);
//                        else
//                            msg.setY(msg.getY()+ 4);
//                    } else {
//                        msg.setAlpha(msg.getAlpha()-1);
//                    }
//                }
//                frame.repaint();
//                msgs.removeAll(toDelete);
//            }
//        });
//        t.start();
    }

    @SuppressWarnings("MagicConstant")
    public void createMessage(String msg, MessageType type) {
        JOptionPane.showMessageDialog(frame, msg, type.getTitle(), type.getMessageType());
    }

//    private void paint(Graphics g) {
//        for (Message msg : msgs) {
//            msg.draw(g);
//        }
//    }

}
