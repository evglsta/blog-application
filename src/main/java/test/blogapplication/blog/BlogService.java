package test.blogapplication.blog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import test.blogapplication.blog.dto.BlogRequestDto;
import test.blogapplication.blog.dto.BlogResponseDto;

@Service
public interface BlogService {

    public Page<BlogResponseDto> getBlogs(Pageable pageable);
    public BlogResponseDto createBlog(BlogRequestDto request);
    BlogResponseDto getBlogById(Long id);
    BlogResponseDto updateBlog(BlogRequestDto request, Long id);
    String deleteBlog(Long id);
}
