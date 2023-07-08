package test.blogapplication.blog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogResponseDto {

    private Long id;
    private String title;
    private String body;
    private String author;
}
