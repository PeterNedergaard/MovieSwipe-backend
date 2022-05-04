package facades;

import entities.Movie;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Facade implements Ifacade {

    private static EntityManagerFactory emf;
    private static Facade instance;
//
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
    public List<Movie> likedMoviesByUserId(Long id) {
        EntityManager em = emf.createEntityManager();


        return null;
    }
}
