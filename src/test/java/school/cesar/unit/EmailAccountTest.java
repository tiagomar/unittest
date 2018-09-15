package school.cesar.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class EmailAccountTest {


    private static EmailAccount emailAccountStub;
    private static long days;
    private static boolean expirationCheck;

    private static EmailAccount emailAccount;
    private static String user;
    private static String domain;
    private static String password;
    private static Instant lastPasswordUpdate;

    //private static EmailAccountBuilder emailAccount;


    @BeforeAll
    public static void setUp() {
        emailAccountStub = new EmailAccount(user, domain, password, lastPasswordUpdate) {
            @Override
            public long daysSinceLastPasswordUpdate(Instant lastPasswordUpdate) {
                return days;
            }

            @Override
            public boolean verifyPasswordExpiration(Instant lastPasswordUpdate) {
                return expirationCheck;
            }
        };
    }

    @Test
    public void isPasswordLongerThanSixCharacters_With_SixCharacters() {
        emailAccount = new EmailAccountBuilder()
                .setPassword("123456")
                .build();
        Assertions.assertTrue(emailAccount.isPasswordLongerThanSixCharacters(emailAccount.getPassword()));
    }

    @Test
    public void isPasswordLongerThanSixCharacters_With_LessThanSixCharacters() {
        emailAccount = new EmailAccountBuilder()
                .setPassword("12345")
                .build();
        Assertions.assertFalse(emailAccount.isPasswordLongerThanSixCharacters(emailAccount.getPassword()));
    }

    @Test
    public void isPasswordValid_With_ValidPassword_SixCharacters_NotExpired() {
        expirationCheck = true;
        Assertions.assertTrue(emailAccountStub.isPasswordValid("123456", Instant.now()));
    }

    @Test
    public void isPasswordValid_With_InvalidPassword_FiveCharacters_NotExpired() {
        expirationCheck = true;
        Assertions.assertFalse(emailAccountStub.isPasswordValid("12345", Instant.now()));
    }

    @Test
    public void isPasswordValid_With_InvalidPassword_SixCharacters_Expired() {
        expirationCheck = false;
        Assertions.assertFalse(emailAccountStub.isPasswordValid("123456", Instant.now()));
    }

    @Test
    public void isPasswordValid_With_InvalidPassword_FiveCharacters_Expired() {
        expirationCheck = false;
        Assertions.assertFalse(emailAccountStub.isPasswordValid("12345", Instant.now()));
    }
/*
    @Test
    public void checkVerifyPasswordExpiration_With_NotExpiredPassword_Stub() {
        days = 90;
        Assertions.assertTrue(emailAccountStub.verifyPasswordExpiration(lastPasswordUpdate));
    }

    @Test
    public void checkVerifyPasswordExpiration_With_ExpiredPassword_Stub() {
        days = 91;
        Assertions.assertFalse(emailAccountStub.verifyPasswordExpiration(lastPasswordUpdate));
    }
*/
    @Test
    public void verifyPasswordExpiration_NotExpired(){
        emailAccount = new EmailAccountBuilder()
                .setLastPasswordUpdate(Instant.now().minus(1,ChronoUnit.DAYS))
                .build();
        Assertions.assertTrue(emailAccount.verifyPasswordExpiration(emailAccount.getLastPasswordUpdate()));
    }

    @Test
    public void verifyPasswordExpiration_Expired(){
        emailAccount = new EmailAccountBuilder()
                .setLastPasswordUpdate(Instant.now().minus(100,ChronoUnit.DAYS))
                .build();
        Assertions.assertFalse(emailAccount.verifyPasswordExpiration(emailAccount.getLastPasswordUpdate()));
    }

    @Test
    public void checkDaysSinceLastPasswordUpdate_Yesterday() {
        emailAccount = new EmailAccountBuilder()
                .setLastPasswordUpdate(daysFromToday(-1).toInstant())
                .build();
        Assertions.assertEquals(1, emailAccount.daysSinceLastPasswordUpdate(emailAccount.getLastPasswordUpdate()));
    }

    @Test
    public void checkDaysSinceLastPasswordUpdate_91DaysBefore() {
        //lastPasswordUpdate = daysFromToday(-91).toInstant();
        emailAccount = new EmailAccountBuilder()
                .setLastPasswordUpdate(daysFromToday(-91).toInstant())
                .build();
        Assertions.assertEquals(91, emailAccount.daysSinceLastPasswordUpdate(emailAccount.getLastPasswordUpdate()));
    }

    private Date daysFromToday(Integer daysToAdd) {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, daysToAdd);
        return cal.getTime();
    }

}
