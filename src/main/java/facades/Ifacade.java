package facades;

import dtos.MovieDTO;
import entities.Movie;
import entities.User;
import errorhandling.IdNotFoundException;

import java.util.List;

public interface Ifacade {

    List<MovieDTO> likedMoviesByUserId(Long id)throws IdNotFoundException;
    List<MovieDTO> getAllMovies();
    User getUserByName(String Name);
    Long getUserIdByUserName(String userName);


}
