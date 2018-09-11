package school.cesar.unit;

public class FieldsValidation {

    public static boolean isValidUser(String user){
        if(user.matches("[a-zA-Z0-9._-]+")){
            return true;
        } else {
            //throw new RuntimeException("User is not valid.");
            return false;
        }
    }

    public static boolean isValidDomain(String domain){
        if(domain.contains("..")){
            //throw new RuntimeException("Domain is not valid.");
            return false;
        } else {
            if(domain.matches("^(?!\\.)[a-zA-Z0-9.]*[^.]$")){
                return true;
            } else {
                //throw new RuntimeException("Domain is not valid.");
                return false;
            }
        }
    }
}
