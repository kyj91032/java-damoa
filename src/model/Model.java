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
	private PostEntity currentPost; // 최근 올린 게시글 정보 
	private ArrayList chatRooms; // 최근 채팅방 목록 정보 
	private ArrayList posts; // 현재 모든 글 리스트 

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
    
    public UserEntity getCurrentUser() { // 현재 유저 엔티티 getter
    	return currentUser;
    }
    
    public void logout() { // 로그아웃 메소드
        isLoggedIn = false;
        currentUser = null;
    }
    
    
    public List<ChatRoomEntity> getChatListByUserId(int userId) { // userid를 이용해 chatlist를 가져오는 메소드
        chatRooms = new ArrayList<>();

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

    
    public void postForm(PostFormView postformview) { // 모집하기 글 db로 올리는 메소드 + 채팅방 추가 db까지 
    	
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
        	
        	// roomtable에 데이터 추가
            String roomQuery = "INSERT INTO chatroomtable (roomname, description, image) VALUES (?, ?, ?)";
            PreparedStatement roomStatement = conn.prepareStatement(roomQuery, Statement.RETURN_GENERATED_KEYS);
            roomStatement.setString(1, title);
            roomStatement.setString(2, textarea); // 적절한 값을 설정해주세요.
            roomStatement.setBytes(3, image);
            roomStatement.executeUpdate();
            
            // 생성된 roomid 가져오기
            int roomId = 0;
            ResultSet generatedRoomKeys = roomStatement.getGeneratedKeys();
            if (generatedRoomKeys.next()) {
                roomId = generatedRoomKeys.getInt(1);
                System.out.println("새로운 room ID: " + roomId);
            }

            // 외래 키 제약 조건 비활성화
            String disableForeignKeyQuery = "SET FOREIGN_KEY_CHECKS = 0";
            Statement disableForeignKeyStatement = conn.createStatement();
            disableForeignKeyStatement.executeUpdate(disableForeignKeyQuery);

            // posttable에 데이터 추가
            String postQuery = "INSERT INTO posttable (kategorie, region, specificregion, title, textarea, image, userid, roomid) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement postStatement = conn.prepareStatement(postQuery, Statement.RETURN_GENERATED_KEYS);
            postStatement.setString(1, kategorie);
            postStatement.setString(2, region);
            postStatement.setString(3, specificregion);
            postStatement.setString(4, title);
            postStatement.setString(5, textarea);
            postStatement.setBytes(6, image);
            postStatement.setInt(7, getCurrentUserId());
            postStatement.setInt(8, roomId);
            postStatement.executeUpdate();

            // 외래 키 제약 조건 활성화
            String enableForeignKeyQuery = "SET FOREIGN_KEY_CHECKS = 1";
            Statement enableForeignKeyStatement = conn.createStatement();
            enableForeignKeyStatement.executeUpdate(enableForeignKeyQuery);
            
            // user_chatroom에 데이터 추가
            String userChatroomQuery = "INSERT INTO user_chatroom (roomid, userid) VALUES (?, ?)";
            PreparedStatement userChatroomStatement = conn.prepareStatement(userChatroomQuery);
            userChatroomStatement.setInt(1, roomId);
            userChatroomStatement.setInt(2, getCurrentUserId());
            userChatroomStatement.executeUpdate();
            

            // 생성된 포스트의 ID 가져오기
            int postId = 0;
            ResultSet generatedKeys = postStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                postId = generatedKeys.getInt(1);
                System.out.println("새로운 포스트 ID: " + postId);
            }
            
            
            currentPost = getPostBypostid(postId);
            
            postformview.getTextField().setText("");
            postformview.getKategorieComboBox().setSelectedItem(0);
            postformview.getMainComboBox().setSelectedItem(0);
            postformview.getSubComboBox().setSelectedItem(null);
            postformview.getTextArea().setText("");        

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "글 올리기 중 오류가 발생했습니다.", "글 올리기 실패", JOptionPane.ERROR_MESSAGE);            
            ex.printStackTrace();
        }
    }
    
    public PostEntity getPostBypostid(int postId) {
        String query = "SELECT * FROM posttable WHERE postid = ?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, postId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // 결과 데이터를 가져와서 PostEntity 객체로 변환
                    int fetchedPostId = resultSet.getInt("postid");
                    String kategorie = resultSet.getString("kategorie");
                    String region = resultSet.getString("region");
                    String specificRegion = resultSet.getString("specificregion");
                    String title = resultSet.getString("title");
                    String textarea = resultSet.getString("textarea");
                    // 이미지 데이터를 가져올 때에는 필요한 방법으로 처리
                    byte[] image = resultSet.getBytes("image");
                    int userId = resultSet.getInt("userid");
                    int roomId = resultSet.getInt("roomid");

                    return new PostEntity(fetchedPostId, kategorie, region, specificRegion, textarea, image, userId, title, roomId);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null; // 해당 postid에 대한 데이터를 찾지 못한 경우 null 반환
    }
       
    public PostEntity getCurrentPost() { // 현재 포스트 getter
    	return currentPost;
    }
    
    public List<PostEntity> getAllPosts() { // 모든 글 불러오는 메소드
        posts = new ArrayList<>();

        try {
            String query = "SELECT * FROM posttable";
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                int postId = resultSet.getInt("postid");
                String kategorie = resultSet.getString("kategorie");
                String region = resultSet.getString("region");
                String specificRegion = resultSet.getString("specificregion");
                String textarea = resultSet.getString("textarea");
                byte[] image = resultSet.getBytes("image");
                int userId = resultSet.getInt("userid");
                String title = resultSet.getString("title");
                int roomId = resultSet.getInt("roomid");

                PostEntity post = new PostEntity(postId, kategorie, region, specificRegion, textarea, image, userId, title, roomId);
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }


    

    
}
