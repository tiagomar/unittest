package school.cesar.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

public class EmailClientTest {
    private static Collection<String> to;
    private static Collection<String> cc;
    private static Collection<String> bcc;
    private static EmailClient emailClient;
    private static EmailAccount emailAccount;
    private static String user;
    private static String domain;
    private static String password;
    private static Instant lasPasswordUpdate;
    private static boolean moreThanSix;
    private static boolean validUser;
    private static Collection<EmailAccount> accounts;

    @BeforeAll
    public static void setUp() {
        emailClient = new EmailClient();
    }

    @Test
    public void validateUserShouldReturnTrueForValidUser() {
        Assertions.assertTrue(emailClient.isValidUser("v4lid._-Us3r"));
    }

    @Test
    public void validateUserShouldReturnFalseForInvalidUser() {
        Assertions.assertFalse(emailClient.isValidUser("Invalid*._-user"));
    }

    @Test
    public void validateUserShouldReturnTrueForValidDomain() {
        Assertions.assertTrue(emailClient.isValidDomain("cesar.school"));
    }

    @Test
    public void validateUserShouldReturnFalseForInvalidDomain_PeriodAsFirstCharacter() {
        Assertions.assertFalse(emailClient.isValidDomain(".cesar.school"));
    }

    @Test
    public void validateUserShouldReturnFalseForInvalidDomain_PeriodAsLastCharacter() {
        Assertions.assertFalse(emailClient.isValidDomain("cesar.school."));
    }

    @Test
    public void validateUserShouldReturnFalseForInvalidDomain_DoublePeriod() {
        Assertions.assertFalse(emailClient.isValidDomain("cesar..school"));
    }

    @Test
    public void testIsValidAddress_With_ValidAddress() {
        Assertions.assertTrue(emailClient.isValidAddress("tfm@cesar.school"));
    }

    @Test
    public void testIsValidAddress_With_InvalidUser_ValidDomain() {
        Assertions.assertFalse(emailClient.isValidAddress("t*m@cesar.school"));
    }

    @Test
    public void testIsValidAddress_With_ValidUser_InvalidDomain() {
        Assertions.assertFalse(emailClient.isValidAddress("tfm@cesar..school"));
    }

    @Test
    public void testIsValidAddress_With_InvalidUser_InvalidDomain() {
        Assertions.assertFalse(emailClient.isValidAddress("tfm@cesar..school"));
    }

    @Test
    public void testIsValidAddress_With_InvalidAddress_NoAt() {
        Assertions.assertFalse(emailClient.isValidAddress("tfmcesar.school"));
    }

    @Test
    public void testIsValidAddress_With_InvalidAddress_TwoAts() {
        Assertions.assertFalse(emailClient.isValidAddress("tfm@cesa@r.school"));
    }

    @Test
    public void testIsValidAddressCollection_With_ValidCollection() {
        to = new ArrayList<String>();
        ((ArrayList<String>) to).add("aaa@cesar.school");
        ((ArrayList<String>) to).add("aaaa@cesar.school");
        ((ArrayList<String>) to).add("aaaaa@cesar.school");

        Assertions.assertTrue(emailClient.isValidAddress(to));
    }

    @Test
    public void testIsValidAddressCollection_With_InvalidCollection() {
        to = new ArrayList<String>();
        ((ArrayList<String>) to).add("aaa@cesar.school");
        ((ArrayList<String>) to).add("aaaa*@cesar.school");
        ((ArrayList<String>) to).add("aaaaa@cesar.school");

        Assertions.assertFalse(emailClient.isValidAddress(to));
    }

    @Test
    public void testIsValidEmail_With_ValidEmail() {
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

        Assertions.assertTrue(emailClient.isValidEmail(email));
    }

    @Test
    public void testIsValidEmail_With_InvalidEmail_NoCc_NoBcc() {
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

        Assertions.assertTrue(emailClient.isValidEmail(email));
    }

    @Test
    public void testIsValidEmail_With_InvalidEmail_NoCreationDate() {
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

        Assertions.assertFalse(emailClient.isValidEmail(email));
    }

    @Test
    public void testIsValidEmail_With_InvalidEmail_InvalidFrom() {
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

        Assertions.assertFalse(emailClient.isValidEmail(email));
    }

    @Test
    public void testIsValidEmail_With_InvalidEmail_InvalidTo() {
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

        Assertions.assertFalse(emailClient.isValidEmail(email));
    }
    @Test
    public void testIsValidEmail_With_InvalidEmail_NoTo() {
        to = new ArrayList<String>();
        //((ArrayList<String>) to).add("aaa@cesar.school");
        //((ArrayList<String>) to).add("aaaa@cesar.school");

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

        Assertions.assertFalse(emailClient.isValidEmail(email));
    }


    @Test
    public void testIsValidEmail_With_InvalidEmail_InvalidCc() {
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

        Assertions.assertFalse(emailClient.isValidEmail(email));
    }

    @Test
    public void testIsValidEmail_With_InvalidEmail_InvalidBcc() {
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

        Assertions.assertFalse(emailClient.isValidEmail(email));
    }

    @Test
    public void createAccount_ValidAccount() {
        emailAccount = new EmailAccountBuilder()
                .setUser("user")
                .setDomain("domain")
                .setPassword("password")
                .setLastPasswordUpdate(Instant.now())
                .build();

        Assertions.assertTrue(emailClient.createAccount(emailAccount));
    }

    @Test
    public void createAccount_InvalidAccount_InvalidUser() {
        emailAccount = new EmailAccountBuilder()
                .setUser("us*er")
                .setDomain("domain")
                .setPassword("password")
                .setLastPasswordUpdate(Instant.now())
                .build();

        Assertions.assertFalse(emailClient.createAccount(emailAccount));
    }

    @Test
    public void createAccount_InvalidAccount_InvalidDomain() {
        emailAccount = new EmailAccountBuilder()
                .setUser("user")
                .setDomain("do..main")
                .setPassword("password")
                .setLastPasswordUpdate(Instant.now())
                .build();

        Assertions.assertFalse(emailClient.createAccount(emailAccount));
    }

    @Test
    public void createAccount_InvalidAccount_InvalidPassword() {
        emailAccount = new EmailAccountBuilder()
                .setUser("user")
                .setDomain("domain")
                .setPassword("pass")
                .setLastPasswordUpdate(Instant.now())
                .build();

        Assertions.assertFalse(emailClient.createAccount(emailAccount));
    }
}
