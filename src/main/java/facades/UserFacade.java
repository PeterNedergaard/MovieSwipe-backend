package facades;

import entities.User;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * @author lam@cphbusiness.dk
 */
public class UserFacade {

    private static EntityManagerFactory emf;
    private static UserFacade instance;

    Facade facade = Facade.getFacade(EMF_Creator.createEntityManagerFactory());

    private UserFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static UserFacade getUserFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }

    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = facade.getUserByName(username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }

//    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
//        EntityManager em = emf.createEntityManager();
//        User user;
//        try {
//            user = em.find(User.class, username);
//            if (user == null || !user.verifyPassword(password)) {
//                throw new AuthenticationException("Invalid user name or password");
//            }
//        } finally {
//            em.close();
//        }
//        return user;
//    }

}
