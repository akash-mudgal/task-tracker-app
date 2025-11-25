package com.akashmudgal.tasktrackerapp.services;

import com.akashmudgal.tasktrackerapp.domain.entities.TaskList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService {

    List<TaskList> listTaskLists();

    Optional<TaskList> getTaskList(UUID taskListId);

    TaskList createTaskList(TaskList taskList);

    void deleteTaskList(UUID taskListId);

    TaskList updateTaskList(UUID taskListId, TaskList taskList);
}
