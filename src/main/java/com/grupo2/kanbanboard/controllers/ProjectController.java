package com.grupo2.kanbanboard.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.grupo2.kanbanboard.services.ProjectService;

@RestController
public class ProjectController {
    public ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
}
