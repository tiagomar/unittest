package school.cesar.unit;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;


public class EmailClient {

    private EmailService emailService;



    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }


    public boolean isValidUser(String user) {
        return user.matches("[a-zA-Z0-9._-]+");
    }

    public boolean isValidDomain(String domain) {
        if (domain.contains("..")) {
            return false;
        } else {
            return domain.matches("^(?!\\.)[a-zA-Z0-9.]*[^.]$");
        }
    }

    /*
    Um endereço é considerado válido se possuir usuário válido,
    seguido pelo caractere arroba (@) e posteriormente um domínio válido.
    */
    public boolean isValidAddress(String emailAddress) {
        if (emailAddress.split("@").length == 2) {
            String user = emailAddress.split("@")[0];
            String domain = emailAddress.split("@")[1];

            return isValidUser(user) && isValidDomain(domain);

        } else {
            return false;
        }
    }

    public boolean isValidAddress(Collection<String> emailAddresses) {
        boolean flag = true;
        for (String emailAddress : emailAddresses) {
            if (!isValidAddress(emailAddress)) {
                flag = false;
            }
        }
        return flag;
    }

    /*
    É considerado válido o email que possuir um creationDate,
    um destinatário (to) válido, ao menos um emissor (from) válido
    e os demais e-mails também sejam válidos
    */
    public boolean isValidEmail(Email email) {
        if (email.getCreationDate() != null) {
            if (isValidAddress(email.getFrom())) {
                if (!email.getTo().isEmpty() && isValidAddress(email.getTo())) {
                    if (!email.getCc().isEmpty() && !isValidAddress(email.getCc())) {
                        return false;
                    }
                    if (!email.getBcc().isEmpty() && !isValidAddress(email.getBcc())) {
                        return false;
                    }
                } else return false;
            } else return false;
        } else return false;
        return true;
    }

    /*
    - Antes de obter emails verificar se password é válido
      (password é valido se maior que 6 caracteres e lastPasswordUpdate menor ou igual a 90 dias)
    - Se password inválido levantar uma exeção do tipo RuntimeException
    - Chamar emailService.emailList(account)
    */
    public Collection<Email> emailList(EmailAccount emailAccount) {
        if (emailAccount.isPasswordValid(emailAccount.getPassword(), emailAccount.getLastPasswordUpdate())) {
            return emailService.emailList(emailAccount);
        } else {
            throw new RuntimeException("Password is expired!");
        }
    }

    /*
    - verifica se o email é válido (utilizando o método isValidEmail)
    - chamar emailService.sendEmail(Email email)
    - Se retorno false levantar uma exeção do tipo `RuntimeException
     */
    public void sendEmail(Email email) {
        if (isValidEmail(email)) {
            emailService.sendEmail(email);
        } else {
            throw new RuntimeException("Email is not valid.");
        }
    }

    /*
    - verifica se o usuário e o dominio são válidos
    - verifica se o password é maior que 6 caracteres
    - adiciona data atual ao lastPasswordUpdate
    - adcionar objeto a coleção accounts
     */
    public boolean createAccount(EmailAccount account) {
        Collection<EmailAccount> accounts = new ArrayList<>();
        if(isValidUser(account.getUser())){
            if(isValidDomain(account.getDomain())){
                if(account.isPasswordLongerThanSixCharacters(account.getPassword())){
                    account.setLastPasswordUpdate(Instant.now());
                    return accounts.add(account);
                } else return false;
            } else return false;
        } else return false;
    }


}
