package model;

public class PostEntity {
    
	private int postId;
    private String kategorie;
    private String region;
    private String specificregion;
    private String title;
    private String textArea;
    private byte[] image;
    private int userId;
    
    
    
    public int getPostId() {
        return postId;
    }
    
    public void setPostId(int postId) {
        this.postId = postId;
    }
    
    public String getKategorie() {
        return kategorie;
    }
    
    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }
    
    public String getRegion() {
        return region;
    }
    
    public void setRegion(String region) {
        this.region = region;
    }
    
    public String getSpecificregion() {
        return specificregion;
    }
    
    public void setSpecificregion(String specificregion) {
        this.specificregion = specificregion;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getTextArea() {
        return textArea;
    }
    
    public void setTextArea(String textArea) {
        this.textArea = textArea;
    }
    
    public byte[] getImage() {
        return image;
    }
    
    public void setImage(byte[] image) {
        this.image = image;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
