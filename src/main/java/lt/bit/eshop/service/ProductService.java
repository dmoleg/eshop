package lt.bit.eshop.service;

import lt.bit.eshop.entity.CategoryEntity;
import lt.bit.eshop.entity.Product;
import lt.bit.eshop.form.ProductModel;
import lt.bit.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(ProductModel productModel) {

        Product productEntity = new Product();

        productEntity.setName(productModel.getName());
        productEntity.setDescription(productModel.getDescription());

        CategoryEntity newCategory = new CategoryEntity();
        newCategory.setId((long) 1);
        newCategory.setName("New category");

        productEntity.setCategory(newCategory);

        this.productRepository.save(productEntity);
    }

    public List<ProductModel> getProducts() {

        List<Product> products = (List<Product>) this.productRepository.findAll();

        return products.stream().map(ProductModel::new).collect(Collectors.toList());
    }
}
