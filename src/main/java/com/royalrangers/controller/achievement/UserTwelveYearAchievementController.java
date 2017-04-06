package com.royalrangers.controller.achievement;

import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.achievement.UserAchievementRequestDTO;
import com.royalrangers.service.achievement.UserTwelveYearAchievementService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/achievements/userTwelveYear")
public class UserTwelveYearAchievementController {

    @Autowired
    private UserTwelveYearAchievementService userTwelveYearAchievementService;

    @GetMapping
    public ResponseResult findByUserId() {
        try {
            return ResponseBuilder.success(userTwelveYearAchievementService.findAllForUser());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get all user achievement");
        }
    }

    @PostMapping
    public ResponseResult addUserTwelveYearAchievement(@RequestBody UserAchievementRequestDTO params) {
        try {
            userTwelveYearAchievementService.addUserTwelveYearAchievement(params);
            return ResponseBuilder.success("Successfully added userTwelveYearAchievement");
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
    public ResponseResult editUserTwelveYearAchievement(@RequestBody UserAchievementRequestDTO params, @PathVariable Long userAchievementId) {
        try {
            userTwelveYearAchievementService.editUserTwelveYearAchievement(params, userAchievementId);
            return ResponseBuilder.success("Successfully editing UserTwelveYearAchievement");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed edit userAchievement");
        }
    }

}
