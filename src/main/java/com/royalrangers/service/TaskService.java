package com.royalrangers.service;

import com.royalrangers.model.achievements.Task;

import java.util.List;

public interface TaskService {
    public Task addTask(Task achievement);

    public Task getTaskById(Long id);

    public void deleteTask(Long id);

    public Task editTask(Task achievement);

    public List<Task> getAll();
}
