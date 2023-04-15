package com.kopchak.worldoftoys.service;

import com.kopchak.worldoftoys.dto.AllProductCategoriesDto;
import com.kopchak.worldoftoys.dto.ProductShopDto;

import java.util.Set;

public interface ProductService {
    AllProductCategoriesDto getAllCategories();
    Set<ProductShopDto> getAllProducts();
    ProductShopDto getProductBySlug(String slug);
}
