package com.my.cook_recipe.user.domain;

import com.my.cook_recipe.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "t_user")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 255)
    private String userId;
    @Column(length = 255)
    private String password;
    @Column(length = 100)
    private String nickname;
    @Column(length = 100)
    private String role;
}
