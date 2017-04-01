package com.royalrangers.controller.achievement;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.service.achievement.UserQuarterAchievementService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/achievements/userQuarter")
public class UserQuarterAchievementController {

    @Autowired
    private UserQuarterAchievementService userQuarterAchievementService;

    @GetMapping
    public ResponseResult getAllUserQuarterAchievement() {
        try {
            return ResponseBuilder.success(userQuarterAchievementService.findAllForUser());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get all UserQuarterAchievement");
        }
    }

    @PostMapping
    public ResponseResult addUserQuarterAchievement(@RequestBody Map<String, Object> params) {
        try {
            userQuarterAchievementService.addUserQuarterAchievement(params);
            return ResponseBuilder.success("Successfully added UserQuarterAchievement");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed add UserQuarterAchievement");
        }
    }

    @GetMapping("/{userAchievementId}")
    public ResponseResult getUserQuarterAchievementById(@PathVariable Long userAchievementId) {
        try {
            return ResponseBuilder.success(userQuarterAchievementService.getUserQuarterAchievementById(userAchievementId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get UserQuarterAchievement by id");
        }
    }

    @DeleteMapping("/{userAchievementId}")
    public ResponseResult deleteUserQuarterAchievement(@PathVariable Long userAchievementId) {
        try {
            userQuarterAchievementService.deleteUserQuarterAchievement(userAchievementId);
            return ResponseBuilder.success("UserAchievement was success delete");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed delete UserQuarterAchievement");
        }
    }

    @PutMapping("/{userAchievementId}")
    public ResponseResult editUserQuarterAchievement(@RequestBody Map<String, Object> params, @PathVariable Long userAchievementId) {
        try {
            userQuarterAchievementService.editUserQuarterAchievement(params, userAchievementId);
            return ResponseBuilder.success("Successfully editing UserQuarterAchievement");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed edit userAchievement");
        }
    }
}
