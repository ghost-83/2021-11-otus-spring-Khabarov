package ru.ghost.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ghost.services.IOService;
import ru.ghost.services.IOServiceImpl;

@Configuration
public class IOConfig {

    @Bean
    public IOService getIOService() {
        return new IOServiceImpl(System.out, System.in);
    }
}
