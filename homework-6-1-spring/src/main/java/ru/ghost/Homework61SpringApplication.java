package ru.ghost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableConfigurationProperties
@EnableMongoRepositories(basePackages = "ru.ghost.repository.mongo")
public class Homework61SpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(Homework61SpringApplication.class, args);
    }

}
