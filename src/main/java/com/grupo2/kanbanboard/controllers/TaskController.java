package com.grupo2.kanbanboard.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.grupo2.kanbanboard.entities.Task;
import com.grupo2.kanbanboard.entities.TaskStatusEnum;
import com.grupo2.kanbanboard.requests.CreateTaskInput;
import com.grupo2.kanbanboard.requests.UpdateTaskInput;
import com.grupo2.kanbanboard.services.TaskService;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
        if (taskStatusIsValid(createTaskInput.status())) {
            Task taskCreated = taskService.create(createTaskInput.name(), createTaskInput.projectId(),
                    TaskStatusEnum.valueOf(createTaskInput.status()));
            return new ResponseEntity<>(taskCreated, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Input a valid status.", HttpStatus.NOT_FOUND);
    }

    @PatchMapping("tasks/{id}")
    public ResponseEntity<?> updateTask(@PathVariable int id,
            @RequestBody UpdateTaskInput updateTaskInput) {
        Optional<Task> optionalTask = taskService.findById(id);

        if (optionalTask.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Task taskToUpdate = optionalTask.get();

        if (taskStatusIsValid(updateTaskInput.status())) {
            Task taskUpdated = taskService.changeStatus(updateTaskInput.status(), taskToUpdate);
            return new ResponseEntity<>(taskUpdated, HttpStatus.OK);
        }
        return new ResponseEntity<>("Input a valid status.", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable int id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    public boolean taskStatusIsValid(String status) {
        if (status.equals("toDoTasks") || status.equals("doingTasks")
                || status.equals("doneTasks"))
            return true;
        return false;
    }

}
