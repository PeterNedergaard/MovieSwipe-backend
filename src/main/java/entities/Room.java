package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Long ownerId;
    private String roomCode;
    private String roomName;

    @OneToMany(mappedBy = "room")
    private List<UserRoom> userRoomList = new ArrayList<>();


    public Room(User owner, String roomCode, String roomName) {
        this.ownerId = owner.getId();
        this.roomCode = roomCode;
        this.roomName = roomName;
    }

    public Room() {
    }


    public void addToUserRoomList(UserRoom userRoom){
        this.userRoomList.add(userRoom);
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public List<UserRoom> getUserRoomList() {
        return userRoomList;
    }

    public void setUserRoomList(List<UserRoom> userRoomList) {
        this.userRoomList = userRoomList;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(ownerId, room.ownerId) && Objects.equals(roomCode, room.roomCode) && Objects.equals(roomName, room.roomName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerId, roomCode, roomName);
    }
}
