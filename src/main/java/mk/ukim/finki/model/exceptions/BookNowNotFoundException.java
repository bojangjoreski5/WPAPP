package mk.ukim.finki.model.exceptions;

public class BookNowNotFoundException extends RuntimeException {
    public BookNowNotFoundException(Long id){
        super(String.format("Car with id: %id was not found", id));
    }
}
