package com.akashmudgal.tasktrackerapp.mappers;

import com.akashmudgal.tasktrackerapp.domain.dto.TaskListDto;
import com.akashmudgal.tasktrackerapp.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);
}
