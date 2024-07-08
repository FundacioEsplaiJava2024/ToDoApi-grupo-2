package com.grupo2.kanbanboard.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    
}
