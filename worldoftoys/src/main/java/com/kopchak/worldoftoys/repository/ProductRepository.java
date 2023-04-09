package com.kopchak.worldoftoys.repository;

import com.kopchak.worldoftoys.model.productcategory.BrandCategory;
import com.kopchak.worldoftoys.model.Product;
import com.kopchak.worldoftoys.model.productcategory.TypeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT DISTINCT p.brandCategory FROM Product p WHERE p.typeCategory = :typeCategory")
    List<BrandCategory> findAllBrandsByTypeCategory(@Param("typeCategory") TypeCategory typeCategory);
}
