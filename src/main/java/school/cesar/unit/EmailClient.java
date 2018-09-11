package school.cesar.unit;

import java.util.Collection;

public class EmailClient {
    private Collection<EmailAccount> accounts;
    private EmailService emailService;
    private Email email;


    public EmailClient(Collection<EmailAccount> accounts, EmailService emailService){
        this.accounts = accounts;
        this.emailService = emailService;
        //this.email = email;
    }

    public class EmailService implements school.cesar.unit.EmailService{
        @Override
        public boolean sendEmail(Email email) {
            return false;
        }

        @Override
        public Collection<Email> emailList(EmailAccount account) {
            return null;
        }
    }

    public void setEmailService(EmailService emailService){
        this.emailService = emailService;
    }



    /*
    Um endereço é considerado válido se possuir usuário válido,
    seguido pelo caractere arroba (@) e posteriormente um domínio válido.
    */
    public static boolean isValidAddress(String address){
        String[] parts = address.split("@");
        if(address.split("@").length == 2){
            String user = address.split("@")[0];
            String domain = address.split("@")[1];

            if(FieldsValidation.isValidUser(user) && FieldsValidation.isValidDomain(domain)){
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

    public static boolean isValidAddress(Collection<String> addresses){
        boolean flag = true;
        for(String address : addresses){
            if(!isValidAddress(address)){
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
    public static boolean isValidEmail(Email email){
        if(email.getCreationDate() != null){
            if(isValidAddress(email.getFrom())){
                if(email.getTo().size() != 0 && isValidAddress(email.getTo())){
                    if(email.getCc().size() != 0 && !isValidAddress(email.getCc())){
                        return false;
                    }
                    if(email.getBcc().size() != 0 && !isValidAddress(email.getBcc())){
                        return false;
                    }
                } else return false;
            }else return false;
        }else return false;
        return true;
    }

    /*
    - Antes de obter emails verificar se password é válido
    - Se password inválido levantar uma exeção do tipo RuntimeException
    - Chamar emailService.emailList(account)
    */
    public Collection<Email> emailList(EmailAccount account){
        if(account.verifyPasswordExpiration(account.getLastPasswordUpdate())){
            return emailService.emailList(account);
        } else {
            throw new RuntimeException("Password is expired!");
        }

    }

    /*
    - verifica se o email é válido (utilizando o método isValidEmail)
    - chamar emailService.sendEmail(Email email)
    - Se retorno false levantar uma exeção do tipo `RuntimeException
     */
    public void sendEmail(Email email){
        if(isValidEmail(email)){
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
    public boolean createAccount(EmailAccount account){
        return false;
    }



}
