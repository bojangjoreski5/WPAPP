package mk.ukim.finki.service.impl;

import mk.ukim.finki.model.BookNow;
import mk.ukim.finki.model.Cars;
import mk.ukim.finki.model.User;
import mk.ukim.finki.model.enums.BookNowStatus;
import mk.ukim.finki.model.exceptions.BookNowNotFoundException;
import mk.ukim.finki.model.exceptions.CarIsAlreadyBookException;
import mk.ukim.finki.model.exceptions.CarNotFoundException;
import mk.ukim.finki.model.exceptions.UserNotFoundException;
import mk.ukim.finki.repository.BookNowRepository;
import mk.ukim.finki.repository.UserRepository;
import mk.ukim.finki.service.BookNowService;
import mk.ukim.finki.service.CarsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookNowServiceImpl implements BookNowService {
    private final BookNowRepository bookNowRepository;
    private final UserRepository userRepository;
    private final CarsService carsService;

    public BookNowServiceImpl(BookNowRepository shoppingCartRepository, UserRepository userRepository, CarsService carsService) {
        this.bookNowRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.carsService = carsService;
    }

    @Override
    public List<Cars> listAllBookedCars(Long bookNowId) {
        if(!this.bookNowRepository.findById(bookNowId).isPresent())
            throw new BookNowNotFoundException(bookNowId);
        return this.bookNowRepository.findById(bookNowId).get().getCars();

    }

    @Override
    public BookNow getActiveBookNow(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return this.bookNowRepository
                .findByUserAndStatus(user, BookNowStatus.CREATED)
                .orElseGet(() -> {
                    BookNow bookNow = new BookNow(user);
                    return this.bookNowRepository.save(bookNow);
                });

    }

    @Override
    public BookNow bookNowCar(String username, Long carId) {
        BookNow bookNow = this.getActiveBookNow(username);
        Cars cars = this.carsService.findById(carId)
                .orElseThrow(() -> new CarNotFoundException(carId));
        if(bookNow.getCars()
                .stream().filter(i -> i.getId().equals(carId))
                .collect(Collectors.toList()).size() > 0)
            throw new CarIsAlreadyBookException(carId, username);
        bookNow.getCars().add(cars);
        return this.bookNowRepository.save(bookNow);

    }
    @Override
    public void deleteById(Long id) {
        this.bookNowRepository.deleteById(id);
    }

}
