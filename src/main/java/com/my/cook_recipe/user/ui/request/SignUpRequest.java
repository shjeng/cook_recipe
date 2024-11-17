package com.my.cook_recipe.user.ui.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    private String id;
    private String password;
    private String passwordCheck;
    private String nickname;
}
