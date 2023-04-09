package com.kopchak.worldoftoys.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    private String image;

    private int price;

    @ManyToOne
    @JoinColumn(name = "age_category_id")
    private AgeCategory ageCategory;


    @ManyToOne
    @JoinColumn(name = "origin_category_id")
    private OriginCategory originCategory;


    @ManyToOne
    @JoinColumn(name = "gender_category_id")
    private GenderCategory genderCategory;

    @ManyToMany
    private Set<TypeCategory> typeCategories;

    @ManyToOne
    @JoinColumn(name = "brand_category_id")
    private BrandCategory brandCategory;
}
