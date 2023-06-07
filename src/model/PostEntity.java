package model;

import java.time.LocalDateTime;

public class PostEntity {
    private int postId;
    private String kategorie;
    private String region;
    private String specificRegion;
    private String textarea;
    private byte[] image;
    private int userId;
    private String title;
    private int roomId;
    private LocalDateTime date;


    
    public PostEntity(int postId, String kategorie, String region, String specificRegion, String textarea, byte[] image, 
    		int userId, String title, int roomId, LocalDateTime date) {
        this.postId = postId;
        this.kategorie = kategorie;
        this.region = region;
        this.specificRegion = specificRegion;
        this.textarea = textarea;
        this.image = image;
        this.userId = userId;
        this.title = title;
        this.roomId = roomId;
        this.date = date;


    }

    public int getPostId() {
        return postId;
    }

    public String getKategorie() {
        return kategorie;
    }

    public String getRegion() {
        return region;
    }

    public String getSpecificRegion() {
        return specificRegion;
    }

    public String getTextarea() {
        return textarea;
    }

    public byte[] getImage() {
        return image;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public int getRoomId() {
        return roomId;
    }
    
    public LocalDateTime getDate() {
    	return date;
    }
    
}