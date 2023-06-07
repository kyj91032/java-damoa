package model;

public class ChatMessageEntity {
	private int messageId; private int roomId; private int userId; private String content;


    public ChatMessageEntity(int messageId, int roomId, int userId, String content) {
        this.messageId = messageId;
        this.roomId = roomId;
        this.userId = userId;
        this.content = content;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
