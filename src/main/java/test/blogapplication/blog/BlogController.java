package test.blogapplication.blog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.blogapplication.base.BaseResponse;
import test.blogapplication.blog.dto.BlogRequestDto;
import test.blogapplication.blog.dto.BlogResponseDto;
import test.blogapplication.util.PageRequestUtil;

@RestController
@RequestMapping("/blog")
public class BlogController {

    private BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public ResponseEntity<?> getBlogs(@RequestParam(required = false) Integer page,
                                      @RequestParam(required = false) Integer limit,
                                      @RequestParam(required = false) String sortBy,
                                      @RequestParam(required = false) String sortDir) {
        PageRequest pageRequest = PageRequestUtil.generatePageRequest(page, limit, sortBy, sortDir);
        BaseResponse<Page<BlogResponseDto>> response = new BaseResponse<>(HttpStatus.OK, blogService.getBlogs(pageRequest));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<?> createBlog(@RequestBody BlogRequestDto request) {
        BaseResponse<BlogResponseDto> response = new BaseResponse<>(HttpStatus.OK, blogService.createBlog(request));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getBlogById(@PathVariable Long id) {
        BaseResponse<BlogResponseDto> response = new BaseResponse<>(HttpStatus.OK, blogService.getBlogById(id));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateBlog(@RequestBody BlogRequestDto request, @PathVariable Long id) {
        BaseResponse<BlogResponseDto> response = new BaseResponse<>(HttpStatus.OK, blogService.updateBlog(request, id));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Long id) {
        BaseResponse<String> response = new BaseResponse<>(HttpStatus.OK, blogService.deleteBlog(id));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
