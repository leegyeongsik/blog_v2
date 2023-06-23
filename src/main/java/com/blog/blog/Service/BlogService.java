package com.blog.blog.Service;
import com.blog.blog.Repository.BlogRepository;
import com.blog.blog.dto.BlogRequestDto;
import com.blog.blog.dto.BlogResponseDto;
import com.blog.blog.entity.Blog;
import com.blog.blog.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class BlogService {
    private final BlogRepository blogRepository;
    public BlogService(BlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }

    public BlogResponseDto createBlog( User user ,BlogRequestDto blogRequestDto) {
        Blog blog = new Blog(blogRequestDto , user);
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
    public Blog updatePost( BlogRequestDto blogRequestDto , Long id , User user) {
        Blog blog = findName(id);
        confirmTokenId(blog  , user);
        blog.update(blogRequestDto);
        return blog; // 여기서 커밋을 끝나야지만 테이블값이 변경되는거라서 수정됬다고 여겨지지않아 바뀐시간이 그대로
                    // -> commit 이 되어야만 값이 바뀐다고 생각해서 일단 커밋보내놓고 주소를 controller 에 보냄
    }


    public String deletePost(Long id , User user) {
        Blog blog = findName(id);
        confirmTokenId(blog  , user);
        blogRepository.delete(blog);
        return "삭제 성공";
    }

    private Blog findName(Long id) {
        return blogRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("게시글이 존재하지 않습니다")
        );
    }
    private void confirmTokenId(Blog blog , User user){
        if (blog.getUsername().equals(user.getUsername())){
            System.out.println("작성한 글이 맞습니다");
        } else {
            throw new IllegalArgumentException("해당 유저가 작성한글이 아닙니다"); // 문제점 : context의 user의 값과 blog의 user의 값이 다를때 exception을 보내는데
        }                                                                    // 보낸후에 계속 select로 해당 user를 찾음
    }
}
