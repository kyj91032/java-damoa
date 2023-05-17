package basic;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class App extends JFrame {

    private JPanel contentPane;
    private CardLayout cardLayout;
	private Timer timer;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    App frame = new App();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public App() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 570);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        cardLayout = new CardLayout();
        contentPane.setLayout(cardLayout);

        setContentPane(contentPane);

        Start card1 = new Start();
        HomeView card2 = new HomeView();

        contentPane.add(card1, "Card1");
        contentPane.add(card2, "Card2");
        cardLayout.show(contentPane, "Card1");
        
        timer = new Timer(1500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPane, "Card2");
                timer.stop(); // 타이머 중지
            }
        });
        
        timer.setRepeats(false); // 한 번만 실행되도록 설정
        timer.start(); // 타이머 시작

    }
}

