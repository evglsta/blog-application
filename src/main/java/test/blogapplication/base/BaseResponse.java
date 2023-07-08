package test.blogapplication.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BaseResponse<T> {

    private HttpStatus statusCode;
    private String message;
    private String errorMessages;
    private T data;

    public BaseResponse() {

    }

    public BaseResponse(HttpStatus statusCode, String message, String errorMessages, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.errorMessages = errorMessages;
        this.data = data;
    }

    public BaseResponse(String errorMessages) {
        this.errorMessages = errorMessages;
        this.data = null;
    }

    public BaseResponse(HttpStatus statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public BaseResponse(HttpStatus statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }
}
