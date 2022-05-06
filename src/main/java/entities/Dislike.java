package entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Dislike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany(mappedBy = "dislikedListMovie")
    private List<Movie> dislikedMovieList;

    @ManyToMany(mappedBy = "dislikedListUser")
    private List<User> dislikedMoviesUserList;

    public void addUser(User user){
        this.dislikedMoviesUserList.add(user);
        user.addDislike(this);
    }

    public void addMovie(Movie movie){
        this.dislikedMovieList.add(movie);
        movie.addDislike(this);
    }


    public Dislike(List<Movie> dislikedMovieList, List<User> dislikedMoviesUserList) {
        this.dislikedMovieList = dislikedMovieList;
        this.dislikedMoviesUserList = dislikedMoviesUserList;
    }

    public Dislike() {
    }

    public List<Movie> getDislikedMovieList() {
        return dislikedMovieList;
    }

    public void setDislikedMovieList(List<Movie> dislikedMovieList) {
        this.dislikedMovieList = dislikedMovieList;
    }

    public List<User> getDislikedMoviesUserList() {
        return dislikedMoviesUserList;
    }

    public void setDislikedMoviesUserList(List<User> userList) {
        this.dislikedMoviesUserList = userList;
    }
}
