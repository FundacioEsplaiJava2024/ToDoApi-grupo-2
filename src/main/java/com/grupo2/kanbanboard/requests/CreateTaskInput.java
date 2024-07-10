package com.grupo2.kanbanboard.requests;

import com.grupo2.kanbanboard.entities.Task;
import com.grupo2.kanbanboard.entities.TaskStatusEnum;

public record CreateTaskInput(String name, TaskStatusEnum taskStatus) {
    public Task toTask(){
        Task task = new Task();

        task.setName(name);
        task.setStatus(taskStatus);
        return task;

    }
    
}
