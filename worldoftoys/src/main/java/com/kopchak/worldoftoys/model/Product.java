package com.kopchak.worldoftoys.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.slugify.Slugify;
import com.kopchak.worldoftoys.model.productcategory.*;
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
    private String slug;

    private String description;

    private String image;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "origin_id")
    private OriginCategory originCategory;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    private GenderCategory genderCategory;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandCategory brandCategory;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private TypeCategory typeCategory;

    @ManyToMany
    private Set<AgeCategory> ageCategory;

    @PrePersist
    public void setSlug() {
        final Slugify slg = Slugify.builder().transliterator(true).build();
        this.slug = slg.slugify(this.name);
    }

}
