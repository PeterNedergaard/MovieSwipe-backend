package facades;

import dtos.MovieDTO;
import entities.Movie;
import entities.Role;
import entities.User;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FacadeTest {
    static EntityManagerFactory emf;
    static EntityManager em;
    Facade facade = Facade.getFacade(EMF_Creator.createEntityManagerFactoryForTest());



    @BeforeAll
    public static void setUp(){

        emf = EMF_Creator.createEntityManagerFactoryForTest();
        em = emf.createEntityManager();

        Movie uncharted = new Movie("Uncharted", "2022", "https://m.media-amazon.com/images/M/MV5BMWEwNjhkYzYtNjgzYy00YTY2LThjYWYtYzViMGJkZTI4Y2MyXkEyXkFqcGdeQXVyNTM0OTY1OQ@@._V1_.jpg", "6.4", "1h 56m");
        Movie peacemaker = new Movie("Peacemaker", "2022", "https://m.media-amazon.com/images/M/MV5BODk2NjAyOWMtM2FjZC00MjZhLTkxMjQtZTM3NjJlYTE5MDdlXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_.jpg", "8.4", "40m");
        Movie theNorthman = new Movie("The Northman", "2022", "https://m.media-amazon.com/images/M/MV5BMzVlMmY2NTctODgwOC00NDMzLWEzMWYtM2RiYmIyNTNhMTI0XkEyXkFqcGdeQXVyNTAzNzgwNTg@._V1_.jpg", "7.8", "2h 17m");
        Movie spiderManNoWayHome = new Movie("Spider-Man: No Way Home", "2021", "https://m.media-amazon.com/images/M/MV5BZWMyYzFjYTYtNTRjYi00OGExLWE2YzgtOGRmYjAxZTU3NzBiXkEyXkFqcGdeQXVyMzQ0MzA0NTM@._V1_.jpg", "8.4", "2h 28m");
        User user1 = new User("Peter", "test123");
        User user2 = new User("Rabee", "test123");
        User user3 = new User("Mohammed", "test123");
        User admin = new User("admin", "test123");
        User both = new User("user_admin", "test123");
        user1.addMovie(uncharted);
        user1.addMovie(peacemaker);
        user1.addMovie(spiderManNoWayHome);
        user2.addMovie(uncharted);
        user2.addMovie(spiderManNoWayHome);
        user3.addMovie(uncharted);


        em.getTransaction().begin();
        Role userRole = new Role("user");
        Role adminRole = new Role("admin");
        user1.addRole(userRole);
        user2.addRole(userRole);
        user3.addRole(userRole);
        admin.addRole(adminRole);
        both.addRole(userRole);
        both.addRole(adminRole);
        em.persist(userRole);
        em.persist(adminRole);
        em.persist(user1);
        em.persist(user2);
        em.persist(user3);
        em.persist(admin);
        em.persist(both);
        em.persist(uncharted);
        em.persist(peacemaker);
        em.persist(theNorthman);
        em.persist(spiderManNoWayHome);

        em.getTransaction().commit();
    }

    @AfterAll
    public static void tearDown() {
        em.close();
        emf.close();
    }




    @Test
    void likedMoviesByUserIdTest() {
        System.out.println("Test Liked movies by user id");
        List<MovieDTO> expected = new ArrayList<>();
        expected.add(new MovieDTO("Uncharted","2022","https://m.media-amazon.com/images/M/MV5BMWEwNjhkYzYtNjgzYy00YTY2LThjYWYtYzViMGJkZTI4Y2MyXkEyXkFqcGdeQXVyNTM0OTY1OQ@@._V1_.jpg","6.4","1h 56m"));
        expected.add(new MovieDTO("Spider-Man: No Way Home","2021","https://m.media-amazon.com/images/M/MV5BZWMyYzFjYTYtNTRjYi00OGExLWE2YzgtOGRmYjAxZTU3NzBiXkEyXkFqcGdeQXVyMzQ0MzA0NTM@._V1_.jpg","8.4","2h 28m"));

        User user = facade.getUserByName("Rabee");

        List<MovieDTO> actual = facade.likedMoviesByUserId(user.getId());

        assertEquals(expected,actual);
    }

    @Test
    void getAllMovies(){
        System.out.println("Test getAllMovies()");
        int expected = 4;
        int actual = facade.getAllMovies().size();

        assertEquals(expected, actual);
    }
}