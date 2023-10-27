package com.springboot.service;

import com.springboot.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    List<CommentDTO> getCommentsByPostId(String postId);

    CommentDTO getCommentById(String commentId, String postId);

    void createComment(CommentDTO commentDto, String postId);

    CommentDTO updateComment(String commentId, String postId, CommentDTO commentDto);

    void deleteComment(String commentId, String postId);
}