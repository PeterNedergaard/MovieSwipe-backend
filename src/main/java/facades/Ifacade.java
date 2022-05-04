package facades;

import entities.Movie;

import java.util.List;

public interface Ifacade {

    List<Movie> likedMoviesByUserId(Long id);
//

}
