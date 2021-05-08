package eCommerceDemo.business.abstracts;

import eCommerceDemo.entities.concretes.User;

public interface UserManagerService {
    void add(User user);
    void update(User user, int id, String firstName, String lastName, String eMail, String password);
    void delete(User user);
    void signIn(String eMail,String password);

    boolean isPasswordValid(String password);
    boolean isEMailValid(String eMail);
    boolean isNameValid(String name);

}
