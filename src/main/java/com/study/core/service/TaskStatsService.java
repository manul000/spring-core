package com.study.core.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope("prototype")
public class TaskStatsService {
    private final UUID uuid = UUID.randomUUID();

    public UUID uuid() {
        return uuid;
    }
}
