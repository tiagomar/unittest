package school.cesar.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class FieldsValidationTest {
    private static EmailAccount emailAccount;

    @Test
    public void validateUserShouldReturnTrueForValidUser(){
        Assertions.assertTrue(FieldsValidation.isValidUser("v4lid._-Us3r"));
    }

    @Test
    public void validateUserShouldReturnFalseForInvalidUser(){
        /*Executable setInvalidUser = () -> emailAccount = new EmailAccountBuilder()
                .setUser("Invalid*._-user")
                .build();
        Assertions.assertThrows(RuntimeException.class, setInvalidUser, "User is not valid.");
        */
        Assertions.assertFalse(FieldsValidation.isValidUser("Invalid*._-user"));
    }

    @Test
    public void validateUserShouldReturnTrueForValidDomain(){
        Assertions.assertTrue(FieldsValidation.isValidDomain("cesar.school"));
    }

    @Test
    public void validateUserShouldReturnFalseForInvalidDomain_PeriodAsFirstCharacter(){
        /*Executable setInvalidDomain = () -> emailAccount = new EmailAccountBuilder()
                .setDomain(".cesar.school")
                .build();
        Assertions.assertThrows(RuntimeException.class, setInvalidDomain, "Domain is not valid.");
        */
        Assertions.assertFalse(FieldsValidation.isValidDomain(".cesar.school"));
    }

    @Test
    public void validateUserShouldReturnFalseForInvalidDomain_PeriodAsLastCharacter(){
        /*Executable setInvalidDomain = () -> emailAccount = new EmailAccountBuilder()
                .setDomain("cesar.school.")
                .build();
        Assertions.assertThrows(RuntimeException.class, setInvalidDomain, "Domain is not valid.");
        */
        Assertions.assertFalse(FieldsValidation.isValidDomain("cesar.school."));
    }

    @Test
    public void validateUserShouldReturnFalseForInvalidDomain_DoublePeriod(){
        /*Executable setInvalidDomain = () -> emailAccount = new EmailAccountBuilder()
                .setDomain("cesar..school")
                .build();
        Assertions.assertThrows(RuntimeException.class, setInvalidDomain, "Domain is not valid.");
        */
        Assertions.assertFalse(FieldsValidation.isValidDomain("cesar..school"));
    }
}
