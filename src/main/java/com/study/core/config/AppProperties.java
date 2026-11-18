package com.study.core.config;

import com.study.core.model.Task.Priority;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {
    @Value("${app.name}")
    private String name;

    @Value("${app.max-tasks}")
    private int maxTasks;

    @Value("${app.default-priority}")
    private Priority defaultPriority;

    public String name() {
        return name;
    }

    public int maxTasks() {
        return maxTasks;
    }

    public Priority defaultPriority() {
        return defaultPriority;
    }

    @PostConstruct
    void loaded() {
        if (maxTasks < 3) throw new IllegalArgumentException("Для сценария нужно минимум 3 задачи");
        System.out.println("Настройки: " + name + ", " + maxTasks + ", " + defaultPriority);
    }
}
