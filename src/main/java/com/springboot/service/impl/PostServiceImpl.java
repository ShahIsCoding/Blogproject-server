package com.springboot.service.impl;

import com.springboot.dto.PostDto;
import com.springboot.dto.PostResponseDto;
import com.springboot.exception.ResourceNotFoundException;
import com.springboot.model.Post;
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
    @Override
    public PostDto createPost(PostDto postDto) {
        // convert dto to model
        int length = postRepository.findAll().size();
        Post post = mapToModel(postDto,length);
        postRepository.save(post);
        return mapToDTO(post);
    }

    @Override
    public PostResponseDto getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir) {
//        Pageable pageable =  PageRequest.of(pageNo,pageSize); for only pagination
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize, sort); // for pagination and sorting
        Page<Post> posts = postRepository.findAll(pageable);

        List<Post> listOfPosts = posts.getContent();
        List<PostDto> content = listOfPosts.stream()
                .map(post -> mapToDTO(post))
                .collect(Collectors.toList());
        PostResponseDto postResponse = new PostResponseDto(content,posts);
        return postResponse;
    }

    @Override
    public PostDto getPostById(String id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        return mapToDTO(post);
    }

    @Override
    public PostDto updatePostById(PostDto postDto, String id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        post.update(postDto);
        Post updatedPost = postRepository.save(post);
        return mapToDTO(updatedPost);
    }

    @Override
    public void deletePostById(String id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        postRepository.delete(post);
    }

    // convert model to DTO
    private PostDto mapToDTO(Post post){
        return new PostDto(post);
    }

    private Post mapToModel(PostDto post){
        return new Post(post);
    }
    private Post mapToModel(PostDto post,int length){
        return new Post(post,length);
    }
}
