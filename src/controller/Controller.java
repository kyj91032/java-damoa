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

import model.Model;
import view.ChatListView;
import view.ChatView;
import view.HomeView;
import view.LoginView;
import view.MyPageView;
import view.PostFormView;
import view.SignUpView;
import view.StartView;
import view.PostView;

public class Controller extends JFrame {

    private JPanel contentPane;
    private CardLayout cardLayout;
    private Timer timer;
    

    private Model model;
	private HomeView homeview;
	
    public Controller() {
        contentPane = new JPanel();
        cardLayout = new CardLayout();
        model = new Model();
        
        model.initDbConn();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(415, 610);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane.setLayout(cardLayout);
        setContentPane(contentPane);
        
        StartView startView = new StartView();
        HomeView inithomeview = new HomeView(model, this);
        LoginView loginView = new LoginView(model, this);
        SignUpView signUpView = new SignUpView(model, this);
        PostFormView postformview = new PostFormView(model, this);
        
        contentPane.add(startView, "start");
        contentPane.add(inithomeview, "inithome");
        contentPane.add(loginView, "login");
        contentPane.add(signUpView, "signup");
        contentPane.add(postformview, "postformview");
        
        cardLayout.show(contentPane, "start");

        timer = new Timer(1500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCard("inithome");
                timer.stop(); 
            }
        });

        timer.setRepeats(false);
        timer.start();

        
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
        		
        		// chatrooms를 기반으로 chatview를 생성하는 메소드
        		
            } else {
                cardName = "login";
            }
        } else if (cardName.equals("postformview")) {
        	if (model.isLoggedin()) {
        		cardName = "postformview";
        	} else {
        		cardName = "login";
        	}
        } else if (cardName.equals("postview")) {
        	PostView postview = new PostView(model, this, model.getCurrentPost());
        	contentPane.add(postview, "postview");
        	cardName = "postview";
        } else if (cardName.equals("home")) {
        	homeview = new HomeView(model, this);
        	contentPane.add(homeview, "home");
        }
        cardLayout.show(contentPane, cardName);
    }
}
