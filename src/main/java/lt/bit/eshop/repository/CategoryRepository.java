package lt.bit.eshop.repository;

import lt.bit.eshop.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {

    CategoryEntity findBySlug(String slug);
}
