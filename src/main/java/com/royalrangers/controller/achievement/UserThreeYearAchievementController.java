package com.royalrangers.controller.achievement;

import com.fasterxml.jackson.annotation.JsonView;
import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.achievement.UserAchievementRequestDto;
import com.royalrangers.model.Views;
import com.royalrangers.service.achievement.UserThreeYearAchievementService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/achievements/userThreeYear")
public class UserThreeYearAchievementController {

    @Autowired
    private UserThreeYearAchievementService userThreeYearAchievementService;

    @JsonView(Views.AchievementProfile.class)
    @GetMapping
    public ResponseResult getAllTreeYearAchievement() {
        try {
            return ResponseBuilder.success(userThreeYearAchievementService.findAllForUser());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get all UserThreeYearAchievement");
        }
    }

    @PostMapping
    public ResponseResult addTreeYearAchievement(@RequestBody UserAchievementRequestDto params) {
        try {
            userThreeYearAchievementService.addUserThreeYearAchievement(params);
            return ResponseBuilder.success("Successfully added userThreeYearAchievement");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed add userThreeYearAchievement");
        }
    }

    @JsonView(Views.AchievementProfile.class)
    @GetMapping("/{userAchievementId}")
    public ResponseResult getUserThreeYearAchievementById(@PathVariable Long userAchievementId) {
        try {
            return ResponseBuilder.success(userThreeYearAchievementService.getUserThreeYearAchievementById(userAchievementId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get userAchievement by id");
        }
    }

    @DeleteMapping("/{userAchievementId}")
    public ResponseResult deleteUserThreeYearAchievement(@PathVariable Long userAchievementId) {
        try {
            userThreeYearAchievementService.deleteUserThreeYearAchievement(userAchievementId);
            return ResponseBuilder.success("UserAchievement was success delete");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed delete userAchievement");
        }
    }

    @PutMapping("/{userAchievementId}")
    public ResponseResult editUserThreeYearAchievement(@RequestBody UserAchievementRequestDto params, @PathVariable Long userAchievementId) {
        try {
            userThreeYearAchievementService.editUserThreeYearAchievement(params, userAchievementId);
            return ResponseBuilder.success("Successfully editing UserThreeYearAchievement");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed edit userAchievement");
        }
    }

}
