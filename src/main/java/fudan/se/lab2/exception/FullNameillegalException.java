package fudan.se.lab2.exception;

public class FullNameillegalException extends RuntimeException{
    private static final long serialVersionUID = -6074753940710869977L;

    public FullNameillegalException(String FullName) {
        super("Full name "+FullName+" is illegal!");
    }
}
