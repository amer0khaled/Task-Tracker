package com.amerkhaled.tasktracker.mappers;

import com.amerkhaled.tasktracker.domain.dto.TaskDto;
import com.amerkhaled.tasktracker.domain.entites.Task;

public interface TaskMapper {

    Task fromDto(TaskDto taskDto);
    TaskDto toDto(Task task);

}
