package com.grupo2.kanbanboard.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.grupo2.kanbanboard.services.TaskService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class TaskController {
    public TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    
    @PostMapping("/tasks")
    public ResponseEntity<Task> createTasks(@RequestBody CreateTaskInput cre) {
        return new String();
    }
    
}
