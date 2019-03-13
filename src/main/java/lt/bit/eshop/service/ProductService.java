package lt.bit.eshop.service;

import lt.bit.eshop.entity.Product;
import lt.bit.eshop.form.ProductModel;
import lt.bit.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(ProductModel productModel) {

        Product productEntity = new Product();

        productEntity.setName(productModel.getName());
        productEntity.setDescription(productModel.getDescription());

        this.productRepository.save(productEntity);
    }
}
