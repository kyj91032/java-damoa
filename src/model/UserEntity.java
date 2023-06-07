package model;

public class UserEntity {
	
	private int userid; private String username; private String userpw; private String nickname; private String phone; private String birth;

    
    public UserEntity(int userid, String username, String userpw, String nickname, String phone, String birth) {
        this.userid = userid;
    	this.username = username;
        this.userpw = userpw;
        this.nickname = nickname;
        this.phone = phone;
        this.birth = birth;
    }

    
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
}
