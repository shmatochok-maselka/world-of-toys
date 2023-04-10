package com.kopchak.worldoftoys.repository.productcategory;

import com.kopchak.worldoftoys.model.productcategory.AgeCategory;
import com.kopchak.worldoftoys.model.productcategory.ProductCategories;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgeCategoryRepository extends JpaRepository<AgeCategory, Integer> {
}
