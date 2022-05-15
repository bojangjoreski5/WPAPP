package mk.ukim.finki.model.cars;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
//@NoArgsConstructor
//@AllArgsConstructor
public class SUVCars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;

    private String model;

    private String fuelType;

    private Integer year;

    private String color;

    private Integer power;

    private Integer price;

    public SUVCars() {
    }

    public SUVCars(String make, String model, String fuelType, Integer year, String color, Integer power, Integer price) {
        this.make = make;
        this.model = model;
        this.fuelType = fuelType;
        this.year = year;
        this.color = color;
        this.power = power;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getFuelType() {
        return fuelType;
    }

    public Integer getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public Integer getPower() {
        return power;
    }

    public Integer getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
