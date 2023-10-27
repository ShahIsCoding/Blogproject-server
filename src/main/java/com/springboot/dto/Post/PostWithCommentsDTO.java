package com.springboot.dto.Post;

import com.springboot.dto.CommentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostWithCommentsDTO extends PostDTO{
    private List<CommentDTO> comments;

    public PostWithCommentsDTO(PostDTO postDTO, List<CommentDTO> commentDTOList) {
        super(postDTO);
        this.comments= commentDTOList;
    }
}
