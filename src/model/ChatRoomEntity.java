package model;

public class ChatRoomEntity {
    private int roomId;
    private String roomName;
    private String description;
    private byte[] image;

    public ChatRoomEntity(int roomId, String roomName, String description, byte[] image) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.description = description;
        this.image = image;
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
}