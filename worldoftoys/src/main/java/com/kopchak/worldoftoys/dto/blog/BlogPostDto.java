package com.kopchak.worldoftoys.dto.blog;

import com.kopchak.worldoftoys.model.blog.BlogPost;
import lombok.*;

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

    public BlogPostDto(BlogPost blogPost) {
        this.title = blogPost.getTitle();
        this.slug = blogPost.getSlug();
        this.image = blogPost.getImage();
        this.content = blogPost.getContent();
    }
}
