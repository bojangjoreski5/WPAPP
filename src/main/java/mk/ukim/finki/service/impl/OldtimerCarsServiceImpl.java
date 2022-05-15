package mk.ukim.finki.service.impl;

import mk.ukim.finki.model.cars.ClassicCars;
import mk.ukim.finki.model.cars.OldtimerCars;
import mk.ukim.finki.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.repository.OldtimerCarsRepository;
import mk.ukim.finki.service.OldtimerCarsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class OldtimerCarsServiceImpl implements OldtimerCarsService {
    private final OldtimerCarsRepository oldtimerCarsRepository;

    public OldtimerCarsServiceImpl(OldtimerCarsRepository oldtimerCarsRepository) {
        this.oldtimerCarsRepository = oldtimerCarsRepository;
    }


    @Override
    public List<OldtimerCars> getOldtimerCars() {
        return oldtimerCarsRepository.findAll();
    }

    @Override
    public Optional<OldtimerCars> findById(Long id) {
        return this.oldtimerCarsRepository.findById(id);
    }

    @Override
    public Optional<OldtimerCars> save(String make, String model, String fuelType,Integer year, String color, Integer price) {
        this.oldtimerCarsRepository.deleteByMake(make);
        return Optional.of(this.oldtimerCarsRepository.save(new OldtimerCars(make, model, fuelType, year, color, price)));
    }

    @Override
    public Optional<OldtimerCars> edit(Long id, String make, String model, String fuelType,Integer year, String color, Integer price) {
        OldtimerCars oldtimerCars = this.oldtimerCarsRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        oldtimerCars.setMake(make);
        oldtimerCars.setModel(model);
        oldtimerCars.setFuelType(fuelType);
        oldtimerCars.setYear(year);
        oldtimerCars.setColor(color);
        oldtimerCars.setPrice(price);

        return Optional.of(this.oldtimerCarsRepository.save(oldtimerCars));
    }

    @Override
    public void deleteOldtimerCars(Long id) {
        oldtimerCarsRepository.deleteById(id);
    }
}
