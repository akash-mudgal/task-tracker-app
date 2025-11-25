package com.akashmudgal.tasktrackerapp.domain.entities;

import com.akashmudgal.tasktrackerapp.domain.TaskPriority;
import com.akashmudgal.tasktrackerapp.domain.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id",updatable = false, nullable = false)
    private UUID id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="description")
    private String description;

    @Column(name = "due_date", nullable = false)
    private LocalDateTime dueDate;

    @Column(name = "task_priority", nullable = false)
    private TaskPriority priority;

    @Column(name = "task_status", nullable = false)
    private TaskStatus status;

    @Column(name = "created",nullable = false)
    private LocalDateTime created;

    @Column(name = "updated",nullable = false)
    private LocalDateTime updated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_list_id")
    private TaskList taskList;

}
