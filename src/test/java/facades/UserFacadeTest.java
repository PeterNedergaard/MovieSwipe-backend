package facades;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

import static org.junit.jupiter.api.Assertions.*;

class UserFacadeTest {

    UserFacade facade = UserFacade.getUserFacade(EMF_Creator.createEntityManagerFactory());

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


//    @Test
//    void getVeryfiedUser() throws AuthenticationException {
//
//        String expected = "user";
//        String actual = facade.getVeryfiedUser("user","test123").getUserName();
//
//        assertEquals(expected,actual);
//    }
}