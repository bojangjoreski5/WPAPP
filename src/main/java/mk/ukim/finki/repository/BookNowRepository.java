package mk.ukim.finki.repository;

import jdk.jfr.Registered;
import mk.ukim.finki.model.BookNow;
import mk.ukim.finki.model.User;
import mk.ukim.finki.model.enums.BookNowStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.Optional;

@Repository
public interface BookNowRepository extends JpaRepository<BookNow, Long> {
    Optional<BookNow> findByUserAndStatus(User user, BookNowStatus status);

}
