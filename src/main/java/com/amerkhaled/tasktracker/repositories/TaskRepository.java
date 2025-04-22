package com.amerkhaled.tasktracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.amerkhaled.tasktracker.domain.entites.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByTaskListId(UUID taskListId);
    Optional<Task> findByTaskListIdAndId(UUID taskListId, UUID taskId);
    void deleteByTaskListIdAndId(UUID taskListId, UUID taskId);

}
