package com.dev.restapiblog.service;

import com.dev.restapiblog.payload.PostDto;
import com.dev.restapiblog.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse allPosts(int page, int size, String sortBy, String sortDir);

    PostDto getPost(Long id);

    PostDto updatePost(PostDto postDto, Long id);

    void deletePostById(Long id);
}
