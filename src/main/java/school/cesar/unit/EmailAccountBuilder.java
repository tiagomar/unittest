package school.cesar.unit;

import java.time.Instant;

public class EmailAccountBuilder {
    private String user;
    private String domain;
    private String password;
    private Instant lastPasswordUpdate;

    public EmailAccountBuilder(){}

    public EmailAccountBuilder setUser(String user) {
        if(user.matches("[a-zA-Z0-9._-]+")){
            this.user = user;
            return this;
        } else {
            throw new RuntimeException("User is not valid.");
        }
    }

    public EmailAccountBuilder setDomain(String domain) {
        if(String.valueOf(domain.toCharArray()[0]).equals(".") || String.valueOf(domain.toCharArray()[domain.length() - 1]).equals(".") ){
            throw new RuntimeException("Domain is not valid.");
        }
        if(domain.contains("..")){
            throw new RuntimeException("Domain is not valid.");
        }
        if(domain.matches("[a-zA-Z0-9.]+")){
            this.domain = domain;
            return this;
        } else {
            throw new RuntimeException("Domain is not valid.");
        }
    }

    public EmailAccountBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public EmailAccountBuilder setLastPasswordUpdate(Instant lastPasswordUpdate) {
        this.lastPasswordUpdate = lastPasswordUpdate;
        return this;
    }

    public EmailAccount build(){
        return new EmailAccount(user,domain,password,lastPasswordUpdate);
    }
}
