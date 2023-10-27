package com.springboot.dto;

import com.springboot.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private String id;
    private Date timestamp;
    private String name;
    private String email;
    private String body;

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.name = comment.getName();
        this.email = comment.getEmail();
        this.body = comment.getBody();
        this.timestamp = comment.getTimestamp();
    }
}