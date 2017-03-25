package com.royalrangers.controller;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.model.achievement.QuarterAchievement;
import com.royalrangers.model.achievement.Task;
import com.royalrangers.service.QuarterAchievementService;
import com.royalrangers.service.TaskService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    QuarterAchievementService quarterAchievementService;

    @RequestMapping(value = "/achievements/task", method = RequestMethod.GET)
    public ResponseResult getAllTasks() {
        try {
            return ResponseBuilder.success(taskService.getAll());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get tasks");
        }
    }

    @RequestMapping(value = "/achievements/task", method = RequestMethod.POST)
    public ResponseResult addTask(@RequestBody Map<String, Object> params) {
        try {
            taskService.addTask(params);
            return ResponseBuilder.success("Task saved successfully");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed adding task");
        }
    }

    @RequestMapping(value = "/achievements/task/{taskId}", method = RequestMethod.GET)
    public ResponseResult getTaskById(@PathVariable Long taskId) {
        try {
            return ResponseBuilder.success(taskService.getTaskById(taskId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get Task by id");
        }
    }

    @RequestMapping(value = "/achievements/task/{taskId}", method = RequestMethod.DELETE)
    public ResponseResult deleteTaskById(@PathVariable Long taskId) {
        try {
            taskService.deleteTask(taskId);
            return ResponseBuilder.success("Task was successfully deleted");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed delete Task");
        }
    }

    @RequestMapping(value = "/achievements/task/{taskId}", method = RequestMethod.PUT)
    public ResponseResult editTaskById(@RequestBody Map<String, Object> params, @PathVariable Long taskId) {
        try {
            return ResponseBuilder.success(taskService.editTask(params, taskId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed edit Task");
        }
    }

}