package basic;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class App extends JFrame {

    private JPanel contentPane;
    private CardLayout cardLayout;
	private Timer timer;
	
	private static Connection conn;
	private static Statement stmt;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	initDbConn(); // db 연결
                    App frame = new App();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    private static void initDbConn() { // db 최초 연결 메소드
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/damoa",
					"root", 
					"1234");
			stmt = conn.createStatement();
			System.out.println("OK");
						
		} catch (ClassNotFoundException e) {
			System.out.println("해당 드라이버가 존재하지 않습니다.");
			e.printStackTrace();
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 입니다.");
			e.printStackTrace();
			
		}
	}


    public App() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(415, 610);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        cardLayout = new CardLayout();
        contentPane.setLayout(cardLayout);

        setContentPane(contentPane);

        Start start = new Start();
        Home home = new Home(this); // home 생성 시 app(this)를 넘겨서 home에서 showCard를 호출할 수 있게 함.
        Login login = new Login(stmt, this);
        SignUp signup = new SignUp(stmt, this);

        contentPane.add(start, "start");
        contentPane.add(home, "home");
        contentPane.add(login, "login");
        contentPane.add(signup, "signup");
        
        cardLayout.show(contentPane, "start"); // 시작화면 보여줌
        
        timer = new Timer(1500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCard("home"); // 1.5초 뒤 홈 화면 보여줌
                timer.stop(); // 타이머 중지
            }
        });
        
        timer.setRepeats(false); // 한 번만 실행되도록 설정
        timer.start(); // 타이머 시작

    }
    
    public void showCard(String cardName) { // 화면 전환 메소드
        cardLayout.show(contentPane, cardName);
    }
   
    
    
}

