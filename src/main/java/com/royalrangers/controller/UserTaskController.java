package com.royalrangers.controller;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.service.UserTaskService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/achievements/userTask")
public class UserTaskController {

    @Autowired
    private UserTaskService userTaskService;

    @GetMapping
    public ResponseResult getAllUserTask() {
        try {
            return ResponseBuilder.success(userTaskService.getAllUserTaskAchievement());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get all UserTaskAchievement");
        }
    }

    @PostMapping
    public ResponseResult addUserTaskAchievement(@RequestBody Map<String, Object> params) {
        try {
            userTaskService.addUserTaskAchievement(params);
            return ResponseBuilder.success("successfully added UserTaskAchievement");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed add UserTaskAchievement");
        }
    }

    @GetMapping("/{userAchievementId}")
    public ResponseResult getUserTaskAchievementById(@PathVariable Long userAchievementId) {
        try {
            return ResponseBuilder.success(userTaskService.getUserTaskAchievementById(userAchievementId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get userAchievement by id");
        }
    }

    @DeleteMapping("/{userAchievementId}")
    public ResponseResult deleteUserTaskAchievement(@PathVariable Long userAchievementId) {
        try {
            userTaskService.deleteUserTaskAchievement(userAchievementId);
            return ResponseBuilder.success("UserAchievement was success delete");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed delete userAchievement");
        }
    }

    @PutMapping("/{userAchievementId}")
    public ResponseResult editUserTaskAchievement(@RequestBody Map<String, Object> params, @PathVariable Long userAchievementId) {
        try {
            userTaskService.editUserTaskAchievement(params, userAchievementId);
            return ResponseBuilder.success("successfully editing UserTaskAchievement");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed edit userAchievement");
        }
    }
}
