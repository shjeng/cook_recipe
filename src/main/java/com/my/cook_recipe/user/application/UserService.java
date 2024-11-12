package com.my.cook_recipe.user.application;

import com.my.cook_recipe.user.infra.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public boolean idDupleCheck(String id) {
        return userRepository.findByUserId(id).isPresent();
    }

}
