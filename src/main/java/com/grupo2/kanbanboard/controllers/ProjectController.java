package com.grupo2.kanbanboard.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
    public ResponseEntity<Project> createProject(@RequestBody CreateProjectInput createProjectInput) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        Project projectCreated = projectService.create(createProjectInput.name(), login);
        return new ResponseEntity<>(projectCreated, HttpStatus.CREATED);
    }

    @GetMapping("/projects")
    public ResponseEntity<List<Project>> userProjects(@RequestHeader(name = "authorization", required = true)String accessToken) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<Project> projects = projectService.getUserProjects(username);

        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @PatchMapping("/projects/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable int id,
            @RequestBody UpdateProjectInput updateProjectInput) {
        Optional<Project> optionalProject = projectService.findById(id);

        if (optionalProject.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Project projectToUpdate = optionalProject.get();

        projectToUpdate.setProjectName(updateProjectInput.name());

        Project projectUpdated = projectService.save(projectToUpdate);

        return new ResponseEntity<>(projectUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable int id) {
        projectService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
