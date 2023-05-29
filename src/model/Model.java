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

    private Connection conn;
    private Statement stmt;

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
    
    public boolean checkLogin(String username, String password) {
	    // DB와의 비교 로직을 구현
		try {
			String query = "SELECT * FROM usertable WHERE username = '" + username + "' AND userpw = '" + password + "'";
			ResultSet resultSet = stmt.executeQuery(query);
			return resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
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
}
