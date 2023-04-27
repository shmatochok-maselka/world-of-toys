package com.kopchak.worldoftoys.repository.product.category;

import com.kopchak.worldoftoys.model.product.category.AgeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgeCategoryRepository extends JpaRepository<AgeCategory, Integer> {
}
