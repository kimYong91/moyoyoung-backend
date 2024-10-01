package org.community.moyoyoung;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) throws JsonProcessingException {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
            if (new BCryptPasswordEncoder().matches(authRequest.getPassword(), userDetails.getPassword())) {
                String token = jwtTokenProvider.generateToken(userDetails.getUsername());
                AuthResponse response = new AuthResponse(true, token);
                return ResponseEntity.ok(objectMapper.writeValueAsString(response));
            } else {
                AuthResponse response = new AuthResponse(false, "Invalid username or password");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(objectMapper.writeValueAsString(response));
            }
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            AuthResponse response = new AuthResponse(false, "Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(objectMapper.writeValueAsString(response));
        } catch (RuntimeException e) {
            log.error("Unexpected error occurred: {}", e.getMessage());
            AuthResponse response = new AuthResponse(false, "An unexpected error occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(objectMapper.writeValueAsString(response));
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
}
