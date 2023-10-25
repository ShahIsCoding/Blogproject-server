package com.springboot.repository;

import com.springboot.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.awt.print.Pageable;

public interface PostRepository extends MongoRepository<Post,String> {
}
