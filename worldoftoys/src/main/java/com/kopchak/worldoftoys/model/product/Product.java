package com.kopchak.worldoftoys.model.product;

import com.github.slugify.Slugify;
import com.kopchak.worldoftoys.model.product.category.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
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
    @Column(unique = true, length = 60)
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 60, message = "Name must be up to 60 characters long")
    private String name;
    @Column(unique = true, length = 80)
    @NotBlank(message = "Slug is mandatory")
    @Size(min = 3, max = 80, message = "Slug must be up to 80 characters long")
    private String slug;

    @Column(length = 250)
    @NotBlank(message = "Description is mandatory")
    @Size(max = 250, message = "Description must be up to 250 characters long")
    private String description;

    @Column(length = 200)
    @Size(max = 200, message = "Image must be up to 200 characters long")
    private String image;

    @NotNull(message = "Price is mandatory")
    private BigDecimal price;

    @Builder.Default
    @NonNull
    @Min(value = 0, message = "Available quantity must be greater than or equal to 0")
    private BigInteger availableQuantity = BigInteger.ZERO;

    @ManyToOne
    @NotNull(message = "Origin category is mandatory")
    @JoinColumn(name = "origin_id")
    private OriginCategory originCategory;

    @ManyToOne
    @NotNull(message = "Gender category is mandatory")
    @JoinColumn(name = "gender_id")
    private GenderCategory genderCategory;

    @ManyToOne
    @NotNull(message = "Brand category is mandatory")
    @JoinColumn(name = "brand_id")
    private BrandCategory brandCategory;

    @ManyToOne
    @NotNull(message = "Type category is mandatory")
    @JoinColumn(name = "type_id")
    private TypeCategory typeCategory;

    @ManyToMany
    @NotNull(message = "Age categories is mandatory")
    private Set<AgeCategory> ageCategory;

    @PrePersist
    public void setSlug() {
        final Slugify slg = Slugify.builder().transliterator(true).build();
        this.slug = slg.slugify(this.name);
    }
}
