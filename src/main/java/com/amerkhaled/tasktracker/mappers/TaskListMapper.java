package com.amerkhaled.tasktracker.mappers;

import com.amerkhaled.tasktracker.domain.dto.TaskListDto;
import com.amerkhaled.tasktracker.domain.entites.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);
    TaskListDto toDto(TaskList taskList);

}
