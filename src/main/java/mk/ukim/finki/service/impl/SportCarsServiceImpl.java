package mk.ukim.finki.service.impl;


import mk.ukim.finki.model.cars.OldtimerCars;
import mk.ukim.finki.model.cars.SportCars;
import mk.ukim.finki.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.repository.SportCarsRepository;
import mk.ukim.finki.service.SportCarsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class SportCarsServiceImpl implements SportCarsService {

    private final SportCarsRepository sportCarsRepository;

    public SportCarsServiceImpl(SportCarsRepository sportCarsRepository) {
        this.sportCarsRepository = sportCarsRepository;
    }

    @Override
    public List<SportCars> getSportCars() {
        return sportCarsRepository.findAll();
    }

    @Override
    public Optional<SportCars> findById(Long id) {
        return this.sportCarsRepository.findById(id);
    }

    @Override
    public Optional<SportCars> save(String make, String model, String fuelType, String color, Integer power, Integer price) {
        this.sportCarsRepository.deleteByMake(make);
        return Optional.of(this.sportCarsRepository.save(new SportCars(make, model, fuelType, color, power, price)));
    }

    @Override
    public Optional<SportCars> edit(Long id, String make, String model, String fuelType, String color, Integer power, Integer price) {
        SportCars sportCars = this.sportCarsRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        sportCars.setMake(make);
        sportCars.setModel(model);
        sportCars.setFuelType(fuelType);
        sportCars.setColor(color);
        sportCars.setPower(power);
        sportCars.setPrice(price);

        return Optional.of(this.sportCarsRepository.save(sportCars));
    }

    @Override
    public void deleteSportCars(Long id) {

        sportCarsRepository.deleteById(id);
    }
}
