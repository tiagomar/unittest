package school.cesar.unit;

import org.junit.jupiter.api.Test;

import java.util.Collection;

public class EmailServiceTest {
    @Test
    public void interfaceTest(){
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

        EmailClient emailClient = new EmailClient(){
            @Override
            public boolean isValidEmail(Email email){
                return true;
            }
        };

        emailClient.setEmailService(emailService);
        Email email = new EmailBuilder().build();
        emailClient.sendEmail(email);


    }
}
