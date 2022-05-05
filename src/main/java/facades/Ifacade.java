package facades;

import dtos.MovieDTO;
import entities.Movie;

import java.util.List;

public interface Ifacade {

    List<MovieDTO> likedMoviesByUserId(Long id);
    List<MovieDTO> getAllMovies();


}
