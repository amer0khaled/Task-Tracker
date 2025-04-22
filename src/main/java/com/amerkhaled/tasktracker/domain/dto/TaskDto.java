package com.amerkhaled.tasktracker.domain.dto;

import com.amerkhaled.tasktracker.domain.entites.TaskPiriority;
import com.amerkhaled.tasktracker.domain.entites.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskStatus status,
        TaskPiriority priority
) {
}
