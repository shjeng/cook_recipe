package com.my.cook_recipe.auth.ui;

import com.my.cook_recipe.auth.application.AuthService;
import com.my.cook_recipe.common.constant.CommonType;
import com.my.cook_recipe.common.provider.JwtProvider;
import com.my.cook_recipe.common.util.StringUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApiController {

    private final AuthService authService;
    private final JwtProvider jwtProvider;

    @GetMapping("/refresh")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response){
        String refreshToken = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (CommonType.REFRESH.getCategory().equals(cookie.getName())) {
                refreshToken = cookie.getValue();
                break;
            }
        }
        if (StringUtil.isBlank(refreshToken)) {
            return ResponseEntity.badRequest().body("Refresh Token is Null");
        }
        String category = jwtProvider.getCategory(refreshToken);
        if (!CommonType.REFRESH.getCategory().equals(category)) {
            return ResponseEntity.badRequest().body("Invalid Refresh Token");
        }
        String role = jwtProvider.getRole(refreshToken);
        String userId = jwtProvider.getUserId(refreshToken);
        String newAccessToken = jwtProvider.create(CommonType.ACCESS, userId, role);
        response.setHeader(CommonType.ACCESS.getCategory(), newAccessToken);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
