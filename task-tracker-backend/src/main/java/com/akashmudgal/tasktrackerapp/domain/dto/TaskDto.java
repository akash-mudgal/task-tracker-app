package com.akashmudgal.tasktrackerapp.domain.dto;

import com.akashmudgal.tasktrackerapp.domain.TaskPriority;
import com.akashmudgal.tasktrackerapp.domain.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status

) { }
