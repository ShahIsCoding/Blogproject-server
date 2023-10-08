package com.springboot.service;

import com.springboot.dto.PostDto;
import com.springboot.model.Post;
import com.springboot.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostById(String id);

    PostDto updatePostById(PostDto postDto, String id);

    void deletePostById(String id);
}
