package com.kopchak.worldoftoys.service.impl;

import com.kopchak.worldoftoys.dto.blog.BlogPostDto;
import com.kopchak.worldoftoys.exception.BlogPostNotFoundException;
import com.kopchak.worldoftoys.model.blog.BlogPost;
import com.kopchak.worldoftoys.repository.blog.BlogPostRepository;
import com.kopchak.worldoftoys.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BlogServiceImpl implements BlogService {
    private BlogPostRepository blogPostRepository;

    @Override
    public Set<BlogPostDto> getAllPosts() {
        return blogPostRepository
                .findAll()
                .stream()
                .map(BlogPostDto::new)
                .collect(Collectors.toSet());
    }

    @Override
    public BlogPostDto getPostBySlug(String slug) {
        BlogPost blogPost = blogPostRepository.findBySlug(slug);
        if(slug == null || blogPost == null){
            throw new BlogPostNotFoundException(HttpStatus.BAD_REQUEST, "Post does not exist!");
        }
        return new BlogPostDto(blogPost);
    }
}
