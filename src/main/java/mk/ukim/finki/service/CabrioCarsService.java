package mk.ukim.finki.service;

import mk.ukim.finki.model.Cars;
import mk.ukim.finki.model.cars.CabrioCars;
import mk.ukim.finki.model.dto.CarsDto;

import java.util.List;
import java.util.Optional;

public interface CabrioCarsService {
    List<CabrioCars> getCabrioCars();

    Optional<CabrioCars> findById(Long id);


    Optional<CabrioCars> save(String make,String model, String fuelType, Integer year, String color, Integer power, Integer price);

    Optional<CabrioCars> edit(Long id, String make,String model, String fuelType, Integer year, String color, Integer power, Integer price);


    void deleteById(Long id);
}
