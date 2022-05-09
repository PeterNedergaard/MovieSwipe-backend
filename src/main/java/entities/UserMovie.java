package entities;

import javax.persistence.*;

@Entity
public class UserMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "liked")
    private boolean liked;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;


    public UserMovie(User user, Movie movie, boolean liked) {
        this.user = user;
        this.movie = movie;
        this.liked = liked;
    }

    public UserMovie(Movie movie, boolean liked) {
        this.movie = movie;
        this.liked = liked;
    }

    public UserMovie() {
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }


    @Override
    public String toString() {
        return "UserMovie{" +
                "id = " + id +
                ", liked = " + liked +
                ", user = " + user +
                ", movie = " + movie +
                '}';
    }
}
