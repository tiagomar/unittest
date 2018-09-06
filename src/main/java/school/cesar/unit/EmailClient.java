package school.cesar.unit;

import java.util.Collection;

public class EmailClient {
    private Collection<EmailAccount> accounts;
    private EmailService emailService;
    private Email email;

    public EmailClient(Collection<EmailAccount> ac, EmailService emailService, Email e){
        this.accounts = ac;
        this.emailService = emailService;
        this.email = e;
    }

    public void setEmailService(EmailService emailService){
        this.emailService = emailService;
    }

    /*
    Um endereço é considerado válido se possuir usuário válido,
    seguido pelo caractere arroba (@) e posteriormente um domínio válido.
    */
    public boolean isValidAddress(){
        return false;
    }

    /*
    É considerado válido o email que possuir um creationDate,
    um destinatário (to) válido, ao menos um emissor (from) válido
    e os demais e-mails também sejam válidos
    */
    public boolean isValidEmail(){
        return false;
    }

    /*
    - Antes de obter emails verificar se password é válido
    - Se password inválido levantar uma exeção do tipo RuntimeException
    - Chamar emailService.emailList(account)
    */
    //public Collection<Email> emailList(EmailAccount account){

    //}

    /*
    - verifica se o email é válido (utilizando o método isValidEmail)
    - chamar emailService.sendEmail(Email email)
    - Se retorno false levantar uma exeção do tipo `RuntimeException
     */
    public void sendEmail(){
        if(isValidEmail()){
            emailService.sendEmail(this.email);
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
