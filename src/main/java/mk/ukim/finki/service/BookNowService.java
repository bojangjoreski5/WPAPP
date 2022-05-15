package mk.ukim.finki.service;

import mk.ukim.finki.model.BookNow;
import mk.ukim.finki.model.Cars;

import java.util.List;

public interface BookNowService {

    List<Cars> listAllBookedCars(Long bookNowId);

    BookNow getActiveBookNow(String username);

    BookNow bookNowCar(String username, Long carId);

    void deleteById(Long id);
}
