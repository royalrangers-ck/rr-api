package com.royalrangers.controller;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.service.UserTwelveYearAchievementService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/achievements/userTwelveYear")
public class UserTwelveYearAchievementController {

    @Autowired
    private UserTwelveYearAchievementService userTwelveYearAchievementService;

    @GetMapping
    public ResponseResult getAllUserTwelveYearAchievement() {
        try {
            return ResponseBuilder.success(userTwelveYearAchievementService.getAllUserWithAchievement());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get all user achievement");
        }
    }

    @PostMapping
    public ResponseResult addUserTwelveYearAchievement(@RequestBody Map<String, Object> params) {
        try {
            userTwelveYearAchievementService.addUserTwelveYearAchievement(params);
            return ResponseBuilder.success("successfully added userTwelveYearAchievement");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed add userAchievement");
        }
    }

    @GetMapping("/{userAchievementId}")
    public ResponseResult getUserTwelveYearAchievementById(@PathVariable Long userAchievementId) {
        try {
            return ResponseBuilder.success(userTwelveYearAchievementService.getUserTwelveYearAchievementById(userAchievementId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get userAchievement by id");
        }
    }

    @DeleteMapping("/{userAchievementId}")
    public ResponseResult deleteUserTwelveYearAchievement(@PathVariable Long userAchievementId) {
        try {
            userTwelveYearAchievementService.delete(userAchievementId);
            return ResponseBuilder.success("UserAchievement was success delete");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed delete userAchievement");
        }
    }

    @PutMapping("/{userAchievementId}")
    public ResponseResult editUserTwelveYearAchievement(@RequestBody Map<String, Object> params, @PathVariable Long userAchievementId) {
        try {
            userTwelveYearAchievementService.editTwelveYearAchievement(params, userAchievementId);
            return ResponseBuilder.success("successfully editing UserTwelveYearAchievement");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed edit userAchievement");
        }
    }

}
