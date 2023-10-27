package com.springboot.controller;

import com.springboot.dto.CommentDTO;
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
    public ResponseEntity<List<CommentDTO>> getAllComments(@PathVariable("postId") String postId) {
        List<CommentDTO> commentDTOList = commentService.getCommentsByPostId(postId);
        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CommentDTO> getComment(@PathVariable("id") String commentId, @PathVariable("postId") String postId) {
        CommentDTO commentDto = commentService.getCommentById(commentId, postId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> uploadComment(@PathVariable("postId") String postId, @RequestBody CommentDTO commentDto) {
        commentService.createComment(commentDto, postId);
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable("id") String commentId, @PathVariable("postId") String postId, @RequestBody CommentDTO commentDto) {
        CommentDTO responseCommentDTO = commentService.updateComment(commentId, postId, commentDto);
        return new ResponseEntity<>(responseCommentDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") String commentId, @PathVariable("postId") String postId) {
        commentService.deleteComment(commentId, postId);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}