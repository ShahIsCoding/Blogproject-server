package com.springboot.controller;

import com.springboot.dto.CommentDto;
import com.springboot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/{postId}/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComments(@PathVariable("postId") String postId) {
        List<CommentDto> commentDtoList = commentService.getCommentsByPostId(postId);
        return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable("id") String commentId, @PathVariable("postId") String postId) {
        CommentDto commentDto = commentService.getCommentById(commentId, postId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> uploadComment(@PathVariable("postId") String postId,@RequestBody CommentDto commentDto) {
        commentService.createComment(commentDto, postId);
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("id") String commentId, @PathVariable("postId") String postId, @RequestBody CommentDto commentDto) {
        CommentDto responseCommentDto = commentService.updateComment(commentId, postId, commentDto);
        return new ResponseEntity<>(responseCommentDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") String commentId, @PathVariable("postId") String postId) {
        commentService.deleteComment(commentId, postId);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}