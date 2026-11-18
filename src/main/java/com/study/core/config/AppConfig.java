package com.study.core.config;

import com.study.core.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AppConfig {
    @Bean
    public java.time.Clock applicationClock() {
        return java.time.Clock.systemDefaultZone();
    }

    @Bean
    @Profile("prod")
    public String priorityRepositoryInfo(
            @Qualifier("priorityTaskRepository") TaskRepository repository) {
        String name = repository.getClass().getSimpleName();
        System.out.println("Явно выбран через @Qualifier: " + name);
        return name;
    }
}
