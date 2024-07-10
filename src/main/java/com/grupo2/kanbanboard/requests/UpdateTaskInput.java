package com.grupo2.kanbanboard.requests;

import com.grupo2.kanbanboard.entities.TaskStatusEnum;

public record UpdateTaskInput(TaskStatusEnum status) {
    
}