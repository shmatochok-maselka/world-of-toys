package com.kopchak.worldoftoys.repository.product;

import com.kopchak.worldoftoys.model.product.Product;
import com.kopchak.worldoftoys.model.product.category.BrandCategory;
import com.kopchak.worldoftoys.model.product.category.TypeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT min(price) FROM Product")
    Double findMinProductPrice();

    @Query(value = "SELECT max(price) FROM Product")
    Double findMaxProductPrice();

    @Query("SELECT p FROM Product p WHERE p.slug = :slug")
    Product findBySlug(@Param("slug") String slug);

    @Query("SELECT DISTINCT p.brandCategory FROM Product p WHERE p.typeCategory = :typeCategory")
    Set<BrandCategory> findAllBrandsByTypeCategory(@Param("typeCategory") TypeCategory typeCategory);
}
