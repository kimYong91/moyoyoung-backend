package org.community.moyoyoung.security;

import org.community.moyoyoung.entity.MyUser;
import org.community.moyoyoung.samgak0.services.MyUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//Author : MinU Bak
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;
    private final MyUserService myUserService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/token")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            MyUser myUser = myUserService.findUserByUsername(authRequest.username).orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
            if (!passwordEncoder.matches(authRequest.password, myUser.getPassword())) throw new UsernameNotFoundException("Invalid username or password");
            return handleTokenGeneration(authRequest.getUsername());
        } catch (UsernameNotFoundException e) {
            return createAuthResponse(false, e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return createAuthResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<?> refreshAccessToken(@RequestBody TokenRefreshRequest tokenRefreshRequest) {
        if (!jwtTokenProvider.validateToken(tokenRefreshRequest.getRefreshToken())) {
            return createAuthResponse(false, "Invalid or expired refresh token", HttpStatus.UNAUTHORIZED);
        }

        return handleTokenGeneration(tokenRefreshRequest.getUsername());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AuthResponse> handleAllExceptions(Exception ex) {
        log.error("Unhandled exception occurred: {}", ex.getMessage());
        return createAuthResponse(false, "An error occurred during authentication", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<?> handleTokenGeneration(String username) {
        try {
            String accessToken = jwtTokenProvider.generateAccessToken(username);
            String refreshToken = jwtTokenProvider.generateRefreshToken(username);
            TokenResponse response = new TokenResponse(true, accessToken, refreshToken);
            return ResponseEntity.ok(response);
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            return createAuthResponse(false, "Invalid username or password", HttpStatus.UNAUTHORIZED);
        } catch (RuntimeException e) {
            return createErrorResponse(e);
        }
    }

    private ResponseEntity<AuthResponse> createAuthResponse(boolean success, String message, HttpStatus status) {
        AuthResponse response = new AuthResponse(success, message);
        return ResponseEntity.status(status).body(response);
    }

    private ResponseEntity<AuthResponse> createErrorResponse(RuntimeException e) {
        log.error("Unexpected error occurred: {}", e.getMessage());
        return createAuthResponse(false, "An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TokenResponse {
        private boolean success;
        private String message;
        private String accessToken;
        private String refreshToken;

        public TokenResponse(boolean success, String accessToken, String refreshToken) {
            this.success = success;
            this.message = "Token generated successfully";
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthRequest {
        private String username;
        private String password;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthResponse {
        private boolean success;
        private String message;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TokenRefreshRequest {
        private String username;
        private String refreshToken;
    }
}
