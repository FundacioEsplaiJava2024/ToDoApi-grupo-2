package com.grupo2.kanbanboard.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.kanbanboard.entities.Project;
import com.grupo2.kanbanboard.entities.Task;
import com.grupo2.kanbanboard.requests.CreateProjectInput;
import com.grupo2.kanbanboard.requests.CreateTaskInput;
import com.grupo2.kanbanboard.requests.UpdateProjectInput;
import com.grupo2.kanbanboard.services.ProjectService;
import com.grupo2.kanbanboard.services.TaskService;

@RestController
public class TaskController {
    public TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/task")
    public ResponseEntity<Task> createTask(@RequestBody CreateTaskInput createTaskInput) {
        Task taskCreated = taskService.create(createTaskInput.toTask());

        return new ResponseEntity<>(taskCreated, HttpStatus.CREATED);
    }

    @GetMapping("/task")
    public ResponseEntity<List<Task>> allTasks() {
        List<Task> tasks = taskService.findAll();

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id,
            @RequestBody UpdateTaskInput updateProjectInput) {
        Optional<Project> optionalTask = projectService.findById(id);

        if (optionalTask.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Project taskToUpdate = optionalTask.get();

        taskToUpdate.setProjectName(updateProjectInput.projectName());

        Project taskUpdated = projectService.update(taskToUpdate);

        return new ResponseEntity<>(taskUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        projectService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
