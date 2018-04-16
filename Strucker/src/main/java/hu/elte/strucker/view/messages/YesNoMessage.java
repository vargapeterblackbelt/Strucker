package hu.elte.strucker.view.messages;

import hu.elte.strucker.view.tools.MotionPanel;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class YesNoMessage extends JDialog {

    private JPanel mainPanel;
    private JLabel label;
    private JButton noButton;
    private JPanel sidePanel;
    private JButton yesButton;

    @Getter
    private boolean yes = false;
    private Color colorStyle;

    public YesNoMessage(String msg, Color color) {
        super();
        this.colorStyle = color;
        mainPanel = new MotionPanel(this);
        getContentPane().add(mainPanel);
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        yesButton.addActionListener(e -> yes());
        noButton.addActionListener(e -> no());
        setModal(true);

        setVisible(true);
    }

    private void yes() {
        yes = true;
        setVisible(false);
        dispose();
    }
    private void no() {
        setVisible(false);
        dispose();
    }

    private void initComponents() {
        yesButton = new JButton();
        noButton = new JButton();
        sidePanel = new JPanel();
        label = new JLabel();

        mainPanel.setBackground(colorStyle.brighter().brighter().brighter());
        mainPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

        yesButton.setBackground(colorStyle.brighter().brighter());
        yesButton.setText("Igen");
        yesButton.setBorderPainted(false);
        yesButton.setFocusPainted(false);

        noButton.setBackground(colorStyle.brighter().brighter());
        noButton.setText("Nem");
        noButton.setBorderPainted(false);
        noButton.setFocusPainted(false);

        sidePanel.setBackground(colorStyle);

        GroupLayout sidePanelLayout = new GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
                sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 37, Short.MAX_VALUE)
        );
        sidePanelLayout.setVerticalGroup(
                sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        label.setText("Biztosan be akarja zárni az ablakot?");
        label.setToolTipText("");

        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(sidePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(noButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(yesButton))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(label)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(sidePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(30, Short.MAX_VALUE)
                                .addComponent(label)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(yesButton)
                                        .addComponent(noButton))
                                .addContainerGap())
        );
        pack();
    }

    public static void main(String[] args) {
        YesNoMessage alma = new YesNoMessage("Alma", Color.gray);
        System.out.println(alma.isYes() ? "yes" : "no");

    }
}
