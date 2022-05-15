package mk.ukim.finki.service;

import mk.ukim.finki.model.Cars;
import mk.ukim.finki.model.dto.CarsDto;

import java.util.List;
import java.util.Optional;

public interface CarsService {
    List<Cars> listAll();

    Optional<Cars> findById(Long id);

    Optional<Cars> findByName(String name);

    Optional<Cars> save(String name, Integer price, Long categoryId);

    Optional<Cars> save(CarsDto carsDto);

    Optional<Cars> edit(Long id, String name, Integer price, Long categoryId);

    Optional<Cars> edit(Long id, CarsDto carsDto);

    void deleteById(Long id);

}
