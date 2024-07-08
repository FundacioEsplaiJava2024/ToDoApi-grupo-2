package com.grupo2.kanbanboard.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.kanbanboard.entities.Project;
import com.grupo2.kanbanboard.requests.CreateProjectInput;
import com.grupo2.kanbanboard.services.ProjectService;

@RestController
public class ProjectController {
    public ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/tasks")
    public ResponseEntity<Project> createTask(@RequestBody CreateProjectInput createProjectInput) {
        Project taskCreated = projectService.create(createProjectInput.toProject());

        return new ResponseEntity<>(taskCreated, HttpStatus.CREATED);
    }
}
