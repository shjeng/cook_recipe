package com.my.cook_recipe.common.constant;

import lombok.Getter;

@Getter
public enum CommonType {
    ACCESS("access", "Access Token"), REFRESH("refresh", "Refresh Token");

    private final String category;
    private final String description;

    CommonType(String category, String description) {
        this.category = category;
        this.description = description;
    }
}
