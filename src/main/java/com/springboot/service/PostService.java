package com.springboot.service;

import com.springboot.dto.PostDto;
import com.springboot.dto.PostResponseDto;
import com.springboot.model.Post;
import com.springboot.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponseDto getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir);

    PostDto getPostById(String id);

    PostDto updatePostById(PostDto postDto, String id);

    void deletePostById(String id);
}
