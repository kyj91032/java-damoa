package controller;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.ChatRoomEntity;
import model.Model;
import view.ChatListView;
import view.ChatServerThread;
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
	private String previousCard; // 이전에 보여준 카드의 이름을 저장하는 변수

	
    public Controller() {
        contentPane = new JPanel();
        cardLayout = new CardLayout();
        model = new Model();
        
        previousCard = "start";
        
        model.initDbConn();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(415, 610);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane.setLayout(cardLayout);
        setContentPane(contentPane);
        
        StartView startView = new StartView();
        homeview = new HomeView(model, this);
        LoginView loginView = new LoginView(model, this);
        SignUpView signUpView = new SignUpView(model, this);
        PostFormView postformview = new PostFormView(model, this);
        
        contentPane.add(startView, "start");
        contentPane.add(homeview, "home");
        contentPane.add(loginView, "login");
        contentPane.add(signUpView, "signup");
        contentPane.add(postformview, "postform");
        
        cardLayout.show(contentPane, "start");

        timer = new Timer(1500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCard("home");
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
        } else if (cardName.equals("chat")) {
        	
        	
        	
        } else if (cardName.equals("postform")) {
        	if (model.isLoggedin()) {
        		cardName = "postform";
        	} else {
        		cardName = "login";
        	}
        } else if (cardName.equals("post")) {
        	PostView postview = new PostView(model, this, model.getCurrentPost());
        	contentPane.add(postview, "post");
        	
        } else if (cardName.equals("home")) {
            homeview = new HomeView(model, this);
            contentPane.add(homeview, "home");
        }
        
        previousCard = cardName;
        cardLayout.show(contentPane, cardName);
    }
    
    public void showCard(String cardName, Socket socket) {
    	if (cardName.equals("chatlist")) {
    		
    		if (model.isLoggedin()) {
        		ChatListView chatlistview = new ChatListView(model, this);
        		contentPane.add(chatlistview, "chatlist");
        		
        		List<ChatRoomEntity> chatRooms = model.getChatListByUserId(model.getCurrentUserId());
                for (ChatRoomEntity chatRoom : chatRooms) {
                    int roomId = chatRoom.getRoomId();
                    int portNumber = model.getPortNumberByRoomId(roomId);
                    
                    String chatViewName = "chat" + roomId;
                    ChatView chatview = new ChatView(model, this, model.getCurrentChatMessages(), socket);
                    contentPane.add(chatview, chatViewName);
                    
                    openChatRoomServer(portNumber, chatview);
                }
        		

            } else {
                cardName = "login";
            }
    		
    	}
    	cardLayout.show(contentPane, cardName);
    }
    
    public void openChatRoomServer(int portNumber, ChatView chatview) {
        // 서버 스레드 생성 및 시작
        Thread serverThread = new Thread(() -> {
            try {
                // 서버 소켓 생성
                ServerSocket serverSocket = new ServerSocket(portNumber);
                
                // 채팅방 서버가 시작되었음을 출력
                System.out.println("채팅방 서버가 포트 " + portNumber + "에서 시작되었습니다.");
                
                // 클라이언트 연결을 계속해서 처리
                while (true) {
                    // 클라이언트의 연결을 대기
                    Socket clientSocket = serverSocket.accept();
                    
                    // 새로운 클라이언트 연결이 들어왔음을 출력
                    System.out.println(portNumber + "번 포트에 새로운 클라이언트가 연결되었습니다.");
                    
                    // 클라이언트의 연결을 처리하는 스레드 생성 및 시작
                    ChatServerThread chatServerThread = new ChatServerThread(clientSocket, chatview);
                    chatServerThread.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
         
        // 서버 스레드 시작
        serverThread.start();
    }



}
