package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Long ownerId;
    private String roomCode;

    @OneToMany(mappedBy = "room")
    private List<UserRoom> userRoomList = new ArrayList<>();


    public Room(User owner, String roomCode) {
        this.ownerId = owner.getId();
        this.roomCode = roomCode;
    }

    public Room() {
    }


    public void addToUserRoomList(UserRoom userRoom){
        this.userRoomList.add(userRoom);
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
}
