package test.blogapplication.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import test.blogapplication.auth.dto.AuthRequestDto;
import test.blogapplication.auth.dto.AuthResponseDto;
import test.blogapplication.security.CustomUserDetailsService;
import test.blogapplication.security.JwtTokenUtil;

@Service
public class AuthServiceImpl implements AuthService {

    private CustomUserDetailsService userDetailsService;

    private AuthenticationManager authenticationManager;

    private JwtTokenUtil tokenUtil;

    public AuthServiceImpl(CustomUserDetailsService userDetailsService, AuthenticationManager authenticationManager, JwtTokenUtil tokenUtil) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.tokenUtil = tokenUtil;
    }

    public AuthResponseDto login(AuthRequestDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String token = tokenUtil.generateToken(userDetails);

        AuthResponseDto response = new AuthResponseDto();
        response.setToken(token);
        return response;
    }
}
