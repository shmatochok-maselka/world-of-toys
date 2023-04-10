package com.kopchak.worldoftoys.repository.productcategory;

import com.kopchak.worldoftoys.model.productcategory.GenderCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderCategoryRepository extends JpaRepository<GenderCategory, Integer> {
}
