package com.royalrangers.controller;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.service.UserTestService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/achievements/userTest")
public class UserTestController {

    @Autowired
    private UserTestService userTestService;

    @GetMapping
    public ResponseResult getAllUserTest() {
        try {
            return ResponseBuilder.success(userTestService.getAllUserTestAchievement());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get all UserTestAchievement");
        }
    }

    @PostMapping
    public ResponseResult addUserTestAchievement(@RequestBody Map<String, Object> params) {
        try {
            userTestService.addUserTestAchievement(params);
            return ResponseBuilder.success("successfully added UserTestAchievement");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed add UserTestAchievement");
        }
    }

    @GetMapping("/{userAchievementId}")
    public ResponseResult getUserTestAchievementById(@PathVariable Long userAchievementId) {
        try {
            return ResponseBuilder.success(userTestService.getUserTestAchievementById(userAchievementId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get userAchievement by id");
        }
    }

    @DeleteMapping("/{userAchievementId}")
    public ResponseResult deleteUserTestAchievement(@PathVariable Long userAchievementId) {
        try {
            userTestService.deleteUserTestAchievement(userAchievementId);
            return ResponseBuilder.success("UserAchievement was success delete");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed delete userAchievement");
        }
    }

    @PutMapping("/{userAchievementId}")
    public ResponseResult editUserTestAchievement(@RequestBody Map<String, Object> params, @PathVariable Long userAchievementId) {
        try {
            userTestService.editUserTestAchievement(params, userAchievementId);
            return ResponseBuilder.success("successfully editing UserTestAchievement");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed edit userAchievement");
        }
    }
}
