package com.dev.restapiblog.controller;

import com.dev.restapiblog.payload.PostDto;
import com.dev.restapiblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping("/")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<PostDto>> getPosts(){
        return new ResponseEntity(postService.allPosts(), HttpStatus.OK);
    }

    @GetMapping("/{id}/")
    public ResponseEntity<PostDto> getPost(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(postService.getPost(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(postService.updatePost(postDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id")Long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post Deleted", HttpStatus.NO_CONTENT);
    }

}
