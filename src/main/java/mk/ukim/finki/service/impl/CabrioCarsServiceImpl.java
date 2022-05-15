package mk.ukim.finki.service.impl;

import mk.ukim.finki.model.Cars;
import mk.ukim.finki.model.Category;
import mk.ukim.finki.model.cars.CabrioCars;
import mk.ukim.finki.model.exceptions.CategoryNotFoundException;
import mk.ukim.finki.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.repository.CabrioCarsRepository;
import mk.ukim.finki.service.CabrioCarsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CabrioCarsServiceImpl implements CabrioCarsService {
    private final CabrioCarsRepository cabrioCarsRepository;

    public CabrioCarsServiceImpl(CabrioCarsRepository cabrioCarsRepository) {
        this.cabrioCarsRepository = cabrioCarsRepository;
    }

    @Override
    public List<CabrioCars> getCabrioCars() {
        return cabrioCarsRepository.findAll();
    }

    @Override
    public Optional<CabrioCars> findById(Long id) {
        return this.cabrioCarsRepository.findById(id);
    }


    @Override
    public Optional<CabrioCars> save(String make, String model, String fuelType, Integer year, String color, Integer power, Integer price) {
        this.cabrioCarsRepository.deleteByMake(make);
        return Optional.of(this.cabrioCarsRepository.save(new CabrioCars(make, model, fuelType, year, color, power, price)));
    }

    @Override
    public Optional<CabrioCars> edit(Long id, String make, String model, String fuelType, Integer year, String color, Integer power, Integer price) {
        CabrioCars cabrioCars = this.cabrioCarsRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        cabrioCars.setMake(make);
        cabrioCars.setModel(model);
        cabrioCars.setFuelType(fuelType);
        cabrioCars.setYear(year);
        cabrioCars.setColor(color);
        cabrioCars.setPower(power);
        cabrioCars.setPrice(price);

        return Optional.of(this.cabrioCarsRepository.save(cabrioCars));
    }


    @Override
    public void deleteById(Long id) {
        cabrioCarsRepository.deleteById(id);
    }

}
