package mk.ukim.finki.service;

import mk.ukim.finki.model.cars.OldtimerCars;
import mk.ukim.finki.model.cars.SportCars;

import java.util.List;
import java.util.Optional;

public interface SportCarsService {
    List<SportCars> getSportCars();

    Optional<SportCars> findById(Long id);

    Optional<SportCars> save(String make, String model, String fuelType, String color, Integer power, Integer price);

    Optional<SportCars> edit(Long id, String make, String model, String fuelType, String color, Integer power, Integer price);

    void deleteSportCars(Long id);
}
