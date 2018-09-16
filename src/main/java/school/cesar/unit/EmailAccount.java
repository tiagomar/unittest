package school.cesar.unit;

import java.time.Instant;
import java.time.temporal.ChronoUnit;


public class EmailAccount {
    private String user;
    private String domain;
    private String password;
    private Instant lastPasswordUpdate;

    public EmailAccount(String user, String domain, String password, Instant lastPasswordUpdate) {
        this.user = user;
        this.domain = domain;
        this.password = password;
        this.lastPasswordUpdate = lastPasswordUpdate;

    }

    public String getUser() {
        return user;
    }

    public String getDomain() {
        return domain;
    }

    public String getPassword() {
        return password;
    }

    public Instant getLastPasswordUpdate() {
        return lastPasswordUpdate;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastPasswordUpdate(Instant lastPasswordUpdate) {
        this.lastPasswordUpdate = lastPasswordUpdate;
    }

    public boolean isPasswordValid(String password, Instant lastPasswordUpdate) {
        return isPasswordLongerThanSixCharacters(password) && verifyPasswordExpiration(lastPasswordUpdate);
    }

    public boolean isPasswordLongerThanSixCharacters(String password){
        return password.length() >= 6;
    }

    public boolean verifyPasswordExpiration(Instant lastPasswordUpdate) {
        return daysSinceLastPasswordUpdate(lastPasswordUpdate) <= 90;
    }

    public long daysSinceLastPasswordUpdate(Instant lastPasswordUpdate) {
        Instant currentInstant = Instant.now();
        return ChronoUnit.DAYS.between(lastPasswordUpdate, currentInstant);
    }
}
