package com.springboot.controller;

import com.springboot.model.Post;
import com.springboot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    private PostService postService;
    @GetMapping("/posts")
    public List<Post> getPost(){
        return postService.getAllPost();
    }

    @PostMapping("/posts")
    public String uploadPost(@RequestBody Post Post){
        return postService.uploadPost(Post).toString();
    }
}
