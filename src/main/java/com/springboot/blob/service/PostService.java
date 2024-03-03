package com.springboot.blob.service;

import com.springboot.blob.entity.Post;
import com.springboot.blob.payload.PostDto;
import com.springboot.blob.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    //just to check how it is returning the comment as well
//    List<PostDto> getAllPosts();

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePost(long id);
}
