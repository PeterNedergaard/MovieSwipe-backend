import entities.User;
import errorhandling.IdNotFoundException;
import facades.Facade;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class TestMain {

    public static void main(String[] args) throws IdNotFoundException, UnsupportedEncodingException {

        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        Facade facade = Facade.getFacade(EMF_Creator.createEntityManagerFactory());

        //facade.likedMoviesByUserId(1L);
        //System.out.println(facade.getLikedMoviesByRoomCode("4321"));
//        System.out.println(facade.getRoomsByUser(em.find(User.class, 3L)).toString());
//        facade.createRoom(em.find(User.class,1L),"4444","Mo123");
//        System.out.println(facade.getUsersByRoomCode("1234"));

//        System.out.println(facade.getRoomMembersLikedMovies("1234",em.find(User.class,2L)));

        String decoded = URLDecoder.decode("%7B%22username%22%3A%22Peter%22%2C%22roomcode%22%3A%221234%22%7D", "UTF8");
        System.out.println(decoded);
    }

}
