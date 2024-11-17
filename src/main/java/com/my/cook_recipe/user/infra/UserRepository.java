package com.my.cook_recipe.user.infra;

import com.my.cook_recipe.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    Optional<User> findByUserId(String id);

    Optional<User> findByNickname(String nickname);
}
