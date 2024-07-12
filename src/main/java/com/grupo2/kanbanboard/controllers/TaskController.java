package com.grupo2.kanbanboard.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.grupo2.kanbanboard.entities.Task;
import com.grupo2.kanbanboard.entities.TaskStatusEnum;
import com.grupo2.kanbanboard.requests.CreateTaskInput;
import com.grupo2.kanbanboard.services.TaskService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class TaskController {
    public TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    public ResponseEntity<?> createTasks(@RequestBody CreateTaskInput createTaskInput) {
        if (createTaskInput.status() != "toDoTasks" || createTaskInput.status() != "doingTasks" || createTaskInput.status() != "doneTasks" ) {
            return new ResponseEntity<>("Input a valid status.", HttpStatus.NOT_FOUND);
        }
        Task taskCreated = taskService.create(createTaskInput.name(), createTaskInput.projectId(), TaskStatusEnum.valueOf(createTaskInput.status()));
        return new ResponseEntity<>(taskCreated, HttpStatus.CREATED);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable int id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
