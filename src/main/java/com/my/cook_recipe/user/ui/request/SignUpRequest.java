package com.my.cook_recipe.user.ui.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    @NotBlank
    private String id;
    @NotBlank
    private String idCheck;
    @NotBlank
    private String password;
    @NotBlank
    private String passwordCheck;
    @NotBlank
    private String nickname;
    @NotBlank
    private String nicknameCheck;
}
