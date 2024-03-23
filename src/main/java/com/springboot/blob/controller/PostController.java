package com.springboot.blob.controller;

import com.springboot.blob.entity.Post;
import com.springboot.blob.payload.PostDto;
import com.springboot.blob.payload.PostResponse;
import com.springboot.blob.service.PostService;
import com.springboot.blob.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto)
    {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public PostResponse getAllPost(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                   @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                   @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                   @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    //just to check how it is returning the comment as well
//    @GetMapping
//    public List<PostDto> getAllPost(){
//        return postService.getAllPosts();
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<PostDto> getPostById(@PathVariable long id){
//        return ResponseEntity.ok(postService.getPostById(id));
//    }

    @GetMapping("/{id}")
    public PostDto getPostById(@PathVariable long id){
        return postService.getPostById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public PostDto updatePostBy(@PathVariable long id, @Valid @RequestBody PostDto postDto)
    {
        return postService.updatePost(postDto,id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable long id)
    {
        postService.deletePost(id);
        return "Post Entity Deleted Succesfully";
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable("id") Long categoryId)
    {
        List<PostDto> postDtos = postService.getPostByCategoryId(categoryId);
        return ResponseEntity.ok(postDtos);
    }

}
