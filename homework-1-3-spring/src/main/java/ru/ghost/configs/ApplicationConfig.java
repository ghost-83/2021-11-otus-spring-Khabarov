package ru.ghost.configs;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ghost.services.QuizService;

@Configuration
public class ApplicationConfig {

    @Bean
    public SmartInitializingSingleton importProcessor(QuizService quizService) {
        return quizService::run;
    }
}

