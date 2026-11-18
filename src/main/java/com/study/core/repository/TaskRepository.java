package com.study.core.repository;

import com.study.core.model.Task;

import java.util.List;

public interface TaskRepository {
    List<Task> findAll();

    Task save(Task task);

    void delete(Long id);

    void updateStatus(Long id, Task.Status status);
}
