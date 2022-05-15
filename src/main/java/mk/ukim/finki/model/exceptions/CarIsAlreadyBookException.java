package mk.ukim.finki.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class CarIsAlreadyBookException extends RuntimeException{
    public CarIsAlreadyBookException(Long id, String username) {
        super(String.format("Car with id: %d is already is booked for user with username %s", id, username));
    }

}
