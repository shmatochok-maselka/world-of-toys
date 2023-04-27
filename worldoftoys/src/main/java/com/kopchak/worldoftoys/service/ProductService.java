package com.kopchak.worldoftoys.service;

import com.kopchak.worldoftoys.dto.product.AllProductCategoriesDto;
import com.kopchak.worldoftoys.dto.product.ProductShopDto;

import java.util.Set;

public interface ProductService {
    AllProductCategoriesDto getAllCategories();
    Set<ProductShopDto> getAllProducts();
    ProductShopDto getProductBySlug(String slug);
}
