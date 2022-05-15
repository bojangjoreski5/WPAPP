package mk.ukim.finki.repository;

import mk.ukim.finki.model.cars.SUVCars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SUVCarsRepository extends JpaRepository<SUVCars, Long> {
    void deleteByMake(String make);
}
