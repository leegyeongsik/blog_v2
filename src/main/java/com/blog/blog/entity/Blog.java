package com.blog.blog.entity;

import com.blog.blog.dto.BlogRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Blog")
@NoArgsConstructor
public class Blog  extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title" , nullable = false)
    private String title;

    @Column(name = "username" , nullable = false)
    private String username;

    @Column(name = "content" , nullable = false)
    private String content;

    @Column(name = "password" , nullable = false)
    private String password;


    public Blog(BlogRequestDto blogRequestDto){
        this.title = blogRequestDto.getTitle();
        this.username = blogRequestDto.getUsername();
        this.content = blogRequestDto.getContent();
        this.password = blogRequestDto.getPassword();
    }
    public void update(BlogRequestDto blogRequestDto){
        this.title = blogRequestDto.getTitle();
        this.content = blogRequestDto.getContent();
        this.username = blogRequestDto.getUsername();
    }
}
