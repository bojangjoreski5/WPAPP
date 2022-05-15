package mk.ukim.finki.service.impl;

import mk.ukim.finki.model.cars.SUVCars;
import mk.ukim.finki.model.cars.SportCars;
import mk.ukim.finki.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.repository.SUVCarsRepository;
import mk.ukim.finki.service.SUVCarsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class SUVCarsServiceImpl implements SUVCarsService {

    private final SUVCarsRepository suvCarsRepository;

    public SUVCarsServiceImpl(SUVCarsRepository suvCarsRepository) {
        this.suvCarsRepository = suvCarsRepository;
    }

    @Override
    public List<SUVCars> getSUVCars() {
        return suvCarsRepository.findAll();
    }

    @Override
    public Optional<SUVCars> findById(Long id) {
        return this.suvCarsRepository.findById(id);
    }

    @Override
    public Optional<SUVCars> save(String make, String model, String fuelType,Integer year, String color, Integer power, Integer price) {
        this.suvCarsRepository.deleteByMake(make);
        return Optional.of(this.suvCarsRepository.save(new SUVCars(make, model, fuelType, year, color, power, price)));
    }

    @Override
    public Optional<SUVCars> edit(Long id, String make, String model, String fuelType,Integer year, String color, Integer power, Integer price) {
        SUVCars suvCars = this.suvCarsRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        suvCars.setMake(make);
        suvCars.setModel(model);
        suvCars.setFuelType(fuelType);
        suvCars.setYear(year);
        suvCars.setColor(color);
        suvCars.setPower(power);
        suvCars.setPrice(price);

        return Optional.of(this.suvCarsRepository.save(suvCars));
    }

    @Override
    public void deleteSUVCars(Long id) {
        suvCarsRepository.deleteById(id);
    }
}
