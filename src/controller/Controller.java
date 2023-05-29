package controller;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Test.Chat;
import Test.Recruit;
import Test.RecruitComplete;
import model.Model;
import view.ChatListView;
import view.ChatView;
import view.HomeView;
import view.LoginView;
import view.MyPageView;
import view.SignUpView;
import view.StartView;

public class Controller extends JFrame {

    private JPanel contentPane;
    private CardLayout cardLayout;
    private Timer timer;
    private ChatView chatview;
	private ArrayList<ChatView> chats;

    private Model model;

    public Controller() {
        contentPane = new JPanel();
        cardLayout = new CardLayout();
        model = new Model();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(415, 610);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane.setLayout(cardLayout);
        setContentPane(contentPane);
        
        chats = new ArrayList<>();
        
        ChatView chat1 = new ChatView(model, this, chats);
        ChatView chat2 = new ChatView(model, this, chats);
        ChatView chat3 = new ChatView(model, this, chats);
        
        chats.add(chat1);
        chats.add(chat2);
        chats.add(chat3);

        StartView startView = new StartView();
        HomeView homeView = new HomeView(model, this);
        LoginView loginView = new LoginView(model, this);
        SignUpView signUpView = new SignUpView(model, this);
        
        contentPane.add(startView, "start");
        contentPane.add(homeView, "home");
        contentPane.add(loginView, "login");
        contentPane.add(signUpView, "signup");
        
        for (int i = 0; i < chats.size(); i++) {
            chatview = chats.get(i);
            contentPane.add(chatview, "chat" + (i+1));
        }
        
        cardLayout.show(contentPane, "start");

        timer = new Timer(1500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCard("home"); 
                timer.stop(); 
            }
        });

        timer.setRepeats(false);
        timer.start();

        model.initDbConn();
    }

    public void startApp() {
        setVisible(true);
    }

    public void showCard(String cardName) {
        if (cardName.equals("mypage")) {
            if (model.isLoggedin()) {
                // 로그인이 되어있을 경우 MyPageView 생성
                MyPageView myPageView = new MyPageView(model, this);
                contentPane.add(myPageView, "mypage");
            } else {
                // 로그인이 되어있지 않을 경우 로그인 화면으로 이동
                cardName = "login";
            }
        } else if (cardName.equals("chatlist")) {
        	if (model.isLoggedin()) {
        		ChatListView chatlistview = new ChatListView(model, this);
        		contentPane.add(chatlistview, "chatlist");
            } else {
                cardName = "login";
            }
        }
        cardLayout.show(contentPane, cardName);
    }

}

