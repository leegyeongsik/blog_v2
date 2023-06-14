package com.blog.blog.entity;

import com.blog.blog.dto.BlogRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "blog")
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 아이디값을 지정해주지않아도 자동으로 1씩 오름
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "RegistrantName" , nullable =false)
    private String RegistrantName;

    @Column(name = "content")
    private String content;

    @Column(name = "password" , nullable =false)
    private String password;


    Blog(BlogRequestDto blogRequestDto){
        this.title = blogRequestDto.getTitle();
        this.RegistrantName = blogRequestDto.getRegistrantName();
        this.content = blogRequestDto.getContent();
        this.password = blogRequestDto.getPassword();
    }
    public void update(BlogRequestDto blogRequestDto){
        this.title = blogRequestDto.getTitle();
        this.content = blogRequestDto.getContent();
    }
}
