package com.springboot.controller;

import com.springboot.constants.BlogConstant;
import com.springboot.dto.Post.PostDTO;
import com.springboot.dto.Post.PostResponseDTO;
import com.springboot.dto.Post.PostWithCommentsDTO;
import com.springboot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<PostResponseDTO> getAllPost(
            @RequestParam(value = "pageNo",defaultValue = BlogConstant.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = BlogConstant.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = BlogConstant.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = BlogConstant.DEFAULT_SORT_DIRECTION,required = false) String sortDir
    ){
        PostResponseDTO posts = postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("id") String id){
        PostDTO post = postService.getPostById(id);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }
    @GetMapping("/{id}/comments")
    public ResponseEntity<PostWithCommentsDTO> getPostWithComments(@PathVariable("id") String id){
        PostWithCommentsDTO responsePost = postService.getPostWithComments(id);
        return new ResponseEntity<>(responsePost,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDto){
        PostDTO post = postService.createPost(postDto);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }
    @PostMapping("all")
    public ResponseEntity<String> uploadPosts(@RequestBody List<PostDTO> postDTOList){
        postDTOList.forEach(postDto -> postService.createPost(postDto));
        return new ResponseEntity<>("done",HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePostById(@RequestBody PostDTO postDto, @PathVariable("id") String id){
        PostDTO postResponse = postService.updatePostById(postDto,id);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") String id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post Deleted",HttpStatus.OK);
    }

}
