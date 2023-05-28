package Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.util.ArrayList;

public class DynamicPanelExample {
    private static Statement stmt;
    private static App app;
    private static ArrayList<Chat> chats;

    public void createAndShowGUI() {
        JPanel panelContainer = new JPanel();
        panelContainer.setLayout(new BorderLayout());

        JPanel[] panelArray = new JPanel[3]; // 패널 배열 선언

        JButton addButton = new JButton("Add Panel");
        panelContainer.add(addButton, BorderLayout.NORTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 새로운 패널 생성
                JPanel newPanel = createNewPanel();
                for (int i = 0; i < panelArray.length; i++) {
                    if (panelArray[i] == null) {
                        panelArray[i] = newPanel; // 패널 배열에 추가
                        panelContainer.add(newPanel, BorderLayout.CENTER);
                        panelContainer.revalidate();
                        panelContainer.repaint();
                        break;
                    }
                }
            }
        });

        JFrame frame = new JFrame("Dynamic Panel Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.getContentPane().add(panelContainer);
        frame.setVisible(true);
    }

    private JPanel createNewPanel() {
        Chat chat = new Chat(stmt, app, chats); // Chat 객체 생성

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(300, 200));

        // 패널에 Chat 객체를 추가하는 로직을 구현
        panel.add(chat);

        return panel;
    }

    public static void main(String[] args) {
        DynamicPanelExample example = new DynamicPanelExample();
        example.createAndShowGUI();
    }
}
