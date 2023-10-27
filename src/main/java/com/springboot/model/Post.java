package com.springboot.model;

import com.springboot.constants.BlogConstant;
import com.springboot.dto.Post.PostDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "posts")
public class Post {
    private String id;
    private String title;
    private String description;
    private String content;

    public Post(PostDTO post) {
        this.id = BlogConstant.BLOG+":"+ BlogConstant.POST+"="+ UUID.randomUUID().toString();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.content = post.getContent();
    }
    public Post(PostDTO post, int length) {
        this.id = BlogConstant.BLOG+":"+ BlogConstant.POST+":"+length+":"+ UUID.randomUUID().toString();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.content = post.getContent();
    }

    public void update(PostDTO postDto) {
        this.title = postDto.getTitle();
        this.description = postDto.getDescription();
        this.content = postDto.getContent();
    }
}
