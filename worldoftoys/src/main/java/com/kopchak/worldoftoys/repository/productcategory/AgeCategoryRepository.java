package com.kopchak.worldoftoys.repository.productcategory;

import com.kopchak.worldoftoys.model.productcategory.AgeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgeCategoryRepository extends JpaRepository<AgeCategory, Integer> {
}
