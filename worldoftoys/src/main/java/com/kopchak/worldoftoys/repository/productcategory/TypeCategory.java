package com.kopchak.worldoftoys.repository.productcategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeCategory extends JpaRepository<TypeCategory, Integer> {
}
