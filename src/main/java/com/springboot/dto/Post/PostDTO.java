package com.springboot.dto.Post;

import com.springboot.model.Post;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO
{
    private String id;
    private String title;
    private String description;
    private String content;

    public PostDTO(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.content = post.getContent();
    }
    public PostDTO(PostDTO post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.content = post.getContent();
    }
}
