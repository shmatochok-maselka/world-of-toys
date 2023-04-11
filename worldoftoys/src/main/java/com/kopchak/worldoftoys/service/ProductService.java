package com.kopchak.worldoftoys.service;

import com.kopchak.worldoftoys.dto.AllProductCategoriesDto;
import com.kopchak.worldoftoys.dto.ProductDto;

import java.util.Set;

public interface ProductService {
    AllProductCategoriesDto getAllCategories();
    Set<ProductDto> getAllProducts();
    ProductDto getProductBySlug(String slug);
}
