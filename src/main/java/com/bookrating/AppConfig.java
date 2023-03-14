package com.bookrating;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.bookrating")
public class AppConfig {

    @Bean
    public EmailService emailService() {
        return new EmailService();
    }
}
