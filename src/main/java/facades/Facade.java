package facades;

import dtos.MovieDTO;
import entities.Movie;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

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
    public List<Movie> likedMoviesByUserId(Long id) {
        EntityManager em = emf.createEntityManager();
        List<Long> query= em.createQuery("select m from Movie m left join fetch m.id ul where ul = :id")
                .setParameter("id",id)
                .getResultList();
        return null;
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m", Movie.class);
        List<Movie> movieList = query.getResultList();
        System.out.println(movieList.toString());
        return MovieDTO.getMovieDTOS(movieList);
    }
}
