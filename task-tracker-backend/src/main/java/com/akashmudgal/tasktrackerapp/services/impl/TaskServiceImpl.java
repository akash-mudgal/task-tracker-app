package com.akashmudgal.tasktrackerapp.services.impl;

import com.akashmudgal.tasktrackerapp.domain.TaskPriority;
import com.akashmudgal.tasktrackerapp.domain.TaskStatus;
import com.akashmudgal.tasktrackerapp.domain.entities.Task;
import com.akashmudgal.tasktrackerapp.domain.entities.TaskList;
import com.akashmudgal.tasktrackerapp.repositories.TaskListRepository;
import com.akashmudgal.tasktrackerapp.repositories.TaskRepository;
import com.akashmudgal.tasktrackerapp.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository =  taskListRepository;
    }

    @Override
    public Task createTask(UUID taskListId,Task task) {

        TaskList taskList = taskListRepository.getReferenceById(taskListId);
        LocalDateTime now = LocalDateTime.now();

        if(null != task.getId())
            throw new IllegalArgumentException("Task already has an ID!");

        if(task.getTitle() == null || task.getTitle().isBlank())
            throw new IllegalArgumentException("Task title is required");

        if(null == task.getDueDate())
            throw new IllegalArgumentException("Task due date is required");

        TaskPriority taskPriority = Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);

        return taskRepository.save(new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskPriority,
                TaskStatus.OPEN,
                now,
                now,
                taskList
        ));
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId,taskId);
    }

    @Override
    public Task updateTask(UUID taskListId,UUID taskId, Task task) {
        if(null == task.getId())
            throw new IllegalArgumentException("Task id is required");

        if(!Objects.equals(taskId, task.getId()))
            throw new IllegalArgumentException("Task ids do not match");

        if(null == task.getPriority())
            throw new IllegalArgumentException("Task must have a valid priority");

        if(null == task.getStatus())
            throw new IllegalArgumentException("Task must have a valid status");

        Task existingTask = taskRepository.findByTaskListIdAndId(taskListId,taskId)
                .orElseThrow(()-> new IllegalStateException("Task not found"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setUpdated(LocalDateTime.now());

        return taskRepository.save(existingTask);
    }

    @Override
    @Transactional
    public void deleteTask(UUID taskId) {
        taskRepository.deleteById(taskId);
    }
}
