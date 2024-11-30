package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidFieldException extends ResponseStatusException {

    public InvalidFieldException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
