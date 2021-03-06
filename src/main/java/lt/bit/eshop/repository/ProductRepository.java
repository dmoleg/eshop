package lt.bit.eshop.repository;

import lt.bit.eshop.entity.CategoryEntity;
import lt.bit.eshop.entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(CategoryEntity entity);

    List<Product> findByCategoryAndNameContaining(CategoryEntity entity, String name);

    List<Product> findByNameContaining(String name, Sort sort);
}
