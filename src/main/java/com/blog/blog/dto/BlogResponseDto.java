package com.blog.blog.dto;

import com.blog.blog.entity.Blog;
import lombok.Getter;
@Getter
public class BlogResponseDto {
    private Long id;
    private String title;
    private String RegistrantName;
    private String content;
    private String password;


    BlogResponseDto(Blog blog){
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.RegistrantName = blog.getRegistrantName();
        this.content = blog.getContent();
        this.password = blog.getPassword();
    }
}
