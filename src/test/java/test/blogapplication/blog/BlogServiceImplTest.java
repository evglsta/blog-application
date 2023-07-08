package test.blogapplication.blog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import test.blogapplication.blog.dto.BlogRequestDto;
import test.blogapplication.blog.dto.BlogResponseDto;

import javax.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BlogServiceImplTest {

    @Mock
    public BlogRepository blogRepository;

    public BlogServiceImpl blogService;

    private Blog blog;
    private Long id = 1L;
    private String title = "Blog Title";
    private String body = "This is a body blog.";
    private String author = "Author";

    @BeforeEach
    void setUp() {
        blogService = new BlogServiceImpl(blogRepository);

        blog = new Blog();
        blog.setId(id);
        blog.setTitle(title);
        blog.setBody(body);
        blog.setAuthor(author);
    }

    @Test
    public void getBlogsTest() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<Blog> blogPage = new PageImpl<>(List.of(blog), pageable, 0);
        when(blogRepository.findAll(any(Pageable.class))).thenReturn(blogPage);
        Page<BlogResponseDto> result = blogService.getBlogs(pageable);
        assertNotNull(result);

        verify(blogRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    public void blogNotFoundTest() {
        assertThrows(EntityNotFoundException.class, () -> {
            blogService.getBlogById(null);
        });
    }

    @Test
    public void blogFoundTest() {
        when(blogRepository.findById(any())).thenReturn(Optional.of(blog));

        BlogResponseDto response = blogService.getBlogById(id);
        assertEquals(id, response.getId());
        assertEquals(title, response.getTitle());
        assertEquals(body, response.getBody());
        assertEquals(author, response.getAuthor());
    }

    @Test
    public void createBlogTest() {
        BlogRequestDto request = new BlogRequestDto();
        request.setTitle(title);
        request.setBody(body);
        request.setAuthor(author);

        BlogResponseDto response = blogService.createBlog(request);
        response.setId(id);
        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals(title, response.getTitle());
        assertEquals(body, response.getBody());
        assertEquals(author, response.getAuthor());
    }

    @Test
    public void updateBlogTest() {
        BlogRequestDto request = new BlogRequestDto();
        request.setTitle(title);
        request.setBody("Update body blog");
        request.setAuthor(author);

        when(blogRepository.findById(any())).thenReturn(Optional.of(blog));
        BlogResponseDto response = blogService.updateBlog(request, id);
        assertEquals(id, response.getId());
        assertEquals(title, response.getTitle());
        assertEquals("Update body blog", response.getBody());
        assertEquals(author, response.getAuthor());
    }

    @Test
    public void deleteBlogTest() {
        when(blogRepository.findById(any())).thenReturn(Optional.of(blog));
        String response = blogService.deleteBlog(id);

        assertEquals("Blog deleted", response);
    }
}
