package facades;

import dtos.MovieDTO;
import entities.Movie;
import entities.Room;
import entities.User;
import errorhandling.IdNotFoundException;

import java.util.List;

public interface Ifacade {

    List<MovieDTO> likedMoviesByUserId(Long id)throws IdNotFoundException;
    List<MovieDTO> getAllMovies();
    User getUserByName(String Name);
    Long getUserIdByUserName(String userName);
    Movie addMovieInteraction(Movie movie, User user, boolean isLiked);
    Room createRoom(User owner, String roomCode);
    User addUserToRoom(User user,Room room);
    Room getRoomByRoomCode(String roomCode);
    List<MovieDTO> getLikedMoviesByRoomCode(String roomCode) throws IdNotFoundException;

}
