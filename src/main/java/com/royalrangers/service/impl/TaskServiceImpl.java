package com.royalrangers.service.impl;

import com.royalrangers.dao.TaskRepository;
import com.royalrangers.model.achievements.Task;
import com.royalrangers.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Override
    public Task addTask(Task achievement) {
        return taskRepository.saveAndFlush(achievement);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findOne(id);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.delete(id);
    }

    @Override
    public Task editTask(Task achievement) {
        return taskRepository.saveAndFlush(achievement);
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }
}
