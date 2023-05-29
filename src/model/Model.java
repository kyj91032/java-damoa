package model;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import view.PostFormView;
import view.SignUpView;

import java.util.ArrayList;
import java.util.List;


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
    
    public Connection getConnection() {
    	return conn;
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
       
    public String getNicknameById(int userId) {
    	String nickname = currentUser.getNickname();
    	return nickname;
    }
    
    public void logout() { // 로그아웃 메소드
        isLoggedIn = false;
        currentUser = null;
    }
    
    
    public List<ChatRoomEntity> getChatListByUserId(int userId) { // userid를 이용해 chatlist를 가져오는 메소드
        List<ChatRoomEntity> chatRooms = new ArrayList<>();

        try {
            // user_chatroom 테이블과 chatroomtable을 조인하여 사용자가 속한 채팅방 목록을 가져오는 쿼리
            String query = "SELECT c.roomid, c.roomname, c.description, c.image " +
                           "FROM user_chatroom uc " +
                           "JOIN chatroomtable c ON uc.roomid = c.roomid " +
                           "WHERE uc.userid = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int roomId = resultSet.getInt("roomid");
                String roomName = resultSet.getString("roomname");
                String description = resultSet.getString("description");
                Blob blob = resultSet.getBlob("image");
                byte[] imageData = null;

                if (blob != null) {
                    imageData = blob.getBytes(1, (int) blob.length());
                }

                ChatRoomEntity chatRoom = new ChatRoomEntity(roomId, roomName, description, imageData);
                chatRooms.add(chatRoom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chatRooms;
    }

    
    public void postForm(PostFormView postformview) { // 모집하기 글 db로 올리는 메소드
    	
        String title = postformview.getTextField().getText(); // JTextField에서 입력된 값
        String kategorie = (String) postformview.getKategorieComboBox().getSelectedItem(); // JComboBox에서 선택된 값
        String region = (String) postformview.getMainComboBox().getSelectedItem(); // JComboBox에서 선택된 값
        String specificregion = (String) postformview.getSubComboBox().getSelectedItem(); // JComboBox에서 선택된 값
        String textarea = postformview.getTextArea().getText(); // JTextArea에서 입력된 값
        byte[] image = postformview.getImageData(); // 이미지 데이터 (BLOB 형태)

        // 입력된 정보의 유효성 검사
        if (title.isEmpty() || kategorie.isEmpty() || region.isEmpty() || specificregion.isEmpty() || textarea.isEmpty()) {
            JOptionPane.showMessageDialog(null, "모든 항목을 입력해주세요.", "글 올리기 실패", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String query = "INSERT INTO posttable (kategorie, region, specificregion, title, textarea, image, userid) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, kategorie);
            statement.setString(2, region);
            statement.setString(3, specificregion);
            statement.setString(4, title);
            statement.setString(5, textarea);
            statement.setBytes(6, image);
            statement.setInt(7, getCurrentUserId()); // Replace getUserId() with the appropriate method to retrieve the user ID

            // 쿼리 실행
            statement.executeUpdate();

            // 생성된 포스트의 ID 가져오기
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int postId = generatedKeys.getInt(1);
                System.out.println("새로운 포스트 ID: " + postId);
            }
            
            postformview.getTextField().setText("");
            postformview.getKategorieComboBox().setSelectedItem("카테고리");
            postformview.getMainComboBox().setSelectedItem("지역");
            postformview.getSubComboBox().setSelectedItem(null);
            postformview.getTextArea().setText("");
            

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "글 올리기 중 오류가 발생했습니다.", "글 올리기 실패", JOptionPane.ERROR_MESSAGE);            
            ex.printStackTrace();
        }
    }
    
    
       
    
    


    

    
}
