package mk.ukim.finki.repository;

import mk.ukim.finki.model.cars.ClassicCars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassicCarsRepository extends JpaRepository<ClassicCars, Long> {
    void deleteByMake(String make);
}
