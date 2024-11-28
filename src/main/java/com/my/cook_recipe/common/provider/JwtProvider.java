package com.my.cook_recipe.common.provider;

import com.my.cook_recipe.common.constant.CommonType;
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

    public String create(CommonType category, String userId, String role, Long expiredMs) {
        Map<String, String> claimsMap = Map.of("category", category.getCategory(), "userId", userId, "role", role);

        // 1초에 1000밀리초
        // 10분에 600,000밀리초
        return Jwts.builder()
                .claims(claimsMap) // jwt 토큰 내 정보
                .expiration(new Date(System.currentTimeMillis() + expiredMs)) // 만료시간
                .signWith(secretKey) // 암호화
                .issuedAt(Date.from(Instant.now())) // 발급 시간
                .compact();
    }

    public String getUserId(String jwt) { // JWT 토큰의 유효성 검사
        return getTokenInfo(jwt, "userId");
    }

    private Claims getPayload(String jwt) {
        return validateToken().parseSignedClaims(jwt).getPayload();
    }

    public String getRole(String jwt) { // JWT 토큰의 유효성 검사
        return getTokenInfo(jwt, "role");
    }


    public String getCategory(String jwt) {
        return getTokenInfo(jwt, "category");
    }

    private String getTokenInfo(String jwt, String value) {
        Claims claims = getPayload(jwt);
        return claims.get(value, String.class);
    }

    private JwtParser validateToken() {
        try {
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
