package com.akashmudgal.tasktrackerapp.controllers;


import com.akashmudgal.tasktrackerapp.domain.dto.TaskDto;
import com.akashmudgal.tasktrackerapp.domain.entities.Task;
import com.akashmudgal.tasktrackerapp.mappers.TaskMapper;
import com.akashmudgal.tasktrackerapp.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/task-lists/{task_list_id}/tasks")
public class TaskController {

    private final TaskService taskService;

    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @PostMapping
    public TaskDto createTask(@PathVariable("task_list_id") UUID taskListId, @RequestBody TaskDto taskDto) {

        return taskMapper.toDto(taskService.createTask(taskListId, taskMapper.fromDto(taskDto)));
    }

    @GetMapping
    public List<TaskDto> getTasks(@PathVariable("task_list_id") UUID taskListId) {
        return taskService.listTasks(taskListId).stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @GetMapping("/{task_id}")
    public Optional<TaskDto> getTask(@PathVariable("task_list_id") UUID taskListId, @PathVariable("task_id") UUID tasktId) {
        return taskService.getTask(taskListId,tasktId)
                .map(taskMapper::toDto);
    }

    @PutMapping("/{task_id}")
    public TaskDto updateTask(@PathVariable("task_list_id") UUID taskListId,@PathVariable("task_id") UUID taskId, @RequestBody TaskDto taskDto) {
        Task updatedTask = taskService.updateTask(taskListId, taskId, taskMapper.fromDto(taskDto));

        return taskMapper.toDto(updatedTask);
    }

    @DeleteMapping("/{task_id}")
    public void deleteTask(@PathVariable("task_id") UUID taskId) {
        taskService.deleteTask(taskId);
    }

}
