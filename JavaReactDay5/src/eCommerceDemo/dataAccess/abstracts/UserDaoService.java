package eCommerceDemo.dataAccess.abstracts;

import eCommerceDemo.entities.concretes.User;

import java.util.ArrayList;

public interface UserDaoService {
    void add(User user);
    void update(User user, int id, String firstName, String lastName, String eMail, String password);
    void delete(User user);
    User checkEnteredInfos(String eMail, String password);
    ArrayList<User> getDatabase();
}
