package mk.ukim.finki.model;

import lombok.Data;
import mk.ukim.finki.model.enums.BookNowStatus;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class BookNow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Cars> cars;

    @Enumerated(EnumType.STRING)
    private BookNowStatus status;

    public BookNow() {
    }

    public BookNow(User user) {
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.cars = new ArrayList<>();
        this.status = BookNowStatus.CREATED;
    }

    public List<Cars> getCars() {
        return cars;
    }

    public void setCars(List<Cars> cars) {
        this.cars = cars;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public User getUser() {
        return user;
    }

    public BookNowStatus getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStatus(BookNowStatus status) {
        this.status = status;
    }
}
