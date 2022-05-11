import entities.*;
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

        Room room1 = new Room(user1,"1234");
        Room room2 = new Room(user2,"4321");

        UserRoom userRoom1 = new UserRoom(user1,room1);
        UserRoom userRoom2 = new UserRoom(user2, room2);

        UserRoom userRoom3 = new UserRoom(user2,room1);
        UserRoom userRoom4 = new UserRoom(user3,room1);
        UserRoom userRoom5 = new UserRoom(user3,room2);

        UserMovie userMovie1 = new UserMovie(uncharted,true);
        UserMovie userMovie2 = new UserMovie(peacemaker,false);
        UserMovie userMovie3 = new UserMovie(spiderManNoWayHome,true);
        UserMovie userMovie4 = new UserMovie(uncharted,true);
        UserMovie userMovie5 = new UserMovie(spiderManNoWayHome,false);
        UserMovie userMovie6 = new UserMovie(uncharted,true);

        user1.addToUserMovieList(userMovie1);
        user1.addToUserMovieList(userMovie2);
        user1.addToUserMovieList(userMovie3);
        user2.addToUserMovieList(userMovie4);
        user2.addToUserMovieList(userMovie5);
        user3.addToUserMovieList(userMovie6);




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

            em.persist(userMovie1);
            em.persist(userMovie2);
            em.persist(userMovie3);
            em.persist(userMovie4);
            em.persist(userMovie5);
            em.persist(userMovie6);

            em.persist(room1);
            em.persist(room2);

            em.persist(userRoom1);
            em.persist(userRoom2);
            em.persist(userRoom3);
            em.persist(userRoom4);
            em.persist(userRoom5);


            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

}
