package com.grupo2.kanbanboard.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.grupo2.kanbanboard.entities.Task;
import com.grupo2.kanbanboard.repositories.TaskRepository;

@Service
public class TaskService {
    private final TaskRepository TaskRepository;

    public TaskService(TaskRepository TaskRepository) {
        this.TaskRepository = TaskRepository;
    }

    public Task create(Task task) {
        return TaskRepository.save(task);
    }

    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        TaskRepository.findAll().forEach(tasks::add);

        return tasks;
    }

    public Optional<Task> findById(int id) {
        return TaskRepository.findById(id);
    }

    public Task update(Task projectToUpdate) {
        return TaskRepository.save(projectToUpdate);
    }

    public void delete(int id) {
        TaskRepository.deleteById(id);
    }
}

