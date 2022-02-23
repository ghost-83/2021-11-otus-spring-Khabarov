package ru.ghost;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableMongock
@SpringBootApplication
public class Homework41SpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(Homework41SpringApplication.class, args);
	}

}
