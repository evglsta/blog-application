package test.blogapplication.auth;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.blogapplication.auth.dto.AuthRequestDto;
import test.blogapplication.auth.dto.AuthResponseDto;
import test.blogapplication.base.BaseResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody AuthRequestDto request) {
        BaseResponse<AuthResponseDto> response = new BaseResponse<>(HttpStatus.OK, authService.login(request));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
