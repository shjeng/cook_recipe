package com.my.cook_recipe.auth.ui;

import com.my.cook_recipe.auth.application.AuthService;
import com.my.cook_recipe.auth.ui.request.RefreshRequest;
import com.my.cook_recipe.common.constant.CommonType;
import com.my.cook_recipe.common.error.exception.CustomJwtExpiredException;
import com.my.cook_recipe.common.provider.JwtProvider;
import com.my.cook_recipe.common.util.StringUtil;
import com.my.cook_recipe.user.ui.response.TokenResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApiController {

    private final AuthService authService;
    private final JwtProvider jwtProvider;

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refreshToken(HttpServletRequest request, HttpServletResponse response, @RequestBody RefreshRequest requestDto){
        String refreshToken = requestDto.getRefreshToken();

        if (StringUtil.isBlank(refreshToken)) {
            throw new CustomJwtExpiredException("Refresh Token is Null");
        }
        String category = jwtProvider.getCategory(refreshToken);
        if (!CommonType.REFRESH.getCategory().equals(category)) {
            throw new CustomJwtExpiredException("Invalid Refresh Token");
        }
        String role = jwtProvider.getRole(refreshToken);
        String userId = jwtProvider.getUserId(refreshToken);

        String newAccessToken = jwtProvider.create(CommonType.ACCESS, userId, role);
        String newRefreshToken = jwtProvider.create(CommonType.REFRESH, userId, role);

        TokenResponse result = TokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();

        return ResponseEntity.ok(result);
    }
}
