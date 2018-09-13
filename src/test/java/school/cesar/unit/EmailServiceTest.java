package school.cesar.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Collection;

public class EmailServiceTest {
    @Test
    public void sendEmailShouldNotThrowExceptionWhenIsValidEmailReturnsTrue() {
        EmailService emailService = new EmailService() {
            @Override
            public boolean sendEmail(Email email) {
                return false;
            }

            @Override
            public Collection<Email> emailList(EmailAccount account) {
                return null;
            }
        };

        EmailClient emailClient = new EmailClient() {
            @Override
            public boolean isValidEmail(Email email) {
                return true;
            }
        };

        emailClient.setEmailService(emailService);
        Email email = new EmailBuilder().build();

        Assertions.assertDoesNotThrow(() -> emailClient.sendEmail(email));
    }

    @Test
    public void sendEmailShouldThrowExceptionWhenIsValidEmailReturnsFalse() {
        EmailService emailService = new EmailService() {
            @Override
            public boolean sendEmail(Email email) {
                return false;
            }

            @Override
            public Collection<Email> emailList(EmailAccount account) {
                return null;
            }
        };

        EmailClient emailClient = new EmailClient() {
            @Override
            public boolean isValidEmail(Email email) {
                return false;
            }
        };

        emailClient.setEmailService(emailService);
        Email email = new EmailBuilder().build();

        Assertions.assertThrows(RuntimeException.class, () -> emailClient.sendEmail(email));
    }

    @Test
    public void emailListShouldNotThrowExceptionWhenIsPasswordValidReturnsTrue() {
        EmailService emailService = new EmailService() {
            @Override
            public boolean sendEmail(Email email) {
                return false;
            }

            @Override
            public Collection<Email> emailList(EmailAccount account) {
                return null;
            }
        };

        EmailClient emailClient = new EmailClient();

        String user = "";
        String domain = "";
        String password = "";
        Instant lastPasswordUpdate = Instant.now();
        EmailAccount account = new EmailAccount(user, domain, password, lastPasswordUpdate) {
            @Override
            public boolean isPasswordValid(String password, Instant lastPasswordUpdate) {
                return true;
            }

        };

        emailClient.setEmailService(emailService);

        Assertions.assertDoesNotThrow(() -> emailClient.emailList(account));
    }

    @Test
    public void emailListShouldThrowExceptionWhenIsPasswordValidReturnsFalse() {
        EmailService emailService = new EmailService() {
            @Override
            public boolean sendEmail(Email email) {
                return false;
            }

            @Override
            public Collection<Email> emailList(EmailAccount account) {
                return null;
            }
        };

        EmailClient emailClient = new EmailClient();

        String user = "";
        String domain = "";
        String password = "";
        Instant lastPasswordUpdate = Instant.now();
        EmailAccount account = new EmailAccount(user, domain, password, lastPasswordUpdate) {
            @Override
            public boolean isPasswordValid(String password, Instant lastPasswordUpdate) {
                return false;
            }

        };

        emailClient.setEmailService(emailService);

        Assertions.assertThrows(RuntimeException.class, () -> emailClient.emailList(account));
    }
}
