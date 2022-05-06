package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.MovieDTO;
import entities.Movie;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

class ResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static Movie movie1,movie2;
    private static MovieDTO movie1DTO,movie2DTO;


    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();

        movie1 = new Movie("Uncharted","2022","https://m.media-amazon.com/images/M/MV5BMWEwNjhkYzYtNjgzYy00YTY2LThjYWYtYzViMGJkZTI4Y2MyXkEyXkFqcGdeQXVyNTM0OTY1OQ@@._V1_.jpg","6.4","1h 56m");
        movie2 = new Movie("Peacemaker","2022","https://m.media-amazon.com/images/M/MV5BODk2NjAyOWMtM2FjZC00MjZhLTkxMjQtZTM3NjJlYTE5MDdlXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_.jpg","8.4","40m");


        try {
            em.getTransaction().begin();

            em.persist(movie1);
            em.persist(movie2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }

        movie1DTO = new MovieDTO(movie1);
        movie1DTO = new MovieDTO(movie2);

    }

    @AfterEach
    void tearDown() {
    }


//    @Test
//    public void testServerIsUp() {
//        System.out.println("Testing is server up");
//        given()
//                .contentType("application/json")
//                .when()
//                .get("/info")
//                .then()
//                .statusCode(200);
//    }
//
//
//    @Test
//    void getAllMovies() {
//        System.out.println("Testing to get all movies");
//        List<MovieDTO> actualMovieDTOList = given()
//                .contentType("application/json")
//                .when()
//                .get("/info/movies")
//                .then()
//                .extract().body().jsonPath().getList("", MovieDTO.class);
//        assertThat(actualMovieDTOList, containsInAnyOrder(movie1DTO,movie2DTO));
//
//    }


}