package com.kopchak.worldoftoys.repository;

import com.kopchak.worldoftoys.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {
    @Query("SELECT post FROM BlogPost post WHERE post.slug = :slug")
    BlogPost findBySlug(@Param("slug") String slug);
}
