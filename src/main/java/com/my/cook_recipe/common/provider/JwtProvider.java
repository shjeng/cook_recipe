package com.my.cook_recipe.common.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.secretKey}")
    public String secretKey;


    public String create(String email){
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS)); // 엑세스 토큰 유효시간 1시간
        SecretKey sk = Keys.hmacShaKeyFor(secretKey.getBytes());

        return Jwts.builder()
                .claim("email", email)
                .expiration(expiredDate)
                .signWith(sk)
                .compact();

    }

    public String validate(String jwt){ // JWT 토큰의 유효성 검사

        SecretKey sk = Keys.hmacShaKeyFor(secretKey.getBytes());
        Claims claims = null;
        try{
            claims = Jwts.parser().verifyWith(sk).build().parseSignedClaims(jwt).getPayload();
        }catch (ExpiredJwtException e) {
            throw e; // 예외를 던져서 상위 메서드에서 처리하도록 함
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return claims.getSubject();
    }
}
