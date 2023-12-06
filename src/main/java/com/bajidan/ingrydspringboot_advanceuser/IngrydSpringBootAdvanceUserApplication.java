package com.bajidan.ingrydspringboot_advanceuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class IngrydSpringBootAdvanceUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(IngrydSpringBootAdvanceUserApplication.class, args);
    }

}
