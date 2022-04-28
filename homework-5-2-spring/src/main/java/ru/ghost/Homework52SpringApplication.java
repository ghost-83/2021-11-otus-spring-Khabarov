package ru.ghost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class Homework52SpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(Homework52SpringApplication.class, args);
    }

}
