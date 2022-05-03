package entities;

public class Joke {

    private String joke;
    private String value;

    public Joke(String joke, String value) {
        this.joke = joke;
        this.value = value;
    }


    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
