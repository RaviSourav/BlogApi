package com.springboot.blob.service.impl;

import com.springboot.blob.entity.Post;
import com.springboot.blob.exception.ArgumentNotValidException;
import com.springboot.blob.exception.ResourceNotFoundException;
import com.springboot.blob.payload.PostDto;
import com.springboot.blob.payload.PostResponse;
import com.springboot.blob.repository.PostRepository;
import com.springboot.blob.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired // either we can use contructor injection here and we can use @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PostDto createPost(PostDto postDto) {
        //if we don't want to use @Valid and wanted to make custom exception according to length of "title" field we can use this
//        if (postDto.getTitle().length()<2)
//            throw new ArgumentNotValidException("Post Title should have at least 2 characters");
        Post post = mapToEntity(postDto);

        Post newPost = postRepository.save(post);

        return mapToDTO(newPost);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
//        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy)); //default is ascending order of sortBy
//        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending()); //with descending order of sortBy
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> listOfPosts = posts.getContent();
        List<PostDto> content = listOfPosts.stream().map(this::mapToDTO).collect(Collectors.toList());
//        return posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());


        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        return postResponse;

    }

    //just to check how it is returning the comment as well
//    @Override
//    public List<PostDto> getAllPosts() {
//        List<Post> post = postRepository.findAll();
//        return post.stream().map(this::mapToDTO).collect(Collectors.toList());
//    }

    @Override
    public PostDto getPostById(Long id) {
//        PostDto postDto = mapToDTO(postRepository.findById(id));
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        return mapToDTO(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return mapToDTO(postRepository.save(post));
    }

    @Override
    public void deletePost(long id) {
//        postRepository.deleteById(id);
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        postRepository.delete(post);
    }

    private PostDto mapToDTO(Post post)
    {
//        PostDto postDto = new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());

//        PostDto postDto = modelMapper.map(post, PostDto.class);
        return modelMapper.map(post, PostDto.class);
    }

    private Post mapToEntity(PostDto postDto)
    {
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());

//        Post post = modelMapper.map(postDto,Post.class);
        return modelMapper.map(postDto,Post.class);
    }

}
