package mk.ukim.finki.service.impl;

import mk.ukim.finki.model.Cars;
import mk.ukim.finki.model.Category;
import mk.ukim.finki.model.dto.CarsDto;
import mk.ukim.finki.model.exceptions.CategoryNotFoundException;
import mk.ukim.finki.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.repository.CarsRepository;
import mk.ukim.finki.repository.CategoryRepository;
import mk.ukim.finki.service.CarsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CarsServiceImpl implements CarsService {

    private final CarsRepository carsRepository;
    private final CategoryRepository categoryRepository;


    public CarsServiceImpl(CarsRepository carsRepository, CategoryRepository categoryRepository) {
        this.carsRepository = carsRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Cars> listAll() {
        return this.carsRepository.findAll();
    }

    @Override
    public Optional<Cars> findById(Long id) {
        return this.carsRepository.findById(id);
    }

    @Override
    public Optional<Cars> findByName(String name) {
        return this.carsRepository.findByName(name);
    }

    @Override
    public Optional<Cars> save(String name, Integer price, Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        this.carsRepository.deleteByName(name);
        return Optional.of(this.carsRepository.save(new Cars(name, price, category)));
    }

    @Override
    public Optional<Cars> save(CarsDto carsDto) {
        return Optional.empty();
    }

    @Override
    public Optional<Cars> edit(Long id, String name, Integer price, Long categoryId) {
        Cars cars = this.carsRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        cars.setName(name);
        cars.setPrice(price);

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        cars.setCategory(category);

        return Optional.of(this.carsRepository.save(cars));
    }

    @Override
    public Optional<Cars> edit(Long id, CarsDto carsDto) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        this.carsRepository.deleteById(id);
    }


}
