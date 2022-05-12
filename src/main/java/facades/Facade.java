package facades;

import dtos.MovieDTO;
import entities.*;
import errorhandling.IdNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Facade implements Ifacade {

    private static EntityManagerFactory emf;
    private static Facade instance;

    private Facade() {
    }

    public static Facade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new Facade();
        }
        return instance;
    }


    @Override
    public List<MovieDTO> likedMoviesByUserId(Long id) throws IdNotFoundException {
        EntityManager em = emf.createEntityManager();

        List<Movie> movieList = new ArrayList<>();
        User user = em.find(User.class, id);

        if (user.getId() == null) {
            throw new IdNotFoundException("id not found");
        }

        List<UserMovie> userMovieList = em.createQuery("SELECT um FROM UserMovie um WHERE um.user.id = :id", UserMovie.class)
                .setParameter("id", id).getResultList();


        for (UserMovie um : userMovieList) {
            if (um.isLiked()) {
                movieList.add(um.getMovie());
            }
        }

        return MovieDTO.getMovieDTOS(movieList);
    }


    @Override
    public List<MovieDTO> getAllMovies() {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m", Movie.class);
        List<Movie> movieList = query.getResultList();

        return MovieDTO.getMovieDTOS(movieList);
    }


    @Override
    public User getUserByName(String Name) {
        EntityManager em = emf.createEntityManager();

        List<User> userList = em.createQuery("SELECT u FROM User u", User.class).getResultList();

        for (User u : userList) {
            if (u.getUserName().equals(Name)) {
                return u;
            }
        }

        return null;
    }


    @Override
    public Long getUserIdByUserName(String userName) {
        EntityManager em = emf.createEntityManager();

        Long userId = em.createQuery("SELECT u FROM User u where u.userName=:userName", User.class)
                .setParameter("userName", userName).getSingleResult().getId();


        return userId;
    }


    @Override
    public Movie addMovieInteraction(Movie movie, User user, boolean isLiked) {
        EntityManager em = emf.createEntityManager();

        UserMovie userMovie = new UserMovie(user, movie, isLiked);

        try {
            em.getTransaction().begin();

            em.persist(userMovie);

            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return movie;
    }


    public Room createRoom(User owner, String roomCode, String roomName) {
        EntityManager em = emf.createEntityManager();

        Room room = new Room(owner, roomCode, roomName);
        UserRoom userRoom = new UserRoom(owner,room);

        owner.addToUserRoomList(userRoom);

        try {
            em.getTransaction().begin();

            em.persist(room);
            em.persist(userRoom);
            em.merge(owner);

            em.getTransaction().commit();
        } finally {
            em.close();
        }


        return room;
    }


    public User addUserToRoom(User user, Room room) {
        EntityManager em = emf.createEntityManager();
        UserRoom userRoom = new UserRoom(user, room);
        try {

            em.getTransaction().begin();
            em.persist(userRoom);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return user;
    }


    public Room getRoomByRoomCode(String roomCode) {
        EntityManager em = emf.createEntityManager();
        Room room = em.createQuery("select r from Room r where r.roomCode= :roomCode", Room.class)
                .setParameter("roomCode", roomCode).getSingleResult();


        return room;
    }


    public List<MovieDTO> getLikedMoviesByRoomCode(String roomCode) throws IdNotFoundException {

        EntityManager em = emf.createEntityManager();
        Room room = getRoomByRoomCode(roomCode);
        List<Long> usersInRoom = em.createQuery("select u.user.id from UserRoom u where u.room.roomCode= :roomCode", Long.class)
                .setParameter("roomCode", roomCode).getResultList();

        Set<MovieDTO> moviesList = new HashSet<>();

        for (Long ur : usersInRoom) {
            for (MovieDTO m : likedMoviesByUserId(ur)) {
                moviesList.add(m);
            }
        }

        List<MovieDTO> hashedList = new ArrayList<>(moviesList);

        List<MovieDTO> resultList = new ArrayList<>();


        for (MovieDTO movie : hashedList) {
            List<Long> usersWhoLikeSpecificMovie = em.createQuery("select um.user.id from UserMovie um where um.movie.id = :movieid", Long.class)
                    .setParameter("movieid", movie.getId()).getResultList();

            if (usersWhoLikeSpecificMovie.containsAll(usersInRoom) == true) {
                resultList.add(movie);
            }
        }


        return resultList;
    }


//    public List<Room>

//    public List<MovieDTO> getRoomSwipeListByRoomCode(String roomCode, Long userId) throws IdNotFoundException {
//
//        EntityManager em = emf.createEntityManager();
//        Room room = getRoomByRoomCode(roomCode);
//        List<Long> usersInRoom = em.createQuery("select u.user.id from UserRoom u where u.room.roomCode= :roomCode", Long.class)
//                .setParameter("roomCode", roomCode).getResultList();
//
//        Set<MovieDTO> moviesList = new HashSet<>();
//
//        for (int i = 0; i < usersInRoom.size(); i++) {
//            if (usersInRoom.get(i) == userId){
//                usersInRoom.remove(i);
//            }
//        }
//
//        for (Long ur : usersInRoom) {
//            for (MovieDTO m : likedMoviesByUserId(ur)) {
//                moviesList.add(m);
//            }
//        }
//        List<MovieDTO> hashedList = new ArrayList<>(moviesList);
//
//        List<MovieDTO> resultList = new ArrayList<>();
//
//        for (MovieDTO movie : hashedList) {
//            List<Long> usersWhoLikeSpecificMovie = em.createQuery("select um.user.id from UserMovie um where um.movie.id = :movieid and um.liked = true", Long.class)
//                    .setParameter("movieid", movie.getId()).getResultList();
//            if (usersWhoLikeSpecificMovie.containsAll(usersInRoom) == true) {
//                resultList.add(movie);
//            }
//        }
//        return resultList;
//    }
}
