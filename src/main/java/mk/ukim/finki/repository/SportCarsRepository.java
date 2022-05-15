package mk.ukim.finki.repository;

import jdk.jfr.Registered;
import mk.ukim.finki.model.cars.SportCars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportCarsRepository extends JpaRepository<SportCars, Long> {
    void deleteByMake(String make);
}
