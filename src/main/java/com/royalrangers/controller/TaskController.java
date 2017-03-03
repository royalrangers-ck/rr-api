package com.royalrangers.controller;

import com.royalrangers.model.achievements.Task;
import com.royalrangers.service.AchievementService;
import com.royalrangers.service.TaskService;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    AchievementService achievementService;

    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    public void addTask(@RequestBody Map<String, Object> map
    ){
        Task savedTask = new Task();

        savedTask.setDescription((String) map.get("description"));
        Integer id = (Integer) map.get("id");
        savedTask.setAchievement(achievementService.getAchievementById(id.longValue()));
        taskService.addTask(savedTask);
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public List<Task> getAllTasks(){
        return taskService.getAll();
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET)
    public void addTask(@PathVariable Long id){
        taskService.getTaskById(id);
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.DELETE)
    public void deleteTaskById(@PathVariable Long id){
        taskService.deleteTask(id);
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.PUT)
    public void editTaskById(@PathVariable Long id, @RequestBody Task task){
        taskService.editTask(task);
    }

}
