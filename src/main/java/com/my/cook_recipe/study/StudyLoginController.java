package com.my.cook_recipe.study;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudyLoginController {
    @GetMapping("/login")
    public String loginView(){
        return "/study/login";
    }
    @GetMapping("/admin")
    public String adminVIew() {
        return "/study/admin";
    }
    @PostMapping("/loginProc")
    public String loginProc(){
        System.out.println("로그인");
        return null;
    }
}
