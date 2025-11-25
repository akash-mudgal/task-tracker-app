package com.akashmudgal.tasktrackerapp.controllers;

import com.akashmudgal.tasktrackerapp.domain.dto.TaskListDto;
import com.akashmudgal.tasktrackerapp.domain.entities.TaskList;
import com.akashmudgal.tasktrackerapp.mappers.TaskListMapper;
import com.akashmudgal.tasktrackerapp.mappers.TaskMapper;
import com.akashmudgal.tasktrackerapp.services.TaskListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/task-lists")
public class TaskListController {

    private TaskListService taskListService;

    private TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper, TaskMapper taskMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }



    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
        TaskList taskList = taskListService.createTaskList(
                taskListMapper.fromDto(taskListDto)
        );

        return taskListMapper.toDto(taskList);
    }

    @GetMapping
    public List<TaskListDto> listTaskLists() {
        return taskListService.listTaskLists().stream()
                        .map(taskListMapper::toDto)
                        .toList();

    }

    @GetMapping(path = "/{task_list_id}")
    public Optional<TaskListDto> getTaskList(@PathVariable("task_list_id") UUID taskListId) {
        Optional<TaskList> taskList = taskListService.getTaskList(taskListId);

        return taskList.map(taskListMapper::toDto);
    }

    @PutMapping("/{task_list_id}")
    public TaskListDto updateTaskList(@PathVariable("task_list_id") UUID taskListId,
                                                      @RequestBody TaskListDto taskListDto) {
        TaskList taskList = taskListService.updateTaskList(taskListId,
                taskListMapper.fromDto(taskListDto)
        );
        return taskListMapper.toDto(taskList);
    }

    @DeleteMapping("/{task_list_id}")
    public void deleteTaskList(@PathVariable("task_list_id") UUID taskListId) {
        taskListService.deleteTaskList(taskListId);
    }
}
