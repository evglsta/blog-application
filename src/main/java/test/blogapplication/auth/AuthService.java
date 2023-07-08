package test.blogapplication.auth;

import org.springframework.stereotype.Service;
import test.blogapplication.auth.dto.AuthRequestDto;
import test.blogapplication.auth.dto.AuthResponseDto;

@Service
public interface AuthService {

    public AuthResponseDto login(AuthRequestDto request);
}
