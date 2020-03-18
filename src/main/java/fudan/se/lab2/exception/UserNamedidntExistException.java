package fudan.se.lab2.exception;

import org.springframework.security.core.AuthenticationException;

public class UserNamedidntExistException extends AuthenticationException {
    private static final long serialVersionUID = -6074753940710869973L;

    public UserNamedidntExistException() {
        super("User name didn't exist!");
    }
}
