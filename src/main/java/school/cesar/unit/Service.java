package school.cesar.unit;

import java.util.Collection;

public class Service implements EmailService {
    @Override
    public boolean sendEmail(Email email) {
        return false;
    }

    @Override
    public Collection<Email> emailList(EmailAccount account) {
        return null;
    }
}
