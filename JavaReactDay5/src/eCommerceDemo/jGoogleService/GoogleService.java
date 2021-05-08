package eCommerceDemo.jGoogleService;

public class GoogleService {
    public String[] signUpWithGoogle(GoogleAccount googleAccount){
        String[] informations = {googleAccount.getFirstName(),googleAccount.getLastName(),googleAccount.geteMail()};
        return  informations;
    }
}
