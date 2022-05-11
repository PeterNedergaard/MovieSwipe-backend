package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dtos.MovieDTO;
import entities.Movie;
import entities.Room;
import entities.User;
import errorhandling.API_Exception;
import errorhandling.IdNotFoundException;
import facades.Facade;
import utils.EMF_Creator;
import utils.ParallelJokes;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Path("info")
public class Resource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Facade facade = Facade.getFacade(EMF_Creator.createEntityManagerFactory());

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }

    //Just to verify if the database is setup
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String allUsers() {

        EntityManager em = EMF.createEntityManager();
        try {
            TypedQuery<User> query = em.createQuery ("select u from User u",entities.User.class);
            List<User> users = query.getResultList();
            return "[" + users.size() + "]";
        } finally {
            em.close();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed("user")
    public String getFromUser() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to (user): " + thisuser + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to (admin): " + thisuser + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("jokes")
    public String getJokes() {

        List<String> jokeList = new ArrayList<>();

        try{
            jokeList = ParallelJokes.getJokes();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return gson.toJson(jokeList);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("movies")
    public Response getAllMovies() {

        return Response
                .ok()
                .entity(gson.toJson(facade.getAllMovies()))
                .build();
    }


    @GET
    @Path("likedmovies/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getLikedMoviesByUserId(@PathParam("id") Long id)throws IdNotFoundException
    {
        return Response
                .ok()
                .entity(gson.toJson(facade.likedMoviesByUserId(id)))
                .build();

    }


    @POST
    @Path("likeordislike")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void movieInteraction(String jsonString) throws API_Exception {

        EntityManager em = EMF.createEntityManager();

        Long movieId;
        String userName;
        boolean isLiked;

        User user;
        Movie movie;

        try {
            JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
            movieId = Long.valueOf(json.get("movieid").getAsString());
            userName = json.get("username").getAsString();
            isLiked = Boolean.parseBoolean(json.get("isliked").getAsString());

            user = facade.getUserByName(userName);
            movie = em.find(Movie.class,movieId);

        } catch (Exception e) {
            throw new API_Exception("Malformed JSON Suplied",400,e);
        }

        facade.addMovieInteraction(movie,user,isLiked);
    }


    @POST
    @Path("createroom")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void createRoom(String jsonString) throws API_Exception {
        EntityManager em = EMF.createEntityManager();

        User owner;
        String roomCode;
        String userName;

        try {
            JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
            userName = json.get("username").getAsString();
            roomCode = json.get("roomcode").getAsString();

            owner = facade.getUserByName(userName);

        } catch (Exception e) {
            throw new API_Exception("Malformed JSON Suplied", 400, e);
        }
        facade.createRoom(owner,roomCode);
    }


    @POST
    @Path("addtoroom")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void addUserToRoom(String jsonString) throws API_Exception {
        EntityManager em = EMF.createEntityManager();

        User user;
        Room room;
        String userName;
        String roomCode;

        try {
            JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
            userName = json.get("username").getAsString();
            roomCode = json.get("roomcode").getAsString();

            user = facade.getUserByName(userName);
            room= facade.getRoomByRoomCode(roomCode);

        } catch (Exception e) {
            throw new API_Exception("Malformed JSON Suplied", 400, e);
        }
        facade.addUserToRoom(user,room);
    }


    @GET
    @Path("roomlikes/{roomcode}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getLikedMoviesByRoomCode(@PathParam("roomcode") String roomCode)throws IdNotFoundException
    {

        List<MovieDTO> result = facade.getLikedMoviesByRoomCode(roomCode);

        return Response
                .ok()
                .entity(gson.toJson(result))
                .build();

    }

}

