package com.kopchak.worldoftoys.repository.product.category;

import com.kopchak.worldoftoys.model.product.category.OriginCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginCategoryRepository extends JpaRepository<OriginCategory, Integer> {
    OriginCategory findByName(String name);
}
