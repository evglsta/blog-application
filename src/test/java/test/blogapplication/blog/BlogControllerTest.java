package test.blogapplication.blog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import test.blogapplication.blog.dto.BlogRequestDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BlogControllerTest {

    @Mock
    private BlogService blogService;

    private BlogController blogController;

    private Long id = 1L;
    private String title = "Blog Title";
    private String body = "This is a body blog.";
    private String author = "Author";

    @BeforeEach
    void setUp() {
        blogController = new BlogController(blogService);
    }

    @Test
    public void getBlogsTest() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(new Sort.Order(Sort.Direction.ASC, "id")));
        ResponseEntity<?> responseEntity = blogController.getBlogs(0, 10, null, null);
        verify(blogService).getBlogs(pageable);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void blogFoundTest() {
        ResponseEntity<?> responseEntity = blogController.getBlogById((long) 1);
        verify(blogService).getBlogById(any());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void createBlogTest() {
        BlogRequestDto request = new BlogRequestDto();
        request.setTitle(title);
        request.setBody(body);
        request.setAuthor(author);

        ResponseEntity<?> responseEntity = blogController.createBlog(request);
        verify(blogService).createBlog(any());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void updateBlogTest() {
        BlogRequestDto request = new BlogRequestDto();
        request.setTitle(title);
        request.setBody("Update body blog.");
        request.setAuthor(author);

        ResponseEntity<?> responseEntity = blogController.updateBlog(request, id);
        verify(blogService).updateBlog(any(), any());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void deleteBlogTest() {
        ResponseEntity<?> responseEntity = blogController.deleteBlog(id);
        verify(blogService).deleteBlog(any());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
