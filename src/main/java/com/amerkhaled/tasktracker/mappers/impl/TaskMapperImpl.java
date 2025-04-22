package com.amerkhaled.tasktracker.mappers.impl;

import com.amerkhaled.tasktracker.domain.dto.TaskDto;
import com.amerkhaled.tasktracker.mappers.TaskMapper;
import com.amerkhaled.tasktracker.domain.entites.Task;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task fromDto(TaskDto taskDto) {
        return new Task(
                taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.dueDate(),
                taskDto.status(),
                taskDto.priority(),
                null,
                null,
                null
        );
    }

    @Override
    public com.amerkhaled.tasktracker.domain.dto.TaskDto toDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getStatus(),
                task.getPriority()
        );
    }
}
