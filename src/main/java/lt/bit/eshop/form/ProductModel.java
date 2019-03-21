package lt.bit.eshop.form;

import lt.bit.eshop.entity.Product;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

public class ProductModel {

    private Long id;
    @NotBlank(message = "Names is required")
    private String name;

    @Length(min = 5, max = 1000, message = "Between 5 and 100 symbols")
    private String description = "Some description";

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.1", inclusive = true, message = "Min value 0.1")
    private Double price;

    public ProductModel(Product entity) {
        this.setId(entity.getId());
        this.setName(entity.getName());
        this.setDescription(entity.getDescription());
        this.setPrice(entity.getPrice());
    }

    public ProductModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
