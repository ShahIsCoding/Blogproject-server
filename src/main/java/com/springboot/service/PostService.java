package com.springboot.service;

import com.springboot.dto.Post.PostDTO;
import com.springboot.dto.Post.PostResponseDTO;
import com.springboot.dto.Post.PostWithCommentsDTO;

public interface PostService {
    PostDTO createPost(PostDTO postDto);

    PostResponseDTO getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDTO getPostById(String id);

    PostDTO updatePostById(PostDTO postDto, String id);

    void deletePostById(String id);

    PostWithCommentsDTO getPostWithComments(String id);
}
