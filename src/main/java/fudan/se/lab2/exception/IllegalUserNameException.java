package fudan.se.lab2.exception;

public class IllegalUserNameException extends RuntimeException {
    private static final long serialVersionUID = -6074753940710869977L;

    public IllegalUserNameException() {
        super("User Name is illegal!");
    }
}
