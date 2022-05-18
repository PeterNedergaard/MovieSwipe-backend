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
        Movie theBatman = new Movie("The Batman", "2022", "https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_.jpg", "8.0", "2h 56m");
        Movie moonKnight = new Movie("Moon Knight", "2022", "https://m.media-amazon.com/images/M/MV5BYTc5OWNhYjktMThlOS00ODUxLTgwNDQtZjdjYjkyM2IwZTZlXkEyXkFqcGdeQXVyNTA3MTU2MjE@._V1_.jpg", "7.5", "45m");
        Movie theGodfather = new Movie("The Godfather", "1972", "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg", "9.2", "2h 55m");
        Movie theDarkKnight = new Movie("The Dark Knight", "2008", "https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_.jpg", "9.0", "2h 32m");
        Movie forrestGump = new Movie("Forrest Gump", "1994", "https://m.media-amazon.com/images/M/MV5BNWIwODRlZTUtY2U3ZS00Yzg1LWJhNzYtMmZiYmEyNmU1NjMzXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_FMjpg_UX558_.jpg", "8.8", "2h 22m");
        Movie fightClub = new Movie("Fight Club", "1999", "https://m.media-amazon.com/images/M/MV5BNDIzNDU0YzEtYzE5Ni00ZjlkLTk5ZjgtNjM3NWE4YzA3Nzk3XkEyXkFqcGdeQXVyMjUzOTY1NTc@._V1_.jpg", "8.8", "2h 19m");

        User user1 = new User("Peter", "test123");
        User user2 = new User("Rabee", "test123");
        User user3 = new User("Mohammed", "test123");
        User admin = new User("admin", "test123");
        User both = new User("user_admin", "test123");

        Room room1 = new Room(user1,"1234","CoolRoom");
        Room room2 = new Room(user2,"4321","Movie Room");

        //owners
        UserRoom userRoom1 = new UserRoom(user1,room1);
        UserRoom userRoom2 = new UserRoom(user2, room2);

        //other members
        UserRoom userRoom3 = new UserRoom(user2,room1);
        UserRoom userRoom4 = new UserRoom(user3,room1);
        UserRoom userRoom5 = new UserRoom(user3,room2);


        //Movies
        UserMovie userMovie1 = new UserMovie(uncharted,true);
        UserMovie userMovie4 = new UserMovie(uncharted,true);
        UserMovie userMovie6 = new UserMovie(uncharted,true);

        UserMovie userMovie2 = new UserMovie(peacemaker,false);

        UserMovie userMovie3 = new UserMovie(spiderManNoWayHome,true);
        UserMovie userMovie5 = new UserMovie(spiderManNoWayHome,false);

        UserMovie userMovie7 = new UserMovie(theNorthman,true);
        UserMovie userMovie8 = new UserMovie(theNorthman,true);

        UserMovie userMovie9 = new UserMovie(theBatman,true);
        UserMovie userMovie10 = new UserMovie(theBatman,true);
        UserMovie userMovie11 = new UserMovie(theBatman,true);

        UserMovie userMovie12 = new UserMovie(moonKnight,true);
        UserMovie userMovie13 = new UserMovie(moonKnight,true);

        UserMovie userMovie14 = new UserMovie(theGodfather,true);
        UserMovie userMovie15 = new UserMovie(theGodfather,true);

        UserMovie userMovie16 = new UserMovie(theDarkKnight,true);
        UserMovie userMovie17 = new UserMovie(theDarkKnight,true);
        UserMovie userMovie18 = new UserMovie(theDarkKnight,true);

        UserMovie userMovie19 = new UserMovie(forrestGump,true);
        UserMovie userMovie20 = new UserMovie(forrestGump,true);

        UserMovie userMovie21 = new UserMovie(fightClub,true);
        UserMovie userMovie22 = new UserMovie(fightClub,true);



        user1.addToUserMovieList(userMovie1);
        user1.addToUserMovieList(userMovie2);
        user1.addToUserMovieList(userMovie3);
        user1.addToUserMovieList(userMovie9);
        user1.addToUserMovieList(userMovie14);
        user1.addToUserMovieList(userMovie16);
        user1.addToUserMovieList(userMovie19);

        user2.addToUserMovieList(userMovie4);
        user2.addToUserMovieList(userMovie5);
        user2.addToUserMovieList(userMovie7);
        user2.addToUserMovieList(userMovie10);
        user2.addToUserMovieList(userMovie12);
        user2.addToUserMovieList(userMovie17);
        user2.addToUserMovieList(userMovie21);

        user3.addToUserMovieList(userMovie6);
        user3.addToUserMovieList(userMovie8);
        user3.addToUserMovieList(userMovie11);
        user3.addToUserMovieList(userMovie13);
        user3.addToUserMovieList(userMovie15);
        user3.addToUserMovieList(userMovie18);
        user3.addToUserMovieList(userMovie20);
        user3.addToUserMovieList(userMovie22);




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
            em.persist(theBatman);
            em.persist(moonKnight);
            em.persist(theGodfather);
            em.persist(theDarkKnight);
            em.persist(forrestGump);
            em.persist(fightClub);

            em.persist(userMovie1);
            em.persist(userMovie2);
            em.persist(userMovie3);
            em.persist(userMovie4);
            em.persist(userMovie5);
            em.persist(userMovie6);
            em.persist(userMovie7);
            em.persist(userMovie8);
            em.persist(userMovie9);
            em.persist(userMovie10);
            em.persist(userMovie11);
            em.persist(userMovie12);
            em.persist(userMovie13);
            em.persist(userMovie14);
            em.persist(userMovie15);
            em.persist(userMovie16);
            em.persist(userMovie17);
            em.persist(userMovie18);
            em.persist(userMovie19);
            em.persist(userMovie20);
            em.persist(userMovie21);
            em.persist(userMovie22);

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
