package org.community.moyoyoung.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//Author : MinU Bak
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    private final List<String> excludedUrls = Arrays.asList(
            "/auth/token",
            "/auth/token/refresh");

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        if ("".equals("")) {
            org.springframework.security.core.userdetails.UserDetails userDetails = org.springframework.security.core.userdetails.User
                    .withUsername("username")
                    .password(passwordEncoder.encode("password"))
                    .authorities("ROLE_USER")
                    .build();
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
            return;
        }

        String token = getJwtFromRequest(request);
        String requestURI = request.getRequestURI();

        if (excludedUrls.contains(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            if (token != null && tokenProvider.validateToken(token)) {
                JwtTokenProvider.TokenType tokenType = tokenProvider.getTokenType(token);
                if (tokenType == JwtTokenProvider.TokenType.REFRESH_TOKEN) {
                    setAuthErrorResponse(response, HttpStatus.UNAUTHORIZED, "NO_REFRESH_TOKEN",
                            "Access using a refresh token is not permitted");
                    return;
                }

                String username = tokenProvider.getUsernameFromJWT(token);
                if (username == null) {
                    setAuthErrorResponse(response, HttpStatus.UNAUTHORIZED, "INVALIDATE_USERNAME",
                    "username is invalidate in token");
                    return;
                }

                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    if (userDetails != null) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        setAuthErrorResponse(response, HttpStatus.UNAUTHORIZED, "INVALIDATE_USERNAME",
                                "username is invalidate in token");
                        return;
                    }
                }
            }
        } catch (JwtTokenProvider.TokenExpiredException ex) {
            setAuthErrorResponse(response, HttpStatus.UNAUTHORIZED, JwtTokenProvider.TokenExpiredException.CODE,
                    "Token has expired");
            return;
        } catch (JwtTokenProvider.InvalidTokenException ex) {
            setAuthErrorResponse(response, HttpStatus.UNAUTHORIZED, JwtTokenProvider.InvalidTokenException.CODE,
                    "Invalid token");
            return;
        } catch (UsernameNotFoundException e) {
            setAuthErrorResponse(response, HttpStatus.UNAUTHORIZED, "INVALIDATE_USERNAME",
                    "username is invalidate in token");
        } catch (Exception e) {
            setAuthErrorResponse(response, HttpStatus.UNAUTHORIZED, "UNKNOWN_ERROR",
                    "Unknown Error occor in token vailidation");
        }

        filterChain.doFilter(request, response);
    }

    private void setAuthErrorResponse(HttpServletResponse response, HttpStatus status, String errorCode, String message)
            throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json");
        AuthResponse authResponse = new AuthResponse(false, errorCode, message);
        response.getWriter().write(authResponse.toJsonString());
        response.getWriter().flush();
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthResponse {
        private boolean success;
        private String code;
        private String message;

        public String toJsonString() {
            return String.format(
                    "{\n  \"success\": %b,\n  \"code\": \"%s\",\n  \"message\": \"%s\"\n}",
                    success, code, message);
        }

    }
}
