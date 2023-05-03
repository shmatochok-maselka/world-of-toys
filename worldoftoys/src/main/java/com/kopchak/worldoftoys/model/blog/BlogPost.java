package com.kopchak.worldoftoys.model.blog;

import com.github.slugify.Slugify;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 80)
    @NotBlank(message = "Title is mandatory")
    @Size(min = 10, max = 80, message = "Title must be up to 80 characters long")
    private String title;

    @Column(length = 100)
    @NotBlank(message = "Slug is mandatory")
    @Size(min = 10, max = 100, message = "Slug must be up to 80 characters long")
    private String slug;

    @Column(length = 200)
    @Size(max = 200, message = "Image must be up to 200 characters long")
    private String image;

    @Column(length = 1500)
    @NotBlank(message = "Content is mandatory")
    @Size(min = 250, max = 1500, message = "Content must be up to 250 characters long")
    private String content;

    @Column(length = 120)
    @NotBlank(message = "Author is mandatory")
    @Size(max = 120, message = "Author must be up to 120 characters long")
    private String author;

    @Column(nullable = false)
    @NotNull
    private LocalDate createdAt;

    private LocalDate updatedAt;

    @PrePersist
    public void setSlug() {
        final Slugify slg = Slugify.builder().transliterator(true).build();
        this.slug = slg.slugify(this.title);
    }
}
