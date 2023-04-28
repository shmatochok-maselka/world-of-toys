package com.kopchak.worldoftoys.service.impl;

import com.kopchak.worldoftoys.dto.product.AllProductCategoriesDto;
import com.kopchak.worldoftoys.dto.product.ProductCategoryDto;
import com.kopchak.worldoftoys.dto.product.ProductShopDto;
import com.kopchak.worldoftoys.dto.product.TypeCategoryDto;
import com.kopchak.worldoftoys.exception.ProductNotFoundException;
import com.kopchak.worldoftoys.model.product.Product;
import com.kopchak.worldoftoys.model.product.category.AgeCategory;
import com.kopchak.worldoftoys.repository.product.ProductRepository;
import com.kopchak.worldoftoys.repository.product.category.*;
import com.kopchak.worldoftoys.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
    private BrandCategoryRepository brandRepository;

    @Override
    public AllProductCategoriesDto getAllCategories() {
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
    public Set<ProductShopDto> getAllProducts() {
        return productRepository.findAll().stream().map(ProductShopDto::new).collect(Collectors.toSet());
    }

    public ProductShopDto getProductBySlug(String slug) {
        Product product = productRepository.findBySlug(slug);
        if (slug == null || product == null) {
            throw new ProductNotFoundException(HttpStatus.BAD_REQUEST, "Product does not exist!");
        }
        return new ProductShopDto(productRepository.findBySlug(slug));
    }

    @Override
    public void createProduct(ProductShopDto productShopDto) {
        Set<AgeCategory> ageCategories = productShopDto.getAgeCategory()
                .stream()
                .map(ageCategory -> ageRepository.findByName(ageCategory.getName()))
                .collect(Collectors.toSet());
        Product product = Product
                .builder()
                .name(productShopDto.getName())
                .description(productShopDto.getDescription())
                .image(productShopDto.getImage())
                .price(productShopDto.getPrice())
                .originCategory(originRepository.findByName(productShopDto.getOriginCategory().getName()))
                .genderCategory(genderRepository.findByName(productShopDto.getGenderCategory().getName()))
                .brandCategory(brandRepository.findByName(productShopDto.getBrandCategory().getName()))
                .typeCategory(typeRepository.findByName(productShopDto.getTypeCategory().getName()))
                .ageCategory(ageCategories)
                .build();
        productRepository.save(product);
    }

    private Set<TypeCategoryDto> getTypeCategoriesWithBrands() {
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
