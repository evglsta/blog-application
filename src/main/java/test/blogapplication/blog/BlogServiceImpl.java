package test.blogapplication.blog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import test.blogapplication.blog.dto.BlogRequestDto;
import test.blogapplication.blog.dto.BlogResponseDto;

import javax.persistence.EntityNotFoundException;

@Service
public class BlogServiceImpl implements BlogService {

    private BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Page<BlogResponseDto> getBlogs(Pageable pageable) {
        Page<Blog> blogs = blogRepository.findAll(pageable);
        return blogs.map(this::convertDto);
    }

    @Override
    public BlogResponseDto createBlog(BlogRequestDto request) {
        Blog newBlog = new Blog();
        newBlog = this.convertEntity(newBlog, request);
        blogRepository.save(newBlog);

        return convertDto(newBlog);
    }

    @Override
    public BlogResponseDto getBlogById(Long id) {
        Blog blog = getBlog(id);
        return convertDto(blog);
    }

    @Override
    public BlogResponseDto updateBlog(BlogRequestDto request, Long id) {
        Blog blog = getBlog(id);
        blog = convertEntity(blog, request);
        blogRepository.save(blog);

        return convertDto(blog);
    }

    @Override
    public String deleteBlog(Long id) {
        Blog blog = getBlog(id);
        blogRepository.delete(blog);

        return "Blog deleted";
    }

    private Blog getBlog(Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Blog not found"));
        return blog;
    }

    private BlogResponseDto convertDto(Blog blog) {
        BlogResponseDto response = new BlogResponseDto();
        response.setId(blog.getId());
        response.setTitle(blog.getTitle());
        response.setBody(blog.getBody());
        response.setAuthor(blog.getAuthor());
        return response;
    }

    private Blog convertEntity(Blog blog, BlogRequestDto request) {
        blog.setTitle(request.getTitle());
        blog.setBody(request.getBody());
        blog.setAuthor(request.getAuthor());
        return blog;
    }
}
