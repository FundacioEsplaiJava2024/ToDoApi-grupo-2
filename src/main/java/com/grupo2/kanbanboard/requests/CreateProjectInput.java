package com.grupo2.kanbanboard.requests;

import com.grupo2.kanbanboard.entities.Project;

public record CreateProjectInput(String name) {
    public Project toProject() {
        Project project = new Project();

        project.setProjectName(name);
        return project;
    }
}

