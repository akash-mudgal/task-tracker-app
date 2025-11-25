package com.akashmudgal.tasktrackerapp.services;

import com.akashmudgal.tasktrackerapp.domain.entities.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    Task createTask(UUID taskListId, Task task);

    List<Task> listTasks(UUID taskListId);

    Optional<Task> getTask(UUID taskListId, UUID taskId);

    Task updateTask(UUID taskListId, UUID taskId, Task task);

    void deleteTask(UUID taskId);
}
