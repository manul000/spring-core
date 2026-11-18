package com.study.core.repository;

import com.study.core.model.Task;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("prod")
public class PriorityTaskRepository implements TaskRepository {
    private final List<Task> tasks = new ArrayList<>();
    private long id;

    public List<Task> findAll() {
        return tasks.stream()
                .sorted(java.util.Comparator.comparing(Task::priority).reversed())
                .toList();
    }

    public Task save(Task t) {
        Task saved = new Task(++id, t.title(), t.description(), t.priority(), t.status());
        tasks.add(saved);
        return saved;
    }

    public void delete(Long id) {
        tasks.removeIf(t -> t.id().equals(id));
    }

    public void updateStatus(Long id, Task.Status status) {
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.id().equals(id)) {
                tasks.set(i, new Task(t.id(), t.title(), t.description(), t.priority(), status));
                return;
            }
        }
        throw new IllegalArgumentException("Задача не найдена: " + id);
    }
}
