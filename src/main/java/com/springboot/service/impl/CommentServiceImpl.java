package com.springboot.service.impl;

import com.springboot.dto.CommentDto;
import com.springboot.exception.BlogAPIException;
import com.springboot.exception.ResourceNotFoundException;
import com.springboot.model.Comment;
import com.springboot.model.Post;
import com.springboot.repository.CommentRepository;
import com.springboot.repository.PostRepository;
import com.springboot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepo;
    @Autowired
    private PostRepository postRepository;
    @Override
    public List<CommentDto> getCommentsByPostId(String postId) {
        if(!postRepository.existsById(postId)) new ResourceNotFoundException("Post","Id",postId);
        List<Comment> commentList = commentRepo.findAllByPostId(postId);
        List<CommentDto> commentDtoList = commentList.stream()
                .map(comment -> mapToDTO(comment))
                .collect(Collectors.toList());
        return commentDtoList;
    }

    @Override
    public CommentDto getCommentById(String commentId, String postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","Id",postId) );
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment","Id",commentId) );
        if(comment.getPostId().equals(postId)) new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        return mapToDTO(comment);
    }

    @Override
    public void createComment(CommentDto commentDto, String postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","Id",postId) );
        Comment comment = mapToModel(commentDto);
        comment.setPostId(post.getId());
        commentRepo.save(comment);
    }

    @Override
    public CommentDto updateComment(String commentId, String postId, CommentDto commentDto) {
        if(!postRepository.existsById(postId)) new ResourceNotFoundException("Post","Id",postId);
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment","Id",commentId) );
        comment.update(commentDto);
        Comment updatedComment = commentRepo.save(comment);
        return mapToDTO(updatedComment);
    }

    @Override
    public void deleteComment(String commentId, String postId) {
        if(!postRepository.existsById(postId)) new ResourceNotFoundException("Post","Id",postId);
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment","Id",commentId) );
        if(comment.getPostId().equals(postId)) new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        commentRepo.deleteById(commentId);
    }

    private CommentDto mapToDTO(Comment comment){
        return new CommentDto(comment);
    }
    private Comment mapToModel(CommentDto commentDto){
        return new Comment(commentDto);
    }
}
