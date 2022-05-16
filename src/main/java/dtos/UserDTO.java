package dtos;

import entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private Long id;
    private String userName;

    public UserDTO(String userName, Long id) {
        this.userName = userName;
        this.id = id;
    }
    public UserDTO(User user) {
        this.id= user.getId();
        this.userName = user.getUserName();


    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userName='" + userName + '\'' +
                ", id=" + id +
                '}';
    }
}
