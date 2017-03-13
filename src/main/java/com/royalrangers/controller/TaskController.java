package com.royalrangers.controller;

import com.royalrangers.model.achievements.Task;
import com.royalrangers.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    public void addTask(@RequestBody Map<String, Object> map){

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
    public void editTaskById(@RequestBody Task task){
        taskService.editTask(task);
    }

}
