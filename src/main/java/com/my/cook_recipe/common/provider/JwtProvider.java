package com.my.cook_recipe.common.provider;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

@Component
public class JwtProvider {


    public SecretKey secretKey;

    public JwtProvider(@Value("${jwt.secretKey}") String secret) {
        secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String create(String email, String role) {
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS)); // 엑세스 토큰 유효시간 1시간
        Map<String, String> claimsMap = Map.of("email", email, "role", role);
        return Jwts.builder()
                .claims(claimsMap) // jwt 토큰 내 정보
                .expiration(expiredDate) // 만료시간
                .signWith(secretKey) // 암호화
                .issuedAt(Date.from(Instant.now())) // 발급 시간
                .compact();
    }

    public String getEmail(String jwt) { // JWT 토큰의 유효성 검사
        Claims claims = validateToken().parseSignedClaims(jwt).getPayload();
        return claims.get("email", String.class);
    }

    public String getRole(String jwt) { // JWT 토큰의 유효성 검사
        Claims claims = validateToken().parseSignedClaims(jwt).getPayload();
        return claims.get("role", String.class);
    }

    private JwtParser validateToken() {
        try{
            return Jwts.parser().verifyWith(secretKey).build();
        } catch (SecurityException | MalformedJwtException e) {
            // 서명 검증 실패 시 처리
            throw new IllegalArgumentException("Invalid JWT signature", e);
        } catch (ExpiredJwtException e) {
            // JWT가 만료된 경우 처리
            throw new IllegalArgumentException("Expired JWT token", e);
        } catch (Exception e) {
            // 기타 예외 처리
            throw new IllegalArgumentException("Invalid JWT token", e);
        }
    }


}
