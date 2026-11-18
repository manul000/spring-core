package com.study.core.model;

public record Task(Long id, String title, String description, Priority priority, Status status) {
    public enum Priority {
        LOW,
        MEDIUM,
        HIGH
    }

    public enum Status {
        NEW,
        IN_PROGRESS,
        DONE
    }
}
