package com.kopchak.worldoftoys.controller;

import com.kopchak.worldoftoys.dto.BlogPostDto;
import com.kopchak.worldoftoys.exception.BlogPostNotFoundException;
import com.kopchak.worldoftoys.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(value = {"http://localhost:4200", "http://localhost:8080"})
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
@Tag(name = "blog-controller", description = "Controller for return all posts in the blog page and return a specific " +
        "post from blog using its slug")
public class BlogController {
    private final BlogService blogService;

    @Operation(summary = "Return all posts in the blog page")
    @ApiResponse(
            responseCode = "200",
            description = "Posts have been successfully returned",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = BlogPostDto.class))
                    )
            })
    @GetMapping
    public ResponseEntity<Set<BlogPostDto>> getAllPosts() {
        return new ResponseEntity<>(blogService.getAllPosts(), HttpStatus.OK);
    }

    @Operation(summary = "Return a specific post from blog using its slug")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Post has been successfully returned",
                    content = @Content(schema = @Schema(implementation = BlogPostDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Post does not exist!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BlogPostNotFoundException.class)))
    })
    @GetMapping("/{postSlug}")
    public ResponseEntity<BlogPostDto> getPostBySlug(@PathVariable String postSlug) {
        return new ResponseEntity<>(blogService.getPostBySlug(postSlug), HttpStatus.OK);
    }
}
