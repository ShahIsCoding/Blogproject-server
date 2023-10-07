package com.springboot.service;

import com.springboot.model.Post;
import com.springboot.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    public List<Post> getAllPost(){
        return postRepository.findAll();
    }
    public Post uploadPost(Post post){
        return postRepository.save(post);
    }
}
