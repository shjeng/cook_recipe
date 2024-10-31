package com.my.cook_recipe.common.filter;

import com.my.cook_recipe.common.provider.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String token = parseBearerToken(request);
            if(token==null){
                filterChain.doFilter(request,response);
                return;
            }
            String email = jwtProvider.validate(token);
            if(email == null){
                filterChain.doFilter(request,response);
                return;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        filterChain.doFilter(request,response);
    }

    private String parseBearerToken(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        boolean hasAuthorization = StringUtils.hasText(authorization);
        if(!hasAuthorization) return null;

        boolean isBearer = authorization.startsWith("Bearer "); // "Bearer " 로 시작하느냐?
        if(!isBearer) return null;
        String token = authorization.substring(7);
        return token;
    }
}
