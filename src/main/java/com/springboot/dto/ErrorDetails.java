package com.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

@Getter
@AllArgsConstructor
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(String message, Date timestamp, String description) {
        this.message = message;
        this.details = description;
        this.timestamp = timestamp;
    }
}
