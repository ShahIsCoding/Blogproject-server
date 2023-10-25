package com.springboot.controller;

import com.springboot.constants.constant;
import com.springboot.dto.PostDto;
import com.springboot.dto.PostResponseDto;
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
    @PostMapping("all")
    public ResponseEntity<String> uploadPosts(@RequestBody List<PostDto> postDtoList){
        postDtoList.forEach(postDto -> postService.createPost(postDto));
        return new ResponseEntity<>("done",HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<PostResponseDto > getAllPost(
            @RequestParam(value = "pageNo",defaultValue = constant.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = constant.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = constant.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = constant.DEFAULT_SORT_DIRECTION,required = false) String sortDir
        ){
        PostResponseDto posts = postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
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
