package com.grupo2.kanbanboard.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.grupo2.kanbanboard.entities.Project;
import com.grupo2.kanbanboard.entities.Task;
import com.grupo2.kanbanboard.entities.TaskStatusEnum;
import com.grupo2.kanbanboard.repositories.ProjectRepository;
import com.grupo2.kanbanboard.repositories.TaskRepository;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;


    public TaskService (TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;

    }
    
    public Task create (String TaskName, int idProject, TaskStatusEnum taskStatus) {
        Task task = new Task();
        task.setName(TaskName);
        task.setStatus(taskStatus);
        Optional<Project> optionalProject = projectRepository.findById(idProject);  
        Project taskProject = optionalProject.get();
        task.setProject(taskProject);
        return taskRepository.save(task);
    }
}