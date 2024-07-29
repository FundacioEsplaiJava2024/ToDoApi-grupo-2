package com.grupo2.kanbanboard.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.grupo2.kanbanboard.entities.Project;
import com.grupo2.kanbanboard.entities.User;
import com.grupo2.kanbanboard.repositories.ProjectRepository;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    public AuthService userService;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project create(String projectName, String login) {
        Project project = new Project();
        project.setProjectName(projectName);
        UserDetails user = userService.loadUserByUsername(login);
        User rUser = (User) user;
        project.setUser(rUser);
        return projectRepository.save(project);
    }

    public List<Project> getUserProjects(String username) {
        UserDetails user = userService.loadUserByUsername(username);
        User rUser = (User) user;
        List<Project> projects = projectRepository.findByUserId(rUser.getId());
        return projects;
    }

    public List<Project> findAll() {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAll().forEach(projects::add);

        return projects;
    }

    public Optional<Project> findById(int id) {
        return projectRepository.findById(id);
    }

    public Project save(Project projectToUpdate) {
        return projectRepository.save(projectToUpdate);
    }

    public void delete(int id) {
        projectRepository.deleteById(id);
    }
}
