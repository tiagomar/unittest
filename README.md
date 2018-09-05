# cesar-school-unit-testing-project
## Detalhamento da implentação
1. Criar classe `Email` que contenha
    1. Atributos:
        * Instant creationDate
        * Sting from
        * Collection\<String> to
        * Collection\<String> cc
        * Collection\<String> bcc
        * String subject
        * String message
```java
package school.cesar.unit;

public class Email {
    ...
}
```

2. Criar Classe `EmailAccount` que contenha:
    1. Atributos:
        * String user
            * Apenas Letras, números e os seguintes caracteres: ponto (.), linha (_) e traço (-) 
        * String domain
            * Letras, números e o caractere ponto (.), não podendo ele estar no início, final ou seguido de outro ponto
        * String password
        * Instant lastPasswordUpdate
    2. Métodos
        * boolean verifyPasswordExpiration
            * O password é considerado expirado se o lastPasswordUpdate for maior que 90 dias da data atual do sistema
```java
package school.cesar.unit;

public class EmailAccount {
    ...
}
```

3. Criar Interface `EmailService` com as seguintes assinaturas:
    *	boolean sendEmail(Email email)
    *	Collection\<Email> emailList(EmailAccount account)  
```java
package school.cesar.unit;

import java.util.Collection;

public interface EmailService {
    
    boolean sendEmail(Email email);
    
    Collection<Email> emailList(EmailAccount account);
    
}
```

4. Criar classe `EmailClient` que possua
    1. Atributos
        * Collection\<EmailAccount> accounts
        * EmailService emailService
            * Armazena uma instancia de um objeto que implementa a interface `EmailService`
    2. Métodos
        * void setEmailService()
        * boolean isValidAddress()
            * Um endereço é considerado válido se possuir usuário válido, seguido pelo caractere arroba (@) e posteriormente um domínio válido.
        * boolean isValidEmail()
            * É considerado válido o email que possuir um creationDate, um destinatário (to) válido, ao menos um emissor (from) válido e os demais e-mails também sejam válidos
        * Collection\<Email> emailList(EmailAccount account)
            * Antes de obter emails verificar se password é válido
            * Se password inválido levantar uma exeção do tipo `RuntimeException` 
            * Chamar `emailService.emailList(account)`
        * void sendEmail()
            * verifica se o email é válido (utilizando o método isValidEmail)
            * chamar emailService.sendEmail(Email email)
            * Se retorno `false` levantar uma exeção do tipo `RuntimeException
        * boolean createAccount(EmailAccount account)
            * verifica se o usuário e o dominio são válidos
            * verifica se o password é maior que 6 caracteres
            * adiciona data atual ao `lastPasswordUpdate`
            * adcionar objeto a coleção `accounts`
            
```java
package school.cesar.unit;

public class EmailClient {
    ...
}
```
# Nota
* 70% da nota será composta da implementação do código e os testes checando se as condições descritas foram corretamente implentadas 
* 30% da nota será composta pela cobertura proporcional dos seus testes
    * Exemplo: 100% = 3 pontos, 50% = 1.5 pontos
* 1 Ponto extra para quem utilizar o padrão Builder para auxiliar nos casos de teste
