package com.grupo2.kanbanboard.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.kanbanboard.entities.Project;
import com.grupo2.kanbanboard.requests.CreateProjectInput;
import com.grupo2.kanbanboard.requests.UpdateProjectInput;
import com.grupo2.kanbanboard.services.ProjectService;

@RestController
public class ProjectController {
    public ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/projects")
    public ResponseEntity<Project> createTask(@RequestBody CreateProjectInput createProjectInput) {
        Project taskCreated = projectService.create(createProjectInput.toProject());

        return new ResponseEntity<>(taskCreated, HttpStatus.CREATED);
    }

    @GetMapping("/projects")
    public ResponseEntity<List<Project>> allTasks() {
        List<Project> tasks = projectService.findAll();

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<Project> updateTask(@PathVariable int id,
            @RequestBody UpdateProjectInput updateProjectInput) {
        Optional<Project> optionalTask = projectService.findById(id);

        if (optionalTask.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Project taskToUpdate = optionalTask.get();

        taskToUpdate.setProjectName(updateProjectInput.projectName());

        Project taskUpdated = projectService.update(taskToUpdate);

        return new ResponseEntity<>(taskUpdated, HttpStatus.OK);
    }

}
