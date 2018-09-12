package school.cesar.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import static school.cesar.unit.EmailClient.isValidDomain;
//import static school.cesar.unit.EmailClient.isValidEmail;
import static school.cesar.unit.EmailClient.isValidEmail;
import static school.cesar.unit.EmailClient.isValidUser;



public class EmailClientTest {
    private static Collection<String> to;
    private static Collection<String> cc;
    private static Collection<String> bcc;
    private static Collection<Email> emails;
    private static EmailClient emailClient;
    private static Collection<EmailAccount> accounts;

    /*
    private static class EmailServiceStub implements EmailService{

        @Override
        public boolean sendEmail(Email email) {
            return true;
        }

        @Override
        public Collection<Email> emailList(EmailAccount account) {
            to = new ArrayList<String>();
            ((ArrayList<String>) to).add("asdf@cesar.school");

            Email email_1 = new EmailBuilder()
                    .setCreationDate(Instant.now())
                    .setFrom("tfm@cesar.school")
                    .setTo(to)
                    .setSubject("This is a Subject")
                    .setMessage("This is the body of the message.")
                    .build();

            Email email_2 = new EmailBuilder()
                    .setCreationDate(Instant.now())
                    .setFrom("tfm@cesar.school")
                    .setTo(to)
                    .setSubject("This is another Subject")
                    .setMessage("This is not the body of the message.")
                    .build();

            emails = new ArrayList<Email>();
            emails.add(email_1);
            emails.add(email_2);

            if(account.verifyPasswordExpiration(account.getLastPasswordUpdate())){
                return emails;
            } else throw new RuntimeException("Password is expired!");

        }
    }

    private static EmailServiceStub emailServiceStub;
    */


    /*
    @BeforeAll
    public static void setUp() {
        emailServiceStub = new EmailServiceStub();
    }
    */

    @Test
    public void validateUserShouldReturnTrueForValidUser(){
        Assertions.assertTrue(isValidUser("v4lid._-Us3r"));
    }

    @Test
    public void validateUserShouldReturnFalseForInvalidUser(){
        Assertions.assertFalse(isValidUser("Invalid*._-user"));
    }

    @Test
    public void validateUserShouldReturnTrueForValidDomain(){
        Assertions.assertTrue(isValidDomain("cesar.school"));
    }

    @Test
    public void validateUserShouldReturnFalseForInvalidDomain_PeriodAsFirstCharacter(){
        Assertions.assertFalse(isValidDomain(".cesar.school"));
    }

    @Test
    public void validateUserShouldReturnFalseForInvalidDomain_PeriodAsLastCharacter(){
        Assertions.assertFalse(isValidDomain("cesar.school."));
    }

    @Test
    public void validateUserShouldReturnFalseForInvalidDomain_DoublePeriod(){
        Assertions.assertFalse(isValidDomain("cesar..school"));
    }

    @Test
    public void testIsValidAddress_With_ValidAddress(){
        Assertions.assertTrue(EmailClient.isValidAddress("tfm@cesar.school"));
    }

    @Test
    public void testIsValidAddress_With_InvalidUser_ValidDomain(){
        Assertions.assertFalse(EmailClient.isValidAddress("t*m@cesar.school"));
    }

    @Test
    public void testIsValidAddress_With_ValidUser_InvalidDomain(){
        Assertions.assertFalse(EmailClient.isValidAddress("tfm@cesar..school"));
    }

    @Test
    public void testIsValidAddress_With_InvalidUser_InvalidDomain(){
        Assertions.assertFalse(EmailClient.isValidAddress("tfm@cesar..school"));
    }

    @Test
    public void testIsValidAddress_With_InvalidAddress_NoAt(){
        Assertions.assertFalse(EmailClient.isValidAddress("tfmcesar.school"));
    }

    @Test
    public void testIsValidAddress_With_InvalidAddress_TwoAts(){
        Assertions.assertFalse(EmailClient.isValidAddress("tfm@cesa@r.school"));
    }

    @Test
    public void testIsValidEmail_With_ValidEmail(){
        to = new ArrayList<String>();
        ((ArrayList<String>) to).add("aaa@cesar.school");
        ((ArrayList<String>) to).add("aaaa@cesar.school");

        cc = new ArrayList<String>();
        ((ArrayList<String>) cc).add("bbb@cesar.school");
        ((ArrayList<String>) cc).add("bbbb@cesar.school");

        bcc = new ArrayList<String>();
        ((ArrayList<String>) bcc).add("ccc@cesar.school");

        Email email = new EmailBuilder()
                .setCreationDate(Instant.now())
                .setFrom("tfm@cesar.school")
                .setTo(to)
                .setCc(cc)
                .setBcc(bcc)
                .build();

        Assertions.assertTrue(isValidEmail(email));
    }

    @Test
    public void testIsValidEmail_With_InvalidEmail_NoCc_NoBcc(){
        to = new ArrayList<String>();
        ((ArrayList<String>) to).add("aaa@cesar.school");
        ((ArrayList<String>) to).add("aaaa@cesar.school");

        cc = new ArrayList<String>();

        bcc = new ArrayList<String>();

        Email email = new EmailBuilder()
                .setCreationDate(Instant.now())
                .setFrom("tfm@cesar.school")
                .setTo(to)
                .setCc(cc)
                .setBcc(bcc)
                .build();

        Assertions.assertTrue(isValidEmail(email));
    }

    @Test
    public void testIsValidEmail_With_InvalidEmail_NoCreationDate(){
        to = new ArrayList<String>();
        ((ArrayList<String>) to).add("aaa@cesar.school");
        ((ArrayList<String>) to).add("aaaa@cesar.school");

        cc = new ArrayList<String>();
        ((ArrayList<String>) cc).add("bbb@cesar.school");
        ((ArrayList<String>) cc).add("bbbb@cesar.school");

        bcc = new ArrayList<String>();
        ((ArrayList<String>) bcc).add("ccc@cesar.school");

        Email email = new EmailBuilder()
                .setFrom("tfm@cesar.school")
                .setTo(to)
                .setCc(cc)
                .setBcc(bcc)
                .build();

        Assertions.assertFalse(isValidEmail(email));
    }

    @Test
    public void testIsValidEmail_With_InvalidEmail_InvalidFrom(){
        to = new ArrayList<String>();
        ((ArrayList<String>) to).add("aaa@cesar.school");
        ((ArrayList<String>) to).add("aaaa@cesar.school");

        cc = new ArrayList<String>();
        ((ArrayList<String>) cc).add("bbb@cesar.school");
        ((ArrayList<String>) cc).add("bbbb@cesar.school");

        bcc = new ArrayList<String>();
        ((ArrayList<String>) bcc).add("ccc@cesar.school");

        Email email = new EmailBuilder()
                .setCreationDate(Instant.now())
                .setFrom("t*m@cesar.school")
                .setTo(to)
                .setCc(cc)
                .setBcc(bcc)
                .build();

        Assertions.assertFalse(isValidEmail(email));
    }

    @Test
    public void testIsValidEmail_With_InvalidEmail_InvalidTo(){
        to = new ArrayList<String>();
        ((ArrayList<String>) to).add("aaa@cesar.school");
        ((ArrayList<String>) to).add("aaa*a@cesar.school");

        cc = new ArrayList<String>();
        ((ArrayList<String>) cc).add("bbb@cesar.school");
        ((ArrayList<String>) cc).add("bbbb@cesar.school");

        bcc = new ArrayList<String>();
        ((ArrayList<String>) bcc).add("ccc@cesar.school");

        Email email = new EmailBuilder()
                .setCreationDate(Instant.now())
                .setFrom("tfm@cesar.school")
                .setTo(to)
                .setCc(cc)
                .setBcc(bcc)
                .build();

        Assertions.assertFalse(isValidEmail(email));
    }

    @Test
    public void testIsValidEmail_With_InvalidEmail_InvalidCc(){
        to = new ArrayList<String>();
        ((ArrayList<String>) to).add("aaa@cesar.school");
        ((ArrayList<String>) to).add("aaaa@cesar.school");

        cc = new ArrayList<String>();
        ((ArrayList<String>) cc).add("bb*b@cesar.school");
        ((ArrayList<String>) cc).add("bbbb@cesar.school");

        bcc = new ArrayList<String>();
        ((ArrayList<String>) bcc).add("ccc@cesar.school");

        Email email = new EmailBuilder()
                .setCreationDate(Instant.now())
                .setFrom("tfm@cesar.school")
                .setTo(to)
                .setCc(cc)
                .setBcc(bcc)
                .build();

        Assertions.assertFalse(isValidEmail(email));
    }

    @Test
    public void testIsValidEmail_With_InvalidEmail_InvalidBcc(){
        to = new ArrayList<String>();
        ((ArrayList<String>) to).add("aaa@cesar.school");
        ((ArrayList<String>) to).add("aaaa@cesar.school");

        cc = new ArrayList<String>();
        ((ArrayList<String>) cc).add("bbb@cesar.school");
        ((ArrayList<String>) cc).add("bbbb@cesar.school");

        bcc = new ArrayList<String>();
        ((ArrayList<String>) bcc).add("c*c@cesar.school");

        Email email = new EmailBuilder()
                .setCreationDate(Instant.now())
                .setFrom("tfm@cesar.school")
                .setTo(to)
                .setCc(cc)
                .setBcc(bcc)
                .build();

        Assertions.assertFalse(isValidEmail(email));
    }


    //todo
    @Test
    public void testIsValidAddressCollection_With_ValidCollection(){
        to = new ArrayList<String>();
        ((ArrayList<String>) to).add("aaa@cesar.school");
        ((ArrayList<String>) to).add("aaaa@cesar.school");
        ((ArrayList<String>) to).add("aaaaa@cesar.school");
    }
    //todo
    @Test
    public void testIsValidAddressCollection_With_InvalidCollection(){
        to = new ArrayList<String>();
        ((ArrayList<String>) to).add("aaa*@cesar.school");
        ((ArrayList<String>) to).add("aaaa*@cesar.school");
        ((ArrayList<String>) to).add("aaaaa@cesar.school");
    }

    /*
    @Test
    public void testEmailList_With_ValidPassword(){
        EmailAccount account = new EmailAccountBuilder()
                .setUser("tfm")
                .setDomain("cesar.school")
                .setPassword("password")
                .setLastPasswordUpdate(Instant.now())
                .build();
        Assertions.assertEquals(2, emailServiceStub.emailList(account).size());
    }

    @Test
    public void testEmailList_With_InvalidPassword(){
        EmailAccount account = new EmailAccountBuilder()
                .setUser("tfm")
                .setDomain("cesar.school")
                .setPassword("password")
                .setLastPasswordUpdate(Instant.now().minus(Duration.ofDays(100)))
                .build();
        Assertions.assertThrows(RuntimeException.class, () -> {emailServiceStub.emailList(account);;});
    }

    @Test
    public void testSendEmail_ValidEmail(){
        Email validEmail = new EmailBuilder()
                .setCreationDate(Instant.now())
                .setFrom("tfm@cesar.school")
                .setTo(to)
                .setSubject("This is a Subject")
                .setMessage("This is the body of the message.")
                .build();

        accounts.add(new EmailAccountBuilder()
                        .setUser("tfm")
                        .setDomain("cesar.school")
                        .setPassword("password")
                        .setLastPasswordUpdate(Instant.now())
                        .build());

        //emailClient = new EmailClient(accounts, emailServiceStub);

    }
    */
}
