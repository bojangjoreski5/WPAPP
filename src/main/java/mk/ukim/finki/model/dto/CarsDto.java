package mk.ukim.finki.model.dto;

public class CarsDto {
    private String name;

    private Double price;

    private Long category;

    private Long id;

    public CarsDto() {
    }

    public CarsDto(String name, Double price, Integer quantity, Long category, Long manufacturer) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.id = id;
    }
}
