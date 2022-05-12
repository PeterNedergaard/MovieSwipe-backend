import entities.User;
import errorhandling.IdNotFoundException;
import facades.Facade;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class TestMain {

    public static void main(String[] args) throws IdNotFoundException {

        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        Facade facade = Facade.getFacade(EMF_Creator.createEntityManagerFactory());

        //facade.likedMoviesByUserId(1L);
        //System.out.println(facade.getLikedMoviesByRoomCode("4321"));
        System.out.println(facade.getRoomsByUser(em.find(User.class, 3L)).toString());
//        facade.createRoom(em.find(User.class,1L),"4444","Mo123");
    }

}
