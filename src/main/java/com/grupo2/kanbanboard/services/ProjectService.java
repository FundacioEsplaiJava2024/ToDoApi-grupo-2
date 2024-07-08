package com.grupo2.kanbanboard.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.grupo2.kanbanboard.entities.Project;
import com.grupo2.kanbanboard.repositories.ProjectRepository;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project create(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> findAll() {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAll().forEach(projects::add);

        return projects;
    }

    public Optional<Project> findById(int id) {
        return projectRepository.findById(id);
    }

    public Project update(Project projectToUpdate) {
        return projectRepository.save(projectToUpdate);
    }

    public void delete(int id) {
        projectRepository.deleteById(id);
    }
}

