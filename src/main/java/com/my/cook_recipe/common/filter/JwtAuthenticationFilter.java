package com.my.cook_recipe.common.filter;

import com.my.cook_recipe.common.error.code.CommonErrorCode;
import com.my.cook_recipe.common.error.exception.CustomJwtExpiredException;
import com.my.cook_recipe.common.provider.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = parseBearerToken(request);
            if (token == null) {
                filterChain.doFilter(request, response);
                return;
            }
            String category = jwtProvider.getCategory(token);
            if (!"access".equals(category)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
                return;
            }

            String email = jwtProvider.getUserId(token);
            if (email == null) {
                filterChain.doFilter(request, response);
                return;
            }
            String role = jwtProvider.getRole(token);

            /*             // 권한부여X           */
//            AbstractAuthenticationToken authenticationToken =
//                    new UsernamePasswordAuthenticationToken(email,null, AuthorityUtils.NO_AUTHORITIES);

            /*          // 권한 여러개 부여    */
//            AbstractAuthenticationToken authenticationToken =
//                    new UsernamePasswordAuthenticationToken(email, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_" + role),new SimpleGrantedAuthority("ROLE_" + role) ));

            AbstractAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(email, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role))); // 권한 부여
            SecurityContext context = SecurityContextHolder.getContext();
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            context.setAuthentication(authenticationToken);
//            SecurityContext securityContext = SecurityContextHolder.createEmptyContext(); // 새로운 빈 보안 컨텍스트 생성
//            securityContext.setAuthentication(authenticationToken); // 앞서 생성한 인증 토큰을 보안 컨텍스트에 설정
            filterChain.doFilter(request, response);
        } catch (CustomJwtExpiredException e) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(e.getCode().getDescription());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String parseBearerToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        boolean hasAuthorization = StringUtils.hasText(authorization);
        if (!hasAuthorization) return null;

        boolean isBearer = authorization.startsWith("Bearer "); // "Bearer " 로 시작하느냐?
        if (!isBearer) return null;
        String token = authorization.substring(7);
        return token;
    }
}
