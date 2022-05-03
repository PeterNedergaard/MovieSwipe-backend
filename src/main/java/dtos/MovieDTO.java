package dtos;

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


}
