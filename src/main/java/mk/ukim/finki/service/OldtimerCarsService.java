package mk.ukim.finki.service;

import mk.ukim.finki.model.cars.ClassicCars;
import mk.ukim.finki.model.cars.OldtimerCars;

import java.util.List;
import java.util.Optional;

public interface OldtimerCarsService {
    List<OldtimerCars> getOldtimerCars();

    Optional<OldtimerCars> findById(Long id);

    Optional<OldtimerCars> save(String make, String model, String fuelType,Integer year, String color, Integer price);

    Optional<OldtimerCars> edit(Long id, String make,String model, String fuelType, Integer year, String color, Integer price);

    void deleteOldtimerCars(Long id);
}
