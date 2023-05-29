package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import view.SignUpView;

public class Model {

    private Connection conn = null;
    private Statement stmt = null;
    private boolean isLoggedIn = false;
    private UserEntity currentUser; // 현재 로그인되어있는 유저의 정보

    public void initDbConn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/damoa", "root", "1234");
            stmt = conn.createStatement();
            System.out.println("OK");

        } catch (ClassNotFoundException e) {
            System.out.println("The driver does not exist.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL error.");
            e.printStackTrace();
        }
    }

    public Statement getStatement() {
        return stmt;
    }
       
    public void registerUser(SignUpView signupview) { // 회원 등록 메소드
	    
    	JTextField textField = signupview.getTextField();
    	JPasswordField passwordField = signupview.getPasswordField();
    	JPasswordField passwordField_1 = signupview.getPasswordField_1();
    	JTextField textField_1 = signupview.getTextField_1();
    	JTextField textField_2 = signupview.getTextField_2();
    	JTextField textField_3 = signupview.getTextField_3();
    	
    	String username = textField.getText();
	    String password = new String(passwordField.getPassword());
	    String confirmPassword = new String(passwordField_1.getPassword());
	    String nickname = textField_1.getText();
	    String phoneNumber = textField_2.getText();
	    String birthday = textField_3.getText();

	    // 입력된 정보의 유효성 검사
	    if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || nickname.isEmpty() || phoneNumber.isEmpty() || birthday.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "모든 항목을 입력해주세요.", "회원가입 실패", JOptionPane.ERROR_MESSAGE);
	        
	        return;
	    }

	    if (!password.equals(confirmPassword)) {
	        JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", "회원가입 실패", JOptionPane.ERROR_MESSAGE);
	        
	        passwordField.setText("");
	        passwordField_1.setText("");
	        
	        return;
	    }

	    // 데이터베이스에 회원 정보 삽입
	    try {
	        String query = "INSERT INTO usertable (username, userpw, nickname, phone, birth) " +
	                       "VALUES ('" + username + "', '" + password + "', '" + nickname + "', '" + phoneNumber + "', '" + birthday + "')";
	        stmt.executeUpdate(query);
	        JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.", "회원가입 성공", JOptionPane.INFORMATION_MESSAGE);
	        
	        textField.setText("");
	        passwordField.setText("");
	        passwordField_1.setText("");
	        textField_1.setText("");
	        textField_2.setText("");
	        textField_3.setText("");
	        
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "회원가입 중 오류가 발생했습니다.", "회원가입 실패", JOptionPane.ERROR_MESSAGE);
	        ex.printStackTrace();
	        
	        textField.setText("");
	        passwordField.setText("");
	        passwordField_1.setText("");
	        textField_1.setText("");
	        textField_2.setText("");
	        textField_3.setText("");
	    }
	}
    
    public boolean checkLogin(String username, String password) { // 로그인 정보가 일치하는지 확인하는 메소드    
		try {
			String query = "SELECT * FROM usertable WHERE username = '" + username + "' AND userpw = '" + password + "'";
			ResultSet resultSet = stmt.executeQuery(query);
			isLoggedIn = resultSet.next(); // 로그인 여부 업데이트
			initUserInfo(username);
            return isLoggedIn;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
    
    public void initUserInfo(String username) { // 유저 정보 초기화
        if (isLoggedIn) {
            try {
                String query = "SELECT * FROM usertable WHERE username = '" + username + "'";
                ResultSet resultSet = stmt.executeQuery(query);
                if (resultSet.next()) {
                    // 회원 정보 초기화
                    String _username = resultSet.getString("username");
                    String password = resultSet.getString("userpw");
                    String nickname = resultSet.getString("nickname");
                    String phoneNumber = resultSet.getString("phone");
                    String birthday = resultSet.getString("birth");

                    currentUser = new UserEntity();
                    currentUser.init(_username, password, nickname, phoneNumber, birthday);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public int getCurrentUserId() { // id의 초기화
        if (!isLoggedIn) {
            return -1; // 로그인되지 않은 경우 -1을 반환
        }
        int userId = -1;
        try {
            String query = "SELECT userid FROM usertable WHERE username = '" + currentUser.getUsername() + "'";
            ResultSet resultSet = stmt.executeQuery(query);
            if (resultSet.next()) {
                userId = resultSet.getInt("userid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }
    
    public boolean isLoggedin() { // 현재 로그인 상태인지 판단하는 메소드
        return isLoggedIn;
    }
    
    public String getNicknameById(int userId) { // userid를 통해 nickname을 가져오는 메소드
    	String nickname = null;
    	if(isLoggedIn) {
            try {
                String query = "SELECT nickname FROM usertable WHERE userid = " + userId;
                ResultSet resultSet = stmt.executeQuery(query);
                if (resultSet.next()) {
                    nickname = resultSet.getString("nickname");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }    	
        return nickname;
    }
    
    public void logout() { // 로그아웃 메소드
        isLoggedIn = false;
        currentUser = null;
    }

    
}
