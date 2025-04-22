package com.amerkhaled.tasktracker.services.impl;

import com.amerkhaled.tasktracker.domain.entites.Task;
import com.amerkhaled.tasktracker.domain.entites.TaskList;
import com.amerkhaled.tasktracker.domain.entites.TaskPiriority;
import com.amerkhaled.tasktracker.domain.entites.TaskStatus;
import com.amerkhaled.tasktracker.repositories.TaskListRepository;
import com.amerkhaled.tasktracker.repositories.TaskRepository;
import com.amerkhaled.tasktracker.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository,
                           TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listTask(UUID taskListId) {
            return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    public Task createTask(UUID taskListId, Task task) {
        if (task.getId() != null) {
            throw new IllegalArgumentException("Task already has an ID!");
        }
        if (task.getTitle() == null || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task title cannot be empty!");
        }

        TaskPiriority priority = Optional.ofNullable(task.getPriority())
                .orElse(TaskPiriority.MEDIUM);

        TaskStatus status = TaskStatus.OPEN;

        LocalDateTime now = LocalDateTime.now();

        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("TaskList not found!"));

        Task taskToSave =  new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                status,
                priority,
                taskList,
                now,
                now
        );

        return taskRepository.save(taskToSave);

    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }

    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {

        if (task.getPriority() == null) {
            throw new IllegalArgumentException("Task priority cannot be null!");
        }
        if (task.getStatus() == null) {
            throw new IllegalArgumentException("Task status cannot be null!");
        }
        if (task.getTitle() == null || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task title cannot be empty!");
        }


        Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found!"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setStatus(task.getStatus());
        existingTask.setPriority(task.getPriority());
        existingTask.setUpdated(LocalDateTime.now());

        return taskRepository.save(existingTask);

    }

    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        Task task = taskRepository.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found!"));
        taskRepository.delete(task);
    }

}
