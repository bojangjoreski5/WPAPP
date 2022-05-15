package mk.ukim.finki.service;

import mk.ukim.finki.model.cars.CabrioCars;
import mk.ukim.finki.model.cars.ClassicCars;

import java.util.List;
import java.util.Optional;

public interface ClassicCarsService {
    List<ClassicCars> getClassicCars();

    Optional<ClassicCars> findById(Long id);

    Optional<ClassicCars> save(String make, String model, String fuelType, String color, Integer price);

    Optional<ClassicCars> edit(Long id, String make,String model, String fuelType, String color, Integer price);



    void deleteClassicCars(Long id);
}
