package mk.ukim.finki.service.impl;

import mk.ukim.finki.model.cars.CabrioCars;
import mk.ukim.finki.model.cars.ClassicCars;
import mk.ukim.finki.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.repository.ClassicCarsRepository;
import mk.ukim.finki.service.ClassicCarsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ClassicCarsServiceImpl implements ClassicCarsService {
    private final ClassicCarsRepository classicCarsRepository;

    public ClassicCarsServiceImpl(ClassicCarsRepository classicCarsRepository) {
        this.classicCarsRepository = classicCarsRepository;
    }

    @Override
    public List<ClassicCars> getClassicCars() {
        return classicCarsRepository.findAll();
    }

    @Override
        public Optional<ClassicCars> findById(Long id) {
            return this.classicCarsRepository.findById(id);
        }

    @Override
    public Optional<ClassicCars> save(String make, String model, String fuelType, String color, Integer price) {
        this.classicCarsRepository.deleteByMake(make);
        return Optional.of(this.classicCarsRepository.save(new ClassicCars(make, model, fuelType, color, price)));
    }

    @Override
    public Optional<ClassicCars> edit(Long id, String make, String model, String fuelType, String color, Integer price) {
        ClassicCars classicCars = this.classicCarsRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        classicCars.setMake(make);
        classicCars.setModel(model);
        classicCars.setFuelType(fuelType);
        classicCars.setColor(color);
        classicCars.setPrice(price);

        return Optional.of(this.classicCarsRepository.save(classicCars));
    }

    @Override
    public void deleteClassicCars(Long id) {
        classicCarsRepository.deleteById(id);
    }
}
