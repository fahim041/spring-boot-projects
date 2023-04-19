package com.dev.restapiblog.service;

import com.dev.restapiblog.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> allPosts();

    PostDto getPost(Long id);

    PostDto updatePost(PostDto postDto, Long id);

    void deletePostById(Long id);
}
