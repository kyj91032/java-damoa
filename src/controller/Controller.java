package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Model;
import view.ChatListView;
import view.ChatView;
import view.HomeView;
import view.LoginView;
import view.SignUpView;
import view.StartView;

public class Controller extends JFrame {

    private JPanel contentPane;
    private CardLayout cardLayout;
    private Timer timer;

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

        StartView startView = new StartView();
        HomeView homeView = new HomeView(this);
        LoginView loginView = new LoginView(model, this);
        SignUpView signUpView = new SignUpView(model, this);
        ChatListView chatListView = new ChatListView(model, this);
        ChatView chatView = new ChatView(model, this);

        contentPane.add(startView, "start");
        contentPane.add(homeView, "home");
        contentPane.add(loginView, "login");
        contentPane.add(signUpView, "signup");
        contentPane.add(chatListView, "chatlist");
        contentPane.add(chatView, "chat");

        cardLayout.show(contentPane, "start"); // Show the start screen

        timer = new Timer(1500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCard("home"); // Show the home screen after 1.5 seconds
                timer.stop(); // Stop the timer
            }
        });

        timer.setRepeats(false); // Only execute once
        timer.start(); // Start the timer

        model.initDbConn(); // Connect to the database
    }

    public void startApp() {
        setVisible(true);
    }

    public void showCard(String cardName) {
        cardLayout.show(contentPane, cardName);
    }
}