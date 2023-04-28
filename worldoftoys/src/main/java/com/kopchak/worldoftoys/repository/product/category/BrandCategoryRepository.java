package com.kopchak.worldoftoys.repository.product.category;

import com.kopchak.worldoftoys.model.product.category.BrandCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandCategoryRepository extends JpaRepository<BrandCategory, Integer> {
    BrandCategory findByName(String name);
}
