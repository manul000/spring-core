package com.study.core.service;

import com.study.core.config.AppProperties;
import com.study.core.model.Task;
import com.study.core.repository.TaskRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;
    private final AppProperties properties;
    private final ObjectProvider<TaskStatsService> stats;

    public TaskServiceImpl(
            TaskRepository repository, AppProperties properties, ObjectProvider<TaskStatsService> stats) {
        this.repository = repository;
        this.properties = properties;
        this.stats = stats;
    }

    @PostConstruct
    void init() {
        System.out.println("TaskService инициализирован");
    }

    @PreDestroy
    void destroy() {
        System.out.println("Завершение работы. Задач в хранилище: " + repository.findAll().size());
    }

    public Task create(String title, Task.Priority priority) {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("Название не может быть пустым");
        if (repository.findAll().size() >= properties.maxTasks())
            throw new IllegalStateException("Достигнут лимит задач: " + properties.maxTasks());
        return repository.save(
                new Task(
                        null,
                        title,
                        "",
                        priority == null ? properties.defaultPriority() : priority,
                        Task.Status.NEW));
    }

    public List<Task> getAll() {
        return repository.findAll();
    }

    public void status(Long id, Task.Status s) {
        repository.updateStatus(id, s);
    }

    public UUID getStats() {
        UUID id = stats.getObject().uuid();
        System.out.println("Статистика " + id + ": задач " + repository.findAll().size());
        return id;
    }
}
