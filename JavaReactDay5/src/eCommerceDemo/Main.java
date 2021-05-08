package eCommerceDemo;

import eCommerceDemo.business.concretes.UserManager;
import eCommerceDemo.business.abstracts.UserManagerService;
import eCommerceDemo.core.abstracts.EMailValidationService;
import eCommerceDemo.core.concretes.EMailValidationManager;
import eCommerceDemo.dataAccess.abstracts.UserDaoService;
import eCommerceDemo.dataAccess.concretes.UserDao;
import eCommerceDemo.entities.concretes.User;
import eCommerceDemo.jGoogleService.GoogleAccount;
import eCommerceDemo.jGoogleService.GoogleService;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<User> database = new ArrayList<>();

        UserDaoService userDaoService = new UserDao(database);
        EMailValidationService eMailValidationService = new EMailValidationManager(database);
        GoogleService googleService = new GoogleService();
        UserManager userManagerService = new UserManager(userDaoService,eMailValidationService, googleService);

        //Without google
        User wrongUser1 = new User(1,"Recep Batuhan", "Dikmen", "batuhan@gmail.com", "12345");
        userManagerService.add(wrongUser1);

        User wrongUser2 = new User(1,"Recep Batuhan", "Dikmen", "batuhan_gmail.com", "123456789");
        userManagerService.add(wrongUser2);

        User wrongUser3 = new User(1,"Recep Batuhan", "D", "batuhan@gmail.com", "123456789");
        userManagerService.add(wrongUser3);

        User wrongUser4 = new User(1,"B", "Dikmen", "batuhan@gmail.com", "123456789");
        userManagerService.add(wrongUser4);

        User user = new User(1,"Recep Batuhan", "Dikmen", "same_mail@gmail.com", "123456789");
        userManagerService.add(user);

        User wrongUser5 = new User(1,"Engin", "Demiroğ", "same_mail@gmail.com", "123456789");
        userManagerService.add(wrongUser5);

        userManagerService.update(user,2,"Engin", "Demiroğ", "engdmrg@gmail.com", "abcde12345");

        userManagerService.delete(user);

        System.out.println("---------------WITH GOOGLE-----------------");

        //With google
        GoogleAccount googleAccount = new GoogleAccount("Recep Batuhan", "Dikmen", "batuhan@gmail.com","123456789");

        userManagerService.signUpWithGoogle(3, googleAccount);

        userManagerService.signIn("batuhan@gmail.com","123456");
        userManagerService.signIn("batuhan@gmail.com","123456789");

    }
}
