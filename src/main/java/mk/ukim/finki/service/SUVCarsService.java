package mk.ukim.finki.service;

import mk.ukim.finki.model.cars.SUVCars;
import mk.ukim.finki.model.cars.SportCars;

import java.util.List;
import java.util.Optional;

public interface SUVCarsService {
    List<SUVCars> getSUVCars();

    Optional<SUVCars> findById(Long id);

    Optional<SUVCars> save(String make, String model, String fuelType,Integer year, String color, Integer power, Integer price);

    Optional<SUVCars> edit(Long id, String make, String model, String fuelType,Integer year, String color, Integer power, Integer price);

    void deleteSUVCars(Long id);
}
