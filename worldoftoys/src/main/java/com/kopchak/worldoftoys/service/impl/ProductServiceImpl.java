package com.kopchak.worldoftoys.service.impl;

import com.kopchak.worldoftoys.dto.AllProductCategoriesDto;
import com.kopchak.worldoftoys.dto.ProductCategoryDto;
import com.kopchak.worldoftoys.dto.ProductDto;
import com.kopchak.worldoftoys.dto.TypeCategoryDto;
import com.kopchak.worldoftoys.exception.ProductNotFoundException;
import com.kopchak.worldoftoys.model.Product;
import com.kopchak.worldoftoys.model.productcategory.ProductCategories;
import com.kopchak.worldoftoys.model.productcategory.TypeCategory;
import com.kopchak.worldoftoys.repository.ProductRepository;
import com.kopchak.worldoftoys.repository.productcategory.AgeCategoryRepository;
import com.kopchak.worldoftoys.repository.productcategory.GenderCategoryRepository;
import com.kopchak.worldoftoys.repository.productcategory.OriginCategoryRepository;
import com.kopchak.worldoftoys.repository.productcategory.TypeCategoryRepository;
import com.kopchak.worldoftoys.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private AgeCategoryRepository ageRepository;
    private GenderCategoryRepository genderRepository;
    private OriginCategoryRepository originRepository;
    private TypeCategoryRepository typeRepository;

    @Override
    public AllProductCategoriesDto getAllCategories(){
        Double minProductPrice = productRepository.findMinProductPrice();
        Double maxProductPrice = productRepository.findMaxProductPrice();
        var ageCategories = ageRepository.findAll()
                .stream()
                .map(ProductCategoryDto::new)
                .collect(Collectors.toSet());
        var genderCategories = genderRepository.findAll().stream()
                .map(ProductCategoryDto::new)
                .collect(Collectors.toSet());
        var originCategories = originRepository.findAll().stream()
                .map(ProductCategoryDto::new)
                .collect(Collectors.toSet());
        Set<TypeCategoryDto> typeCategoriesDto = getTypeCategoriesWithBrands();
        return AllProductCategoriesDto
                .builder()
                .minProductPrice(minProductPrice)
                .maxProductPrice(maxProductPrice)
                .ageCategories(ageCategories)
                .genderCategories(genderCategories)
                .originCategories(originCategories)
                .typeCategories(typeCategoriesDto)
                .build();
    }

    @Override
    public Set<ProductDto> getAllProducts(){
        return productRepository.findAll().stream().map(ProductDto::new).collect(Collectors.toSet());
    }

    public ProductDto getProductBySlug(String slug){
        Product product = productRepository.findBySlug(slug);
        if(slug == null || product == null){
            throw new ProductNotFoundException(HttpStatus.BAD_REQUEST, "Product does not exist!");
        }
        return new ProductDto(productRepository.findBySlug(slug));
    }

    private Set<TypeCategoryDto> getTypeCategoriesWithBrands(){
        return typeRepository.findAll().stream()
                .map(typeCategory -> {
                    Set<ProductCategoryDto> brandCategory = productRepository.findAllBrandsByTypeCategory(typeCategory)
                            .stream()
                            .map(ProductCategoryDto::new)
                            .collect(Collectors.toSet());
                    return new TypeCategoryDto(typeCategory.getName(), brandCategory);
                })
                .collect(Collectors.toSet());
    }
}
