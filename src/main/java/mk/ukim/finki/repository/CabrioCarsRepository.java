package mk.ukim.finki.repository;

import mk.ukim.finki.model.cars.CabrioCars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CabrioCarsRepository extends JpaRepository<CabrioCars, Long> {
    void deleteByMake(String make);
}
