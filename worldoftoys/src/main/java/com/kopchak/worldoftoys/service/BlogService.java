package com.kopchak.worldoftoys.service;

import com.kopchak.worldoftoys.dto.BlogPostDto;

import java.util.Set;

public interface BlogService {
    Set<BlogPostDto> getAllPosts();
    BlogPostDto getPostBySlug(String slug);
}
