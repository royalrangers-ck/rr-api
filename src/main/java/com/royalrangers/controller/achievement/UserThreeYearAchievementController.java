package com.royalrangers.controller.achievement;

import com.fasterxml.jackson.annotation.JsonView;
import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.achievement.UserAchievementRequestDto;
import com.royalrangers.model.Views;
import com.royalrangers.service.achievement.UserThreeYearAchievementService;
import com.royalrangers.utils.ResponseBuilder;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/achievements/userThreeYear")
public class UserThreeYearAchievementController {

    @Autowired
    private UserThreeYearAchievementService userThreeYearAchievementService;

    @JsonView(Views.Achievement.class)
    @GetMapping
    @ApiOperation(value = "Get a list of three-year achievements for current user")
    public ResponseResult getAllTreeYearAchievement() {
        try {
            return ResponseBuilder.success(userThreeYearAchievementService.findAllForUser());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed get all UserThreeYearAchievement");
        }
    }

    @PostMapping
    @ApiOperation(value = "Add the three-year achievement for current user")
    public ResponseResult addTreeYearAchievement(@RequestBody UserAchievementRequestDto params) {
        try {
            userThreeYearAchievementService.addUserThreeYearAchievement(params);
            return ResponseBuilder.success("Successfully added userThreeYearAchievement");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed add userThreeYearAchievement");
        }
    }

    @JsonView(Views.Achievement.class)
    @GetMapping("/{userAchievementId}")
    @ApiOperation(value = "Get three-year achievement info")
    public ResponseResult getUserThreeYearAchievementById(@PathVariable Long userAchievementId) {
        try {
            return ResponseBuilder.success(userThreeYearAchievementService.getUserThreeYearAchievementById(userAchievementId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed get userAchievement by id");
        }
    }

    @DeleteMapping("/{userAchievementId}")
    @ApiOperation(value = "Delete current user three-year achievement")
    public ResponseResult deleteUserThreeYearAchievement(@PathVariable Long userAchievementId) {
        try {
            userThreeYearAchievementService.deleteUserThreeYearAchievement(userAchievementId);
            return ResponseBuilder.success("UserAchievement was success delete");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed delete userAchievement");
        }
    }

    @PutMapping("/{userAchievementId}")
    @ApiOperation(value = "Update current user three-year achievement")
    public ResponseResult editUserThreeYearAchievement(@RequestBody UserAchievementRequestDto params, @PathVariable Long userAchievementId) {
        try {
            userThreeYearAchievementService.editUserThreeYearAchievement(params, userAchievementId);
            return ResponseBuilder.success("Successfully editing UserThreeYearAchievement");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed edit userAchievement");
        }
    }

}
