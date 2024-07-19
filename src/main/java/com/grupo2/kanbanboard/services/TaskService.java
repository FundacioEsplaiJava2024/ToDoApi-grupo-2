package com.grupo2.kanbanboard.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Task changeStatus (String taskStatus, Task task) {
        task.setStatus(TaskStatusEnum.valueOf(taskStatus));
        return taskRepository.save(task);
    }

    @Transactional
    public void delete(int taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));

        Project project = task.getProject();
        if (project != null) {
            project.dismissTask(task);  
            projectRepository.save(project);  
        }

        taskRepository.delete(task);  
    }

    public Optional<Task> findById(int id) {
        return taskRepository.findById(id);
    }

}