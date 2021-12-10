package ru.ghost;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.ghost.service.QuizService;

import java.io.IOException;

@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "ru.ghost")
@Configuration
public class Homework12SpringApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Homework12SpringApplication.class);

        QuizService quizService = ctx.getBean(QuizService.class);
        quizService.run();

        ctx.close();
    }

}
