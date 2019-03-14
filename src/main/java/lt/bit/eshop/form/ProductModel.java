package lt.bit.eshop.form;

import javax.validation.constraints.NotBlank;

public class ProductModel {

    @NotBlank(message = "Names is required")
    private String name;

    private String description;

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
}
