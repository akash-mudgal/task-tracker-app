package com.akashmudgal.tasktrackerapp.mappers.impl;

import com.akashmudgal.tasktrackerapp.domain.dto.TaskDto;
import com.akashmudgal.tasktrackerapp.domain.entities.Task;
import com.akashmudgal.tasktrackerapp.mappers.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task fromDto(TaskDto taskDto) {
        return new Task(
                taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.dueDate(),
                taskDto.priority(),
                taskDto.status(),
                null,
                null,
                null
        );
    }

    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus()
        );
    }
}
