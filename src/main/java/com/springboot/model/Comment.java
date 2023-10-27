package com.springboot.model;

import com.springboot.constants.BlogConstant;
import com.springboot.dto.CommentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "comment")
public class Comment {
    private String id;
    private Date timestamp;
    private String name;
    private String email;
    private String body;
    private String postId;

    public Comment(CommentDTO comment){
        this.id = BlogConstant.BLOG+":"+ BlogConstant.COMMENT+"="+ UUID.randomUUID().toString();
        this.timestamp = new Date();
        this.name = comment.getName();
        this.email = comment.getEmail();
        this.body = comment.getBody();
    }

    public void update(CommentDTO commentDto) {
        this.name = commentDto.getName();
        this.email = commentDto.getEmail();
        this.body = commentDto.getBody();
        this.timestamp = commentDto.getTimestamp();
    }
}
