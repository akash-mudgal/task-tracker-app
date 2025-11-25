package com.akashmudgal.tasktrackerapp.mappers.impl;

import com.akashmudgal.tasktrackerapp.domain.TaskStatus;
import com.akashmudgal.tasktrackerapp.domain.dto.TaskDto;
import com.akashmudgal.tasktrackerapp.domain.dto.TaskListDto;
import com.akashmudgal.tasktrackerapp.domain.entities.Task;
import com.akashmudgal.tasktrackerapp.domain.entities.TaskList;
import com.akashmudgal.tasktrackerapp.mappers.TaskListMapper;
import com.akashmudgal.tasktrackerapp.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public class TaskListMapperImpl implements TaskListMapper {

    private TaskMapper taskMapper;

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
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::fromDto)
                                .toList())
                        .orElse(null),
                null,
                null
        );
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        final List<Task> tasks = taskList.getTasks();

        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(tasks)
                        .map(List::size)
                        .orElse(0),
                calculateProgress(tasks),
                Optional.ofNullable(tasks)
                        .map(t -> t.stream()
                                .map(taskMapper::toDto)
                                .toList())
                        .orElse(List.of())
        );
    }

    private Double calculateProgress(List<Task> tasks) {
        if(null == tasks)
            return null;

        long closedTaskCount = tasks.stream().filter(t -> t.getStatus()== TaskStatus.CLOSED)
                .count();

        return (double)closedTaskCount / tasks.size();
    }
}
