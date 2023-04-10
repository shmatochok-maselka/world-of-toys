package com.kopchak.worldoftoys.repository;

import com.kopchak.worldoftoys.model.Product;
import com.kopchak.worldoftoys.model.productcategory.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT min(price) FROM Product")
    Double findMinProductPrice();

    @Query(value = "SELECT max(price) FROM Product")
    Double findMaxProductPrice();

    @Query("SELECT DISTINCT p.brandCategory FROM Product p WHERE p.typeCategory = :typeCategory")
    Set<BrandCategory> findAllBrandsByTypeCategory(@Param("typeCategory") TypeCategory typeCategory);
}
