package com.kopchak.worldoftoys.repository.product.category;

import com.kopchak.worldoftoys.model.product.category.GenderCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderCategoryRepository extends JpaRepository<GenderCategory, Integer> {
    GenderCategory findByName(String name);
}
