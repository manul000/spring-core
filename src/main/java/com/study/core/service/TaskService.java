package com.study.core.service;

import com.study.core.model.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    Task create(String title, Task.Priority priority);

    List<Task> getAll();

    void status(Long id, Task.Status status);

    UUID getStats();
}
