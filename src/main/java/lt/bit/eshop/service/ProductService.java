package lt.bit.eshop.service;

import lt.bit.eshop.ProductNotFound;
import lt.bit.eshop.entity.CategoryEntity;
import lt.bit.eshop.entity.Product;
import lt.bit.eshop.form.CategoryModel;
import lt.bit.eshop.form.ProductModel;
import lt.bit.eshop.repository.CategoryRepository;
import lt.bit.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.web.client.HttpClientErrorException.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public void createProduct(ProductModel productModel) {

        Product productEntity = new Product();

        productEntity.setId(productModel.getId());
        productEntity.setName(productModel.getName());
        productEntity.setDescription(productModel.getDescription());
        productEntity.setCategory(getCategoryById(productModel.getCategoryId()));
        productEntity.setPrice(productModel.getPrice());

        this.productRepository.save(productEntity);
    }

    public List<ProductModel> getAllProducts() {
        Sort sort = new Sort(Sort.Direction.DESC, "price");
        return this.getAllProducts(null, sort);
    }

    public List<ProductModel> getAllProducts(String name, Sort sort) {
        List<Product> products;

        if (name != null) {
           products = this.productRepository.findByNameContaining(name, sort);
        } else {
            products = this.productRepository.findAll(sort);
        }

        return products.stream().map(ProductModel::new).collect(Collectors.toList());
    }

    public void createCategory(String name) {
        String slug = name
                .trim()
                .toLowerCase()
                .replace(" ", "-");

        CategoryEntity entity = new CategoryEntity(name, slug);

        categoryRepository.save(entity);
    }

    public List<CategoryModel> getCategories() {
        List<CategoryEntity> categories  = (List<CategoryEntity>) categoryRepository.findAll();

        return categories.stream()
                .map(CategoryModel::new)
                .collect(Collectors.toList());
    }

    public CategoryEntity findCategory(String categorySlug) {

        return categoryRepository.findBySlug(categorySlug);
    }

    public List<ProductModel> getCategoryProducts(CategoryEntity categoryEntity) {
        List<Product> products = productRepository.findByCategory(categoryEntity);


        return products.stream()
                .map(ProductModel::new)
                .collect(Collectors.toList());

    }

    public ProductModel getProductById(Long id) throws NotFound, ProductNotFound {
        Optional<Product> productOptional = productRepository.findById(id);

        if (!productOptional.isPresent()) {
            throw new ProductNotFound("Product not found");
        }

        return new ProductModel(productOptional.get());
    }

    public CategoryEntity getCategoryById(Long id) {
        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(id);

        return categoryOptional.get();
    }
}
