package com.blog.blog.Controller;

import com.blog.blog.Service.BlogService;
import com.blog.blog.dto.BlogRequestDto;
import com.blog.blog.dto.BlogResponseDto;
import com.blog.blog.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @PostMapping("/blogs") // 게시글 등록
    public BlogResponseDto createBlog(@AuthenticationPrincipal UserDetailsImpl userDetails ,@RequestBody BlogRequestDto requestDto){
        return blogService.createBlog(userDetails.getUser() , requestDto);
    }
    @GetMapping("/blogs") // 게시글 전체조회
    public List<BlogResponseDto> getBlogs(){
        return blogService.getBlogs();
    }
    @GetMapping("/blogs/{id}") // 특정 id 게시글 조회
    public BlogResponseDto selectedPost(@PathVariable Long id){
        return blogService.selectedPost(id);
    }
    @PutMapping("/blogs/{id}") // 특정 id 게시글 수정하는데 토큰 아이디랑 , username이랑 같다면 수정
    public BlogResponseDto updatePost(@PathVariable Long id , @RequestBody BlogRequestDto blogRequestDto ,@AuthenticationPrincipal UserDetailsImpl userDetails ){
        BlogResponseDto blogResponseDto = new BlogResponseDto(blogService.updatePost(blogRequestDto,id,userDetails.getUser())); // 커밋이 완료된상태라 blog 주소의 modifiedAt의 값이 변경되었음
        return blogResponseDto;                                                                                    // -> 다시 그 주소를 blogResponseDto 에 넣어서 출력을해줌
    }
    @DeleteMapping("/blogs/{id}") // 특정 id 게시글 수정하는데 토큰 아이디랑 , username이랑 같다면 삭제
    public String deletePost(@PathVariable Long id  ,@AuthenticationPrincipal UserDetailsImpl userDetails  ){
        return blogService.deletePost(id ,userDetails.getUser());
    }

}
