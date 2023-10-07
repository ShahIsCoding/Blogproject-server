package com.springboot.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "post")
public class Post {
    private String id;
    private String title;

}
