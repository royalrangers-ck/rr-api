package com.royalrangers.service.achievement;

import com.royalrangers.repository.achievement.TaskRepository;
import com.royalrangers.model.achievement.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    private TestService testService;

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public Task addTask(Map<String, Object> params) {
        Task savedTask = new Task();
        savedTask.setDescription((String) params.get("description"));
        Integer id = (Integer) params.get("test");
        savedTask.setTest(testService.getTestById(id.longValue()));
        return taskRepository.saveAndFlush(savedTask);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findOne(id);
    }

    public void deleteTask(Long id) {
        taskRepository.delete(id);
    }

    public Task editTask(Map<String, Object> params, Long taskId) {
        Task editTask = getTaskById(taskId);
        Integer testId = (Integer) params.get("test");
        editTask.setName((String) params.get("name"));
        editTask.setUpdateDate(new Date());
        editTask.setDescription((String) params.get("description"));
        editTask.setTest(testService.getTestById(testId.longValue()));
        return taskRepository.saveAndFlush(editTask);
    }
}