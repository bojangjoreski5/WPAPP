package mk.ukim.finki.repository;

import mk.ukim.finki.model.cars.OldtimerCars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OldtimerCarsRepository extends JpaRepository<OldtimerCars, Long> {
    void deleteByMake(String make);
}
