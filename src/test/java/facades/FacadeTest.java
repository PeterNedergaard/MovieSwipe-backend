package facades;

import entities.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FacadeTest {
    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactoryForTest();
    EntityManager em = emf.createEntityManager();
    Facade facade = Facade.getFacade(EMF_Creator.createEntityManagerFactoryForTest());

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void likedMoviesByUserIdTest() {
        System.out.println("Test Liked movies by user id");
        List<Movie> expected = new ArrayList<>();
        expected.add(new Movie("Uncharted","2022","https://m.media-amazon.com/images/M/MV5BMWEwNjhkYzYtNjgzYy00YTY2LThjYWYtYzViMGJkZTI4Y2MyXkEyXkFqcGdeQXVyNTM0OTY1OQ@@._V1_.jpg","6.4","1h 56m"));
        expected.add(new Movie("Spider-Man: No Way Home","2021","https://m.media-amazon.com/images/M/MV5BZWMyYzFjYTYtNTRjYi00OGExLWE2YzgtOGRmYjAxZTU3NzBiXkEyXkFqcGdeQXVyMzQ0MzA0NTM@._V1_.jpg","8.4","2h 28m"));
//        List<Movie> actual = facade.likedMoviesByUserId(2L);

    }

}