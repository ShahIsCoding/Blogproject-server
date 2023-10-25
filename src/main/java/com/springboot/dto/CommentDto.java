package com.springboot.dto;

import com.springboot.constants.constant;
import com.springboot.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private String id;
    private String name;
    private String email;
    private String body;

    public CommentDto(Comment comment){
        this.id = comment.getId();
        this.name = comment.getName();
        this.email = comment.getEmail();
        this.body = comment.getBody();
    }
}