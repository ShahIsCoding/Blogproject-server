package com.springboot.controller;

import com.springboot.dto.PostDto;
import com.springboot.model.Post;
import com.springboot.service.PostService;
import com.springboot.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        PostDto post = postService.createPost(postDto);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<PostDto> > getAllPost(){
        List<PostDto> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") String id){
        PostDto post = postService.getPostById(id);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto, @PathVariable("id") String id){
        PostDto postResponse = postService.updatePostById(postDto,id);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") String id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post Deleted",HttpStatus.OK);
    }

}
