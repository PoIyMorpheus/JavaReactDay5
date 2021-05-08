package eCommerceDemo.business.concretes;

import eCommerceDemo.business.abstracts.UserManagerService;
import eCommerceDemo.core.abstracts.EMailValidationService;
import eCommerceDemo.dataAccess.abstracts.UserDaoService;
import eCommerceDemo.entities.concretes.User;
import eCommerceDemo.jGoogleService.GoogleAccount;
import eCommerceDemo.jGoogleService.GoogleService;


public class UserManager implements UserManagerService {
    private UserDaoService userDaoService;
    private EMailValidationService eMailValidationService;
    private GoogleService googleService;

    public UserManager(UserDaoService userDaoService, EMailValidationService eMailValidationService, GoogleService googleService) {
        this.userDaoService = userDaoService;
        this.eMailValidationService = eMailValidationService;
        this.googleService = googleService;
    }

    @Override
    public void add(User user) {
        if (isEMailValid(user.geteMail()) && isNameValid(user.getFirstName()) &&
                isNameValid(user.getLastName()) && isPasswordValid(user.getPassword())) {

            eMailValidationService.sendEMail(user.geteMail());

            if (!eMailValidationService.isEMailAlreadyExists(user.geteMail())) {

                if (eMailValidationService.isValidationLinkClicked()){
                    userDaoService.add(user);
                    System.out.println(user.getFirstName() + " adlı kullanıcı veritabanına eklendi.");
                }
                else
                    System.out.println("Hata! " + user.getFirstName() + " adlı kullanıcı veritabanına eklenmedi, e-postanıza atılan doğrulama linkini onaylayınız.");

            } else
                System.out.println("Hata! " + user.getFirstName() + " adlı kullanıcı veritabanına eklenmedi, bu e-posta zaten kullanılıyor.");

        } else
            System.out.println("Hata! " + user.getFirstName() + " adlı kullanıcı veritabanına eklenmedi, lütfen kullanıcı bilgilerinizi kontrol ediniz.");

    }

    @Override
    public void update(User user, int id, String firstName, String lastName, String eMail, String password) {
        if (isEMailValid(eMail) && isNameValid(firstName) &&
                isNameValid(lastName) && isPasswordValid(password)) {

            if (userDaoService.getDatabase().contains(user)){
                userDaoService.update(user, id, firstName, lastName, eMail, password);
                System.out.println(user.getFirstName() + " adlı kullanıcı güncellendi.");
            }
            else
                System.out.println("Hata! " + user.getFirstName() + " adlı kullanıcı güncellenmedi veritabanında zaten böyle bir kullancı yok.");


        } else
            System.out.println("Hata! " + user.getFirstName() + " adlı kullanıcı güncellenemedi lütfen kullanıcı bilgilerinizi kontrol ediniz.");
    }

    @Override
    public void delete(User user) {
        if (userDaoService.getDatabase().contains(user)) {
            userDaoService.delete(user);
            System.out.println(user.getFirstName() + " adlı kullanıcı veritabanından silindi.");
        } else
            System.out.println("Hata! " + user.getFirstName() + " adlı kullanıcı silinmedi veritabanında zaten böyle bir kullancı yok.");


    }

    @Override
    public void signIn(String eMail, String password) {
        User user = userDaoService.checkEnteredInfos(eMail, password);
        if ( user != null){
            System.out.println(user.getFirstName()+" adlı kullanıcı başarıyla giriş yaptı.");
        }
        else System.out.println("Hatalı giriş yaptınız.");
    }

    @Override
    public boolean isPasswordValid(String password) {
        return (password.length() >= 6);
    }

    @Override
    public boolean isEMailValid(String eMail) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(eMail);
        return m.matches();
    }

    @Override
    public boolean isNameValid(String name) {
        return (name.length() >= 2);
    }

    public void signUpWithGoogle(int id,GoogleAccount googleAccount){
        googleService.signUpWithGoogle(googleAccount);
        add(new User(id,googleAccount.getFirstName(),googleAccount.getLastName(),googleAccount.geteMail(),googleAccount.getPassword()));
    }

}
