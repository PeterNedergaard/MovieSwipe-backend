package facades;


import entities.Joke;
import com.google.gson.Gson;
import utils.HttpUtils;
import utils.ParallelJokes;

import java.util.ArrayList;
import java.util.List;

public class JokeFacade {


    public String jokeFetcher(String URL){
        String jokeStr;
        String jokeJson = "";
        Gson gson = new Gson();

        try{
            jokeJson = HttpUtils.fetchData(URL);
        } catch (Exception e){
            e.printStackTrace();
        }

        Joke joke = gson.fromJson(jokeJson, Joke.class);

        if (joke.getJoke() == null){
            jokeStr = joke.getValue();
        } else {
            jokeStr = joke.getJoke();
        }

        return jokeStr;
    }





    public static void main(String[] args) {

        JokeFacade facade = new JokeFacade();

        //System.out.println(facade.jokeFetcher("https://api.chucknorris.io/jokes/random"));
        //System.out.println(facade.jokeFetcher("https://icanhazdadjoke.com"));

        ParallelJokes parallelJokes = new ParallelJokes();

        List<String> jokeList = new ArrayList<>();

        try{
            jokeList = parallelJokes.getJokes();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String s : jokeList) {
            System.out.println(s);
        }

    }


}
