import entities.Dislike;
import entities.Movie;
import entities.Role;
import entities.User;
import facades.Facade;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class Generate {

    public static void main(String[] args) {

//        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactoryForTest();
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

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

        List<Movie> movies = new ArrayList<>();
        movies.add(uncharted);
        movies.add(peacemaker);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);


        Dislike dislike = new Dislike(movies,users);
//        dislike.addMovie(uncharted);
//        dislike.addUser(user3);
//        dislike.addMovie(peacemaker);
//        dislike.addUser(user2);


        try {
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
            em.persist(dislike);


            em.getTransaction().commit();
        } finally {
            em.close();
        }

//        em = emf.createEntityManager();
//
//        try {
//            em.getTransaction().begin();
//
//            Dislike dislike1 = new Dislike(1L, 1L);
//            Dislike dislike2 = new Dislike(1L, 2L);
//
//            em.persist(dislike1);
//            em.persist(dislike2);
//
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }

    }

}
