package com.kopchak.worldoftoys.model.product;

import com.github.slugify.Slugify;
import com.kopchak.worldoftoys.model.product.category.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 60)
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 60, message = "Name must be up to 60 characters long")
    private String name;
    @Column(length = 80)
    @NotBlank(message = "Slug is mandatory")
    @Size(min = 3, max = 80, message = "Slug must be up to 80 characters long")
    private String slug;

    @Column(length = 500)
    @NotBlank(message = "Description is mandatory")
    @Size(max = 500, message = "Description must be up to 250 characters long")
    private String description;

    @Column(length = 200)
    @Size(max = 200, message = "Image must be up to 200 characters long")
    private String image;

    @NotBlank(message = "Price is mandatory")
    private BigDecimal price;

    @ManyToOne
    @NotBlank(message = "Origin category is mandatory")
    @JoinColumn(name = "origin_id")
    private OriginCategory originCategory;

    @ManyToOne
    @NotBlank(message = "Gender category is mandatory")
    @JoinColumn(name = "gender_id")
    private GenderCategory genderCategory;

    @ManyToOne
    @NotBlank(message = "Brand category is mandatory")
    @JoinColumn(name = "brand_id")
    private BrandCategory brandCategory;

    @ManyToOne
    @NotBlank(message = "Type category is mandatory")
    @JoinColumn(name = "type_id")
    private TypeCategory typeCategory;

    @ManyToMany
    @NotBlank(message = "Age categories is mandatory")
    private Set<AgeCategory> ageCategory;

    @PrePersist
    public void setSlug() {
        final Slugify slg = Slugify.builder().transliterator(true).build();
        this.slug = slg.slugify(this.name);
    }
}
