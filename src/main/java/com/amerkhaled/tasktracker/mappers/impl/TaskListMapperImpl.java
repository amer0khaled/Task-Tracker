package com.amerkhaled.tasktracker.mappers.impl;

import com.amerkhaled.tasktracker.domain.dto.TaskListDto;
import com.amerkhaled.tasktracker.domain.entites.Task;
import com.amerkhaled.tasktracker.domain.entites.TaskList;
import com.amerkhaled.tasktracker.domain.entites.TaskStatus;
import com.amerkhaled.tasktracker.mappers.TaskListMapper;
import com.amerkhaled.tasktracker.mappers.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapper taskMapper;

    @Autowired
    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        return new TaskList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                Optional.ofNullable(taskListDto.tasks())
                        .map(taskDtos -> taskDtos.stream()
                                .map(taskMapper::fromDto)
                                .toList()
                        )
                .orElse(null),
                null,
                null
        );
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks())
                        .map(List::size).orElse(0),
                calculateTaskListProgress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::toDto)
                                .toList()
                        ).orElse(null)
        );
    }

    private Double calculateTaskListProgress(List<Task> tasks) {
        if (tasks == null) return null;

        long closedTasks = tasks.stream()
                .filter(task -> task.getStatus() == TaskStatus.CLOSED)
                .count();

        double progress = (double) closedTasks / tasks.size();

        return progress;
    }

}
