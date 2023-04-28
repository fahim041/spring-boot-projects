package com.dev.restapiblog.controller;

import com.dev.restapiblog.payload.PostDto;
import com.dev.restapiblog.payload.PostResponse;
import com.dev.restapiblog.service.PostService;
import com.dev.restapiblog.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<PostResponse> getPosts(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int size,
            @RequestParam(value = "sort", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir
    ){
        return new ResponseEntity(postService.allPosts(page, size, sortBy, sortDir), HttpStatus.OK);
    }

    @GetMapping("/{id}/")
    public ResponseEntity<PostDto> getPost(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(postService.getPost(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(postService.updatePost(postDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id")Long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post Deleted", HttpStatus.NO_CONTENT);
    }

}
