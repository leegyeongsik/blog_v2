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

    public Blog(BlogRequestDto blogRequestDto , User user){
        this.title = blogRequestDto.getTitle();
        this.username = user.getUsername();
        this.content = blogRequestDto.getContent();
        this.password = user.getPassword();
    }
    public void update(BlogRequestDto blogRequestDto ){
//        setTitle(blogRequestDto.getTitle()); 굳이 세터를 사용한다면?
        this.title = blogRequestDto.getTitle();
        this.content = blogRequestDto.getContent();
    }
}
