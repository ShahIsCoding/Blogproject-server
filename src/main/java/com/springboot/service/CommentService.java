package com.springboot.service;

import com.springboot.dto.CommentDto;
import com.springboot.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CommentService {
    List<CommentDto> getCommentsByPostId(String postId);

    CommentDto getCommentById(String commentId, String postId);

    void createComment(CommentDto commentDto, String postId);

    CommentDto updateComment(String commentId, String postId, CommentDto commentDto);

    void deleteComment(String commentId, String postId);
}