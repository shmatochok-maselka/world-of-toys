package com.kopchak.worldoftoys.dto.blog;

import com.kopchak.worldoftoys.model.blog.BlogPost;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogPostDto {
    private String title;
    private String slug;
    private String image;
    private String content;
    private String author;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public BlogPostDto(BlogPost blogPost) {
        this.title = blogPost.getTitle();
        this.slug = blogPost.getSlug();
        this.image = blogPost.getImage();
        this.content = blogPost.getContent();
        this.author = blogPost.getAuthor();
        this.createdAt = blogPost.getCreatedAt();
        this.updatedAt = blogPost.getUpdatedAt();
    }
}
