package com.springboot.service.impl;

import com.springboot.dto.CommentDTO;
import com.springboot.dto.Post.PostDTO;
import com.springboot.dto.Post.PostResponseDTO;
import com.springboot.dto.Post.PostWithCommentsDTO;
import com.springboot.exception.ResourceNotFoundException;
import com.springboot.model.Comment;
import com.springboot.model.Post;
import com.springboot.repository.CommentRepository;
import com.springboot.repository.PostRepository;
import com.springboot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public PostDTO createPost(PostDTO postDto) {
        // convert dto to model
        int length = postRepository.findAll().size();
        Post post = mapToModel(postDto, length);
        postRepository.save(post);
        return mapToDTO(post);
    }

    @Override
    public PostResponseDTO getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
//        Pageable pageable =  PageRequest.of(pageNo,pageSize); for only pagination
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort); // for pagination and sorting
        Page<Post> posts = postRepository.findAll(pageable);

        List<Post> listOfPosts = posts.getContent();
        List<PostDTO> content = listOfPosts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
        PostResponseDTO postResponse = new PostResponseDTO(content, posts);
        return postResponse;
    }

    @Override
    public PostDTO getPostById(String id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDTO(post);
    }

    @Override
    public PostDTO updatePostById(PostDTO postDto, String id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.update(postDto);
        Post updatedPost = postRepository.save(post);
        return mapToDTO(updatedPost);
    }

    @Override
    public void deletePostById(String id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    @Override
    public PostWithCommentsDTO getPostWithComments(String id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        List<Comment> commentList = commentRepository.findAllByPostId(id);
        List<CommentDTO> commentDTOList = commentList.stream().map(comment -> mapToCommentDTO(comment)).collect(Collectors.toList());
        PostDTO postDTO = mapToDTO(post);
        PostWithCommentsDTO responsePost = new PostWithCommentsDTO(postDTO, commentDTOList);
        return responsePost;
    }

    // convert model to DTO
    private PostDTO mapToDTO(Post post) {
        return new PostDTO(post);
    }

    private CommentDTO mapToCommentDTO(Comment comment) {
        return new CommentDTO(comment);
    }

    private Post mapToModel(PostDTO post, int length) {
        return new Post(post, length);
    }
}
