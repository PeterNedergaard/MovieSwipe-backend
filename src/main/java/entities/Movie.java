package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String releaseYear;
    private String imgUrl;
    private String rating;
    private String duration;

    @ManyToMany(mappedBy = "movieList")
    @JoinTable(
            name ="user_movie",
            joinColumns = @JoinColumn(name="movie_id"),
            inverseJoinColumns = @JoinColumn(name="user_id"))
    private List<User> userList = new ArrayList<>();

    public Movie(String title, String releaseDate, String imgUrl, String rating, String duration) {
        this.title = title;
        this.releaseYear = releaseDate;
        this.imgUrl = imgUrl;
        this.rating = rating;
        this.duration = duration;
    }

    public Movie() {
    }


    public void addUser(User user){
        this.userList.add(user);
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
