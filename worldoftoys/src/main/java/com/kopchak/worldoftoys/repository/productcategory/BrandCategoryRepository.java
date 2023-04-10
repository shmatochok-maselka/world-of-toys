package com.kopchak.worldoftoys.repository.productcategory;

import com.kopchak.worldoftoys.model.productcategory.BrandCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandCategoryRepository extends JpaRepository<BrandCategory, Integer> {
}
