package com.royalrangers.controller;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.service.UserTaskService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserTaskController {

    @Autowired
    private UserTaskService userTaskService;

    @RequestMapping(value = "/achievements/userTask", method = RequestMethod.GET)
    public ResponseResult getAllUserTask() {
        try {
            return ResponseBuilder.success(userTaskService.getAllUserTaskAchievement());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get all UserTaskAchievement");
        }
    }

    @RequestMapping(value = "/achievements/userTask", method = RequestMethod.POST)
    public ResponseResult addUserTaskAchievement(@RequestBody Map<String, Object> params) {
        try {
            userTaskService.addUserTaskAchievement(params);
            return ResponseBuilder.success("successfully added UserTaskAchievement");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed add UserTaskAchievement");
        }
    }

    @RequestMapping(value = "/achievements/userTask/{userAchievementId}", method = RequestMethod.GET)
    public ResponseResult getUserTaskAchievementById(@PathVariable Long userAchievementId) {
        try {
            return ResponseBuilder.success(userTaskService.getUserTaskAchievementById(userAchievementId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get userAchievement by id");
        }
    }

    @RequestMapping(value = "/achievements/userTask/{userAchievementId}", method = RequestMethod.DELETE)
    public ResponseResult deleteUserTaskAchievement(@PathVariable Long userAchievementId) {
        try {
            userTaskService.deleteUserTaskAchievement(userAchievementId);
            return ResponseBuilder.success("UserAchievement was success delete");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed delete userAchievement");
        }
    }

    @RequestMapping(value = "/achievements/userTask/{userAchievementId}", method = RequestMethod.PUT)
    public ResponseResult editUserTaskAchievement(@RequestBody Map<String, Object> params, @PathVariable Long userAchievementId) {
        try {
            userTaskService.editUserTaskAchievement(params, userAchievementId);
            return ResponseBuilder.success("successfully editing UserTaskAchievement");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed edit userAchievement");
        }
    }
}
