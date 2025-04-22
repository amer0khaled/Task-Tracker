package com.amerkhaled.tasktracker.controllers;

import com.amerkhaled.tasktracker.domain.dto.TaskListDto;
import com.amerkhaled.tasktracker.domain.entites.TaskList;
import com.amerkhaled.tasktracker.domain.entites.TaskPiriority;
import com.amerkhaled.tasktracker.mappers.TaskListMapper;
import com.amerkhaled.tasktracker.services.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/task-lists")
public class TaskListController {
    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;


    @Autowired
    public TaskListController(TaskListService taskListService,
                              TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDto> listTaskLists() {
        return taskListService.listTaskLists()
                .stream()
                .map(taskListMapper::toDto)
                .toList();
    }

    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
        TaskList createdTaskList = taskListService.createTaskList(
                taskListMapper.fromDto(taskListDto)
        );
        return taskListMapper.toDto(createdTaskList);
    }

    @GetMapping(path = "/{task_list_id}")
    Optional<TaskListDto> getTaskList(@PathVariable("task_list_id") UUID taskListId) {
        return taskListService.getTaskList(taskListId)
                .map(taskListMapper::toDto);
    }

    @PutMapping(path = "/{task_list_id}")
    TaskListDto updateTaskList(@PathVariable("task_list_id") UUID taskListId,
                               @RequestBody TaskListDto taskListDto) {

        TaskList updatedTaskList = taskListService.updateTaskList(taskListId,
                taskListMapper.fromDto(taskListDto));

        return taskListMapper.toDto(updatedTaskList);
    }

    @DeleteMapping(path = "/{task_list_id}")
    void deleteTaskList(@PathVariable("task_list_id") UUID taskListId) {
        taskListService.deleteTaskList(taskListId);
    }

}
