import errorhandling.IdNotFoundException;
import facades.Facade;
import utils.EMF_Creator;

public class TestMain {

    public static void main(String[] args) throws IdNotFoundException {

        Facade facade = Facade.getFacade(EMF_Creator.createEntityManagerFactory());

        facade.likedMoviesByUserId(1L);

    }

}
