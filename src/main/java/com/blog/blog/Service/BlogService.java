package com.blog.blog.Service;
import com.blog.blog.Repository.BlogRepository;
import com.blog.blog.dto.BlogRequestDto;
import com.blog.blog.dto.BlogResponseDto;
import com.blog.blog.entity.Blog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class BlogService {
    private final BlogRepository blogRepository;
    public BlogService(BlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }

    public BlogResponseDto createBlog(BlogRequestDto blogRequestDto) {
        Blog blog = new Blog(blogRequestDto);
        Blog saveblog = blogRepository.save(blog);
        return new BlogResponseDto(saveblog);
    }

    public List<BlogResponseDto> getBlogs() {
        return blogRepository.findAllByOrderByModifiedAtDesc().stream().map(BlogResponseDto::new).toList();
    }

    public BlogResponseDto selectedPost(Long id) {
        Blog blog = findName(id);
        return new BlogResponseDto(blog);
    }
    @Transactional
    public Blog updatePost(Long id, BlogRequestDto blogRequestDto) {
        Blog blog = findName(id);
        confirmPwd(blog , blogRequestDto);
        blog.update(blogRequestDto);
        return blog; // 여기서 커밋을 끝나야지만 테이블값이 변경되는거라서 수정됬다고 여겨지지않아 바뀐시간이 그대로
                    // -> commit 이 되어야만 값이 바뀐다고 생각해서 일단 커밋보내놓고 주소를 controller 에 보냄
    }


    public String deletePost(Long id, BlogRequestDto blogRequestDto) {
        Blog blog = findName(id);
        confirmPwd(blog , blogRequestDto);
        blogRepository.delete(blog);
        return "삭제 성공";
    }

    private Blog findName(Long id) {
        return blogRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("게시글이 존재하지 않습니다")
        );
    }
    private void confirmPwd(Blog blog , BlogRequestDto blogRequestDto){
        if (blog.getPassword().equals(blogRequestDto.getPassword())){
            System.out.println("비밀번호가 일치합니다 ");
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지않습니다");
        }
    }
}
