package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Movie {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String title;
    private String releaseYear;
    private String imgUrl;
    private String rating;
    private String duration;

    public Movie(String title, String releaseDate, String imgUrl, String rating, String duration) {
        this.title = title;
        this.releaseYear = releaseDate;
        this.imgUrl = imgUrl;
        this.rating = rating;
        this.duration = duration;
    }

    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
