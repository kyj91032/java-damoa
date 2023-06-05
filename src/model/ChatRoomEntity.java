package model;

public class ChatRoomEntity {
    private int roomId;
    private String roomName;
    private String description;
    private byte[] image;
    private int portNumber;

    public ChatRoomEntity(int roomId, String roomName, String description, byte[] image, int portNumber) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.description = description;
        this.image = image;
        this.portNumber = portNumber; // 채팅방의 포트 번호

    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getImage() {
        return image;
    }
    
    public int getPortNumber() {
        return portNumber;
    }
}