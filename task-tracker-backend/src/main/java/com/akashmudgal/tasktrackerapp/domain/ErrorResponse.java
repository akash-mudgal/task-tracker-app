package com.akashmudgal.tasktrackerapp.domain;

public record ErrorResponse(
        int status,
        String message,
        String details
) { }
