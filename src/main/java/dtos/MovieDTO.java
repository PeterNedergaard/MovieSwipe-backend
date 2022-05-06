package dtos;

import entities.Movie;

import java.util.*;

public class MovieDTO {

    private String title;
    private String releaseYear;
    private String imgUrl;
    private String rating;
    private String duration;

    public MovieDTO(String title, String releaseYear, String imgUrl, String rating, String duration) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.imgUrl = imgUrl;
        this.rating = rating;
        this.duration = duration;
    }

    public MovieDTO(Movie movie) {
        this.title = movie.getTitle();
        this.releaseYear = movie.getReleaseYear();
        this.imgUrl = movie.getImgUrl();
        this.rating = movie.getRating();
        this.duration = movie.getDuration();
    }

    public static List<MovieDTO> getMovieDTOS(List<Movie> movies){
        List<MovieDTO> movieDTOS = new ArrayList<>();
        movies.forEach(p->movieDTOS.add(new MovieDTO(p)));
        return movieDTOS;
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
    public String toString() {
        return "MovieDTO{" +
                "title='" + title + '\'' +
                ", releaseYear='" + releaseYear + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", rating='" + rating + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDTO movieDTO = (MovieDTO) o;
        return Objects.equals(title, movieDTO.title) && Objects.equals(releaseYear, movieDTO.releaseYear) && Objects.equals(imgUrl, movieDTO.imgUrl) && Objects.equals(rating, movieDTO.rating) && Objects.equals(duration, movieDTO.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, releaseYear, imgUrl, rating, duration);
    }
}
