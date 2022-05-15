package mk.ukim.finki.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CarNotFoundException extends RuntimeException{
    public CarNotFoundException(Long id){
        super(String.format("Car with id: %id was not found", id));
    }
}
