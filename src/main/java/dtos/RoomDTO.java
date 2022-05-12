package dtos;

import entities.Movie;
import entities.Room;
import entities.User;
import facades.Facade;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class RoomDTO {

    private Long id;
    private Long ownerId;
    private String roomCode;
    private String roomName;
    private String ownerName;



    public RoomDTO(Room room) {
        this.id = room.getId();
        this.ownerId = room.getOwnerId();
        this.roomCode = room.getRoomCode();
        this.roomName = room.getRoomName();
    }

    public RoomDTO(Long id, Long ownerId, String roomCode, String roomName) {
        this.id = id;
        this.ownerId = ownerId;
        this.roomCode = roomCode;
        this.roomName = roomName;
    }

    public RoomDTO() {
    }

    public static List<RoomDTO> getRoomDTOS(List<Room> rooms){
        List<RoomDTO> roomDTOS = new ArrayList<>();

        rooms.forEach(r->roomDTOS.add(new RoomDTO(r)));

        return roomDTOS;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return "RoomDTO{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", roomCode='" + roomCode + '\'' +
                ", roomName='" + roomName + '\'' +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}
