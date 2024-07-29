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
import com.grupo2.kanbanboard.entities.User;
import com.grupo2.kanbanboard.requests.CreateProjectInput;
import com.grupo2.kanbanboard.requests.UpdateProjectInput;
import com.grupo2.kanbanboard.services.AuthService;
import com.grupo2.kanbanboard.services.ProjectService;

@RestController
public class ProjectController {
    public ProjectService projectService;
    public AuthService authService;

    public ProjectController(ProjectService projectService, AuthService authService) {
        this.projectService = projectService;
        this.authService = authService;
    }

    @PostMapping("/projects")
    public ResponseEntity<?> createProject(@RequestBody CreateProjectInput createProjectInput) throws Exception {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String login = authentication.getName();
            Project projectCreated = projectService.create(createProjectInput.name(), login);
            return new ResponseEntity<>(projectCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/projects")
    public ResponseEntity<?> userProjects(
            @RequestHeader(name = "authorization", required = true) String accessToken) throws Exception {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String login = authentication.getName();
            var user = authService.loadUserByUsername(login);
            User rUser = (User) user;
            return new ResponseEntity<List<Project>>(rUser.getProjects(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

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
