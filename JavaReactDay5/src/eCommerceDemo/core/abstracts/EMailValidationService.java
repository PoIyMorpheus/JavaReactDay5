package eCommerceDemo.core.abstracts;

public interface EMailValidationService {
    void sendEMail(String eMail);
    boolean isValidationLinkClicked();
    boolean isEMailAlreadyExists(String eMail);
}
