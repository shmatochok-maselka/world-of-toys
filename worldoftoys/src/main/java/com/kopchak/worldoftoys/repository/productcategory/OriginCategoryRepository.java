package com.kopchak.worldoftoys.repository.productcategory;

import com.kopchak.worldoftoys.model.productcategory.OriginCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginCategoryRepository extends JpaRepository<OriginCategory, Integer> {
}
