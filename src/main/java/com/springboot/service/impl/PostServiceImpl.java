package com.springboot.service.impl;

import com.springboot.dto.PostDto;
import com.springboot.exception.ResourceNotFoundException;
import com.springboot.model.Post;
import com.springboot.repository.PostRepository;
import com.springboot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Override
    public PostDto createPost(PostDto postDto) {
        // convert dto to model
        Post post = mapToModel(postDto);
        postRepository.save(post);
        return mapToDTO(post);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(post -> mapToDTO(post))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(String id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        return mapToDTO(post);
    }

    @Override
    public PostDto updatePostById(PostDto postDto, String id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatedPost = postRepository.save(post);
        return mapToDTO(updatedPost);
    }

    @Override
    public void deletePostById(String id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        postRepository.delete(post);
    }

    // convert model to DTO
    private PostDto mapToDTO(Post post){
        return new PostDto(post);
    }

    private Post mapToModel(PostDto post){
        return new Post(post);
    }

}
