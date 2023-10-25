package com.springboot.dto;

import com.springboot.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {
    private List<PostDto> content;
    private  int pageNo;
    private  int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;

    public PostResponseDto(List<PostDto> content, Page<Post> posts ) {
        this.content = content;
        this.pageNo = posts.getNumber();
        this.pageSize = posts.getSize();
        this.totalElements = posts.getTotalElements();
        this.totalPages = posts.getTotalPages();
        this.lastPage = posts.isLast();
    }
}
