package test.blogapplication.blog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogRequestDto {

    private String title;
    private String body;
    private String author;
}
