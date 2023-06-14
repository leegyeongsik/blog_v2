package com.blog.blog.Controller;
import com.blog.blog.Service.BlogService;
import com.blog.blog.dto.BlogRequestDto;
import com.blog.blog.dto.BlogResponseDto;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogController {
    private final BlogService blogService;
    public BlogController(BlogService blogService) {
            this.blogService = blogService;
    }

    @PostMapping("/blogs")
    public BlogResponseDto createBlog(@RequestBody BlogRequestDto blogRequestDto){
        return blogService.createBlog(blogRequestDto);
    }
    @GetMapping("/blogs")
    public List<BlogResponseDto> getBlogs(){
        return blogService.getBlogs();
    }
    @GetMapping("/blogs/{id}")
    public BlogResponseDto selectedPost(@PathVariable Long id){
        return blogService.selectedPost(id);
    }
    @PutMapping("/blogs/{id}/{password}")
    public BlogResponseDto updatePost(@PathVariable Long id , @PathVariable String password , @RequestBody BlogRequestDto blogRequestDto){
        BlogResponseDto blogResponseDto = new BlogResponseDto(blogService.updatePost(id,password,blogRequestDto)); // 커밋이 완료된상태라 blog 주소의 modifiedAt의 값이 변경되었음
        return blogResponseDto;                                                                                    // -> 다시 그 주소를 blogResponseDto 에 넣어서 출력을해줌
    }
    @DeleteMapping("/blogs/{id}/{password}")
    public String deletePost(@PathVariable Long id , @PathVariable String password ){
        return blogService.deletePost(id,password);
    }
}
