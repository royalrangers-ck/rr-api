package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.TaskRequestDto;
import com.royalrangers.repository.achievement.TaskRepository;
import com.royalrangers.model.achievement.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    private TestService testService;

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public Task addTask(TaskRequestDto params) {
        Task savedTask = new Task();
        savedTask.setName(params.getName());
        savedTask.setDescription(params.getDescription());
        Integer id = params.getTestId();
        savedTask.setTest(testService.getTestById(id.longValue()));
        return taskRepository.saveAndFlush(savedTask);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findOne(id);
    }

    public void deleteTask(Long id) {
        taskRepository.delete(id);
    }

    public Task editTask(TaskRequestDto params, Long taskId) {
        Task editTask = getTaskById(taskId);
        Integer testId = params.getTestId();
        editTask.setName(params.getName());
        editTask.setDescription(params.getDescription());
        editTask.setTest(testService.getTestById(testId.longValue()));
        return taskRepository.saveAndFlush(editTask);
    }
}