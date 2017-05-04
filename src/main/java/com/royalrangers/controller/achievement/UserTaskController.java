package com.royalrangers.controller.achievement;

import com.fasterxml.jackson.annotation.JsonView;
import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.achievement.UserAchievementRequestDto;
import com.royalrangers.model.Views;
import com.royalrangers.service.achievement.UserTaskService;
import com.royalrangers.utils.ResponseBuilder;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/achievements/userTask")
public class UserTaskController {

    @Autowired
    private UserTaskService userTaskService;

    @JsonView(Views.Achievement.class)
    @GetMapping
    @ApiOperation(value = "Get a list of tasks for current user")
    public ResponseResult getAllTaskForUser() {
        try {
            return ResponseBuilder.success(userTaskService.getAllForUser());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed get all UserTask for current user");
        }
    }

    @JsonView(Views.Achievement.class)
    @GetMapping("/{userTaskId}")
    @ApiOperation(value = "Get user task info")
    public ResponseResult getUserTaskById(@PathVariable Long userTaskId) {
        try {
            return ResponseBuilder.success(userTaskService.getUserTaskById(userTaskId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed get userTask by id");
        }
    }

    @DeleteMapping("/{userTaskId}")
    @ApiOperation(value = "Delete user task")
    public ResponseResult deleteUserTask(@PathVariable Long userTaskId) {
        try {
            userTaskService.deleteUserTask(userTaskId);
            return ResponseBuilder.success("UserTask was success delete");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed delete userTask");
        }
    }

    @PutMapping("/{userTaskId}")
    @ApiOperation(value = "Update user task")
    public ResponseResult editUserTask(@RequestBody UserAchievementRequestDto params, @PathVariable Long userTaskId) {
        try {
            userTaskService.editUserTask(params, userTaskId);
            return ResponseBuilder.success("Successfully editing UserTask");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed edit userTask");
        }
    }
}
