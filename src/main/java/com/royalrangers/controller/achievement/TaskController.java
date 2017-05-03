package com.royalrangers.controller.achievement;

import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.achievement.TaskRequestDto;
import com.royalrangers.service.achievement.TaskService;
import com.royalrangers.utils.ResponseBuilder;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/achievements/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    @ApiOperation(value = "Get all tasks")
    public ResponseResult getAllTasks() {
        try {
            return ResponseBuilder.success(taskService.getAll());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed get tasks");
        }
    }

    @PostMapping
    @ApiOperation(value = "Add task")
    public ResponseResult addTask(@RequestBody TaskRequestDto params) {
        try {
            taskService.addTask(params);
            return ResponseBuilder.success("Task saved successfully");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed adding task");
        }
    }

    @GetMapping("/{taskId}")
    @ApiOperation(value = "Get task by id")
    public ResponseResult getTaskById(@PathVariable Long taskId) {
        try {
            return ResponseBuilder.success(taskService.getTaskById(taskId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed get Task by id");
        }
    }

    @DeleteMapping("/{taskId}")
    @ApiOperation(value = "Delete task by id")
    public ResponseResult deleteTaskById(@PathVariable Long taskId) {
        try {
            taskService.deleteTask(taskId);
            return ResponseBuilder.success("Task was successfully deleted");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed delete Task");
        }
    }

    @PutMapping("/{taskId}")
    @ApiOperation(value = "Update task by id")
    public ResponseResult editTaskById(@RequestBody TaskRequestDto params, @PathVariable Long taskId) {
        try {
            return ResponseBuilder.success(taskService.editTask(params, taskId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed edit Task");
        }
    }
}