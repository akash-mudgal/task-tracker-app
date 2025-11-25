package com.akashmudgal.tasktrackerapp.mappers;

import com.akashmudgal.tasktrackerapp.domain.dto.TaskDto;
import com.akashmudgal.tasktrackerapp.domain.entities.Task;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);
}
