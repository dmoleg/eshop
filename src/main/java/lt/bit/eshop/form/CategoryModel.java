package lt.bit.eshop.form;

import lt.bit.eshop.entity.CategoryEntity;

import javax.validation.constraints.NotBlank;

public class CategoryModel {

    @NotBlank(message = "Category name is required")
    private String name;

    private String slug;

    public CategoryModel() {
    }

    public CategoryModel(CategoryEntity entity) {
        this.name = entity.getName();
        this.slug = entity.getSlug();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
