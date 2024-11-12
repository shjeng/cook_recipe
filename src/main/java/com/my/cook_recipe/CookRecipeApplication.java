package com.my.cook_recipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CookRecipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CookRecipeApplication.class, args);
	}

}
