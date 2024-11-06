package com.my.cook_recipe.user.ui;

import com.my.cook_recipe.user.ui.request.LoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/login")
    public String loginView(Model model){
        return "user/login";
    }

    @GetMapping("/sign-up")
    public String joinView(){
        return "user/signUp";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest loginRequest){
        System.out.println(loginRequest.getId());
        return "/";
    }

    @PostMapping("/sign-up")
    public String join(){
        return null;
    }
}
