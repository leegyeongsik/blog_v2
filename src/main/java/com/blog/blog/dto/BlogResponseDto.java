package com.blog.blog.dto;

import com.blog.blog.entity.Blog;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BlogResponseDto {
    private Long id;
    private String title;
    private String username;
    private String content;
    private String password;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public BlogResponseDto (Blog blog){
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.username = blog.getUsername();
        this.content = blog.getContent();
        this.password = blog.getPassword();
        this.createAt = blog.getCreatedAt();
        this.modifiedAt = blog.getModifiedAt();
    }
}
