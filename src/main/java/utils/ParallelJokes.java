package utils;

import facades.JokeFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


class JokeURL implements Callable<String> {
    String url;
    JokeFacade facade = new JokeFacade();

    JokeURL(String url) {
        this.url = url;
    }

    @Override
    public String call() {
        return facade.jokeFetcher(url);
    }
}


public class ParallelJokes {

    public static List<String> getJokes() throws ExecutionException, InterruptedException {
        List<String> results = new ArrayList<>();
        List<Future<String>> futures = new ArrayList<>();

        ExecutorService ex = Executors.newCachedThreadPool();

        Future<String> fut1 = ex.submit(new JokeURL("https://api.chucknorris.io/jokes/random"));
        Future<String> fut2 = ex.submit(new JokeURL("https://icanhazdadjoke.com"));
        futures.add(fut1);
        futures.add(fut2);

        for (Future<String> future : futures) {
            String response = future.get();
            results.add(response);
        }

        return results;
    }
}
