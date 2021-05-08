package eCommerceDemo.core.concretes;

import eCommerceDemo.core.abstracts.EMailValidationService;
import eCommerceDemo.entities.concretes.User;

import java.util.ArrayList;
import java.util.Scanner;

public class EMailValidationManager implements EMailValidationService {
    private boolean isValidationLinkClicked = false;
    private Scanner sc = new Scanner(System.in);
    private ArrayList<User> database;

    public EMailValidationManager(ArrayList<User> database) {
        this.database = database;
    }

    @Override
    public void sendEMail(String eMail) {
        System.out.println(eMail + " adlı adrese doğrulama linki gönderildi kayıt olmak için linke tıklayınız.");

        System.out.println("Doğrulama linkine tıkla (y/n)");
        String isClicked = sc.nextLine();

        if (isClicked.equals("y")) {
            isValidationLinkClicked = true;
        }
    }

    @Override
    public boolean isValidationLinkClicked() {
        return isValidationLinkClicked;
    }

    @Override
    public boolean isEMailAlreadyExists(String eMail) {
        for(User user: database){
            if (user.geteMail().equals(eMail)){
                return true;
            }
        }

        return false;
    }
}
