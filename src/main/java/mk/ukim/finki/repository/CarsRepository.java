package mk.ukim.finki.repository;

import mk.ukim.finki.model.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CarsRepository extends JpaRepository<Cars, Long> {
    Optional<Cars> findByName(String name);
    void deleteByName(String name);
}
