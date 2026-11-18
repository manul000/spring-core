package com.study.core.runner;

import com.study.core.config.AppProperties;
import com.study.core.model.Task;
import com.study.core.repository.TaskRepository;
import com.study.core.service.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Locale;

@Component
public class AppRunner implements CommandLineRunner {
    private final TaskService service;
    private final AppProperties properties;
    private final ApplicationContext context;

    public AppRunner(TaskService service, AppProperties properties, ApplicationContext context) {
        this.service = service;
        this.properties = properties;
        this.context = context;
    }

    public void run(String... args) {
        System.out.printf(
                "Добро пожаловать в %s! Лимит: %d. Приоритет по умолчанию: %s%n",
                properties.name(), properties.maxTasks(), properties.defaultPriority());
        var low = service.create("Купить молоко", Task.Priority.LOW);
        var medium = service.create("Прочитать главу", Task.Priority.MEDIUM);
        service.create("Подготовить защиту", Task.Priority.HIGH);
        service.getAll().forEach(System.out::println);
        try {
            service.create("", null);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        try {
            while (true) service.create("Проверка лимита", null);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        service.status(low.id(), Task.Status.IN_PROGRESS);
        service.status(medium.id(), Task.Status.DONE);
        System.out.println(
                "DONE: " + service.getAll().stream().filter(t -> t.status() == Task.Status.DONE).toList());
        System.out.println(
                "HIGH: "
                        + service.getAll().stream().filter(t -> t.priority() == Task.Priority.HIGH).toList());
        service.getStats();
        service.getStats();
        System.out.println(
                "Репозиторий: " + context.getBean(TaskRepository.class).getClass().getSimpleName());
        System.out.println("Всего бинов: " + context.getBeanDefinitionCount());
        Arrays.stream(context.getBeanDefinitionNames())
                .filter(s -> s.toLowerCase(Locale.ROOT).contains("task"))
                .forEach(System.out::println);
    }
}
