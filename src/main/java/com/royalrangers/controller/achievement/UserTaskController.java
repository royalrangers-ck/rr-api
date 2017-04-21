package com.royalrangers.controller.achievement;

import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.achievement.UserAchievementRequestDto;
import com.royalrangers.service.achievement.UserTaskService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/achievements/userTask")
public class UserTaskController {

    @Autowired
    private UserTaskService userTaskService;

    @GetMapping
    public ResponseResult getAllTaskForUser() {
        try {
            return ResponseBuilder.success(userTaskService.getAllForUser());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get all UserTask for current user");
        }
    }

    @GetMapping("/{userTaskId}")
    public ResponseResult getUserTaskById(@PathVariable Long userTaskId) {
        try {
            return ResponseBuilder.success(userTaskService.getUserTaskById(userTaskId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get userTask by id");
        }
    }

    @DeleteMapping("/{userTaskId}")
    public ResponseResult deleteUserTask(@PathVariable Long userTaskId) {
        try {
            userTaskService.deleteUserTask(userTaskId);
            return ResponseBuilder.success("UserTask was success delete");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed delete userTask");
        }
    }

    @PutMapping("/{userTaskId}")
    public ResponseResult editUserTask(@RequestBody UserAchievementRequestDto params, @PathVariable Long userTaskId) {
        try {
            userTaskService.editUserTask(params, userTaskId);
            return ResponseBuilder.success("Successfully editing UserTask");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed edit userTask");
        }
    }
}
