package com.royalrangers.service;

import com.royalrangers.dao.TaskRepository;
import com.royalrangers.model.achievements.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public Task addTask(Task achievement) {
        return taskRepository.saveAndFlush(achievement);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findOne(id);
    }

    public void deleteTask(Long id) {
        taskRepository.delete(id);
    }

    public Task editTask(Task achievement) {
        return taskRepository.saveAndFlush(achievement);
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
    }
}
