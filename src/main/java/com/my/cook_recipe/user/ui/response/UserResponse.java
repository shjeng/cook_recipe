package com.my.cook_recipe.user.ui.response;

import com.my.cook_recipe.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserResponse {
    private String userId;
    private String nickname;
    private String role; // USER ADMIN

    public static UserResponse toEntity(User user){
        return UserResponse.builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .role(user.getRole())
                .build();
    }
}
