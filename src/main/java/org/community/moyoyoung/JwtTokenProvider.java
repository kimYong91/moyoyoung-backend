package org.community.moyoyoung;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {

    // 상수 선언
    public static final String CLAIM_TOKEN_TYPE = "token_type";

    private final SecretKey key;
    private final long JWT_EXPIRATION;
    private final long REFRESH_EXPIRATION;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret,
                            @Value("${jwt.expiration}") long expiration,
                            @Value("${jwt.refresh.expiration}") long refreshExpiration) {
        this.key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
        this.JWT_EXPIRATION = expiration;
        this.REFRESH_EXPIRATION = refreshExpiration;
    }

    public String generateAccessToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .claim(CLAIM_TOKEN_TYPE, TokenType.ACCESS_TOKEN)
                .signWith(key)
                .compact();
    }

    public String generateRefreshToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + REFRESH_EXPIRATION);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .claim(CLAIM_TOKEN_TYPE, TokenType.REFRESH_TOKEN)
                .signWith(key)
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) throws TokenExpiredException, InvalidTokenException {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new TokenExpiredException("Token has expired", e);
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidTokenException("Invalid token", e);
        }
    }

    public TokenType getTokenType(String token) {
        Claims claims = getClaimsFromToken(token);
        String tokenType = claims.get(CLAIM_TOKEN_TYPE, String.class);
        return TokenType.fromString(tokenType);
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static class TokenExpiredException extends RuntimeException {
        public static final String CODE = "TOKEN_EXPIRED";
        public TokenExpiredException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class InvalidTokenException extends RuntimeException {
        public static final String CODE = "TOKEN_INVALID";
        public InvalidTokenException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public enum TokenType {
        ACCESS_TOKEN("ACCESS_TOKEN"),
        REFRESH_TOKEN("REFRESH_TOKEN");
    
        private final String type;
    
        TokenType(String type) {
            this.type = type;
        }
    
        public String getType() {
            return type;
        }
    
        public static TokenType fromString(String tokenType) {
            for (TokenType type : TokenType.values()) {
                if (type.getType().equals(tokenType)) {
                    return type;
                }
            }
            return null;
        }
    }
    
}
