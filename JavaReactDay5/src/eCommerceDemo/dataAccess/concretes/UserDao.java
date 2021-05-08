package eCommerceDemo.dataAccess.concretes;

import eCommerceDemo.dataAccess.abstracts.UserDaoService;
import eCommerceDemo.entities.concretes.User;

import java.util.ArrayList;

public class UserDao implements UserDaoService {
    private ArrayList<User> database;

    public UserDao(ArrayList<User> database) {
        this.database = database;
    }

    @Override
    public void add(User user) {
        database.add(user);

    }

    @Override
    public void update(User user, int id, String firstName, String lastName, String eMail, String password) {
        int indexOfUser = database.indexOf(user);

        database.get(indexOfUser).setId(id);
        database.get(indexOfUser).setFirstName(firstName);
        database.get(indexOfUser).setLastName(lastName);
        database.get(indexOfUser).seteMail(eMail);
        database.get(indexOfUser).setPassword(password);


    }


    @Override
    public void delete(User user) {
        database.remove(user);

    }

    @Override
    public User checkEnteredInfos(String eMail, String password) {
        for(User user : database){
            if (user.geteMail().equals(eMail) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    @Override
    public ArrayList<User> getDatabase() {
        return database;
    }
}
