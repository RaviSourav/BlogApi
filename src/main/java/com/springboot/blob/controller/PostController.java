package com.springboot.blob.controller;

import com.springboot.blob.entity.Post;
import com.springboot.blob.payload.PostDto;
import com.springboot.blob.payload.PostDtoV2;
import com.springboot.blob.payload.PostResponse;
import com.springboot.blob.service.PostService;
import com.springboot.blob.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping()
@Tag(
        name = "CRUD REST APIs for Post Resource"
)
public class PostController {

    @Autowired
    private PostService postService;

    @Operation(
            summary = "Create Post REST API",
            description = "Create Post REST API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/v1/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto)
    {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }



    @Operation(
            summary = "Get All Posts REST API",
            description = "Get All Posts REST API is used to fetch all the posts from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/api/v1/posts")
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



    @Operation(
            summary = "Get Post By Id REST API",
            description = "Get Post By Id REST API is used to get single post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
//    @GetMapping(value = "/api/posts/{id}", params = "version=1")  //versioning through Parameters
//    @GetMapping(value = "/api/v1/posts/{id}")   //Versioning through URI
//    @GetMapping(value = "/api/posts/{id}", headers = "X-API-VERSION=1")   //Versioning through Headers
    @GetMapping(value = "/api/posts/{id}", produces = "application/vnd.blog.v1+json")   //Versioning through Content Negotiation
    public PostDto getPostByIdV1(@PathVariable long id){
        return postService.getPostById(id);
    }

    @Operation(
            summary = "Get Post By Id REST API",
            description = "Get Post By Id REST API is used to get single post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
//    @GetMapping(value = "/api/posts/{id}", params = "version=2")   //Versioning through Parameters
//    @GetMapping("/api/v2/posts/{id}")    //Versioning through URI
//    @GetMapping(value = "/api/posts/{id}", headers = "abc=2")   //Versioning through Headers -> abc can be anything make sure to pass same from headers
    @GetMapping(value = "/api/posts/{id}", produces = "application/v2+json")   //Versioning through Content Negotiation -> aaplication mandatory for representation
//    json is also important for representation -> for above 2 Versioning method standard way is defined above
    public PostDtoV2 getPostByIdV2(@PathVariable long id){
        PostDto postDto = postService.getPostById(id);
        PostDtoV2 postDtoV2 = new PostDtoV2();
        postDtoV2.setId(postDto.getId());
        postDtoV2.setComments(postDto.getComments());
        postDtoV2.setContent(postDto.getContent());
        postDtoV2.setDescription(postDto.getDescription());
        postDtoV2.setTitle(postDto.getTitle());
        List<String> tags = new ArrayList<>();
        tags.add("Java");
        tags.add("Spring Boot");
        tags.add("AWS");
        postDtoV2.setTags(tags);
        return postDtoV2;
    }


    @Operation(
            summary = "update Post REST API",
            description = "Update Post REST API is used to update a particular post in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/api/v1/posts/{id}")
    public PostDto updatePostBy(@PathVariable long id, @Valid @RequestBody PostDto postDto)
    {
        return postService.updatePost(postDto,id);
    }

    @Operation(
            summary = "Delete Post REST API",
            description = "Delete Post REST API is used to delete a particular post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/v1/posts/{id}")
    public String deletePost(@PathVariable long id)
    {
        postService.deletePost(id);
        return "Post Entity Deleted Succesfully";
    }

    @GetMapping("/api/v1/posts/category/{id}")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable("id") Long categoryId)
    {
        List<PostDto> postDtos = postService.getPostByCategoryId(categoryId);
        return ResponseEntity.ok(postDtos);
    }

}
