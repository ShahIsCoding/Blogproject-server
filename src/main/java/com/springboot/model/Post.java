package com.springboot.model;

import com.springboot.constants.constant;
import com.springboot.dto.PostDto;
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

    public Post(PostDto post) {
        this.id = constant.BLOG+":"+constant.POST+"="+ UUID.randomUUID().toString();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.content = post.getContent();
    }


}
