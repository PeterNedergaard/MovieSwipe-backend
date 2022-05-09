package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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


    @OneToMany(mappedBy = "movie")
    List<UserMovie> userMovieList = new ArrayList<>();

//    @ManyToMany(mappedBy = "movieList")
//    private List<User> userList = new ArrayList<>();
//
//    @ManyToMany
//    private List <Dislike> dislikedListMovie = new ArrayList<>();



    public Movie(String title, String releaseDate, String imgUrl, String rating, String duration) {
        this.title = title;
        this.releaseYear = releaseDate;
        this.imgUrl = imgUrl;
        this.rating = rating;
        this.duration = duration;
    }




    public Movie() {
    }


//    public void addUser(User user){
//        this.userList.add(user);
//    }

    public void addToUserMovieList(UserMovie userMovie){
        this.userMovieList.add(userMovie);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserMovie> getUserMovieList() {
        return userMovieList;
    }

    public void setUserMovieList(List<UserMovie> userMovieList) {
        this.userMovieList = userMovieList;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title) && Objects.equals(releaseYear, movie.releaseYear) && Objects.equals(imgUrl, movie.imgUrl) && Objects.equals(rating, movie.rating) && Objects.equals(duration, movie.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, releaseYear, imgUrl, rating, duration);
    }

//    public void addDislike(UserMovie userMovie) {
//        this.dislikedListMovie.add(userMovie);
//    }
}
