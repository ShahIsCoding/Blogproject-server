package com.springboot.dto;

import com.springboot.model.Post;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto
{
    private String id;
    private String title;
    private String description;
    private String content;

    public PostDto(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.content = post.getContent();
    }
}
