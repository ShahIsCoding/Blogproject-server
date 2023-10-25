package com.springboot.model;

import com.springboot.constants.constant;
import com.springboot.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "comment")
public class Comment {
    private String id;
    private String name;
    private String email;
    private String body;
    private String postId;

    public Comment(CommentDto comment){
        this.id = constant.BLOG+":"+constant.COMMENT+"="+ UUID.randomUUID().toString();
        this.name = comment.getName();
        this.email = comment.getEmail();
        this.body = comment.getBody();
    }

    public void update(CommentDto commentDto) {
        this.name = commentDto.getName();
        this.email = commentDto.getEmail();
        this.body = commentDto.getBody();
    }
}
