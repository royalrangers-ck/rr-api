package com.royalrangers.controller.achievement;

import com.fasterxml.jackson.annotation.JsonView;
import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.achievement.UserAchievementRequestDto;
import com.royalrangers.model.Views;
import com.royalrangers.service.achievement.UserQuarterAchievementService;
import com.royalrangers.utils.ResponseBuilder;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/achievements/userQuarter")
public class UserQuarterAchievementController {

    @Autowired
    private UserQuarterAchievementService userQuarterAchievementService;

    @JsonView(Views.Achievement.class)
    @GetMapping
    @ApiOperation(value = "Get a list of quarter-year achievements for current user")
    public ResponseResult getAllUserQuarterAchievement() {
        try {
            return ResponseBuilder.success(userQuarterAchievementService.findAllForUser());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed get all UserQuarterAchievement");
        }
    }

    @PostMapping
    @ApiOperation(value = "Add the quarter-year achievement for current user")
    public ResponseResult addUserQuarterAchievement(@RequestBody UserAchievementRequestDto params) {
        try {
            userQuarterAchievementService.addUserQuarterAchievement(params);
            return ResponseBuilder.success("Successfully added UserQuarterAchievement");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed add UserQuarterAchievement");
        }
    }

    @JsonView(Views.Achievement.class)
    @GetMapping("/{userAchievementId}")
    @ApiOperation(value = "Get user quarter-year achievement info")
    public ResponseResult getUserQuarterAchievementById(@PathVariable Long userAchievementId) {
        try {
            return ResponseBuilder.success(userQuarterAchievementService.getUserQuarterAchievementById(userAchievementId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed get UserQuarterAchievement by id");
        }
    }

    @DeleteMapping("/{userAchievementId}")
    @ApiOperation(value = "Delete user quarter-year achievement")
    public ResponseResult deleteUserQuarterAchievement(@PathVariable Long userAchievementId) {
        try {
            userQuarterAchievementService.deleteUserQuarterAchievement(userAchievementId);
            return ResponseBuilder.success("UserAchievement was success delete");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed delete UserQuarterAchievement");
        }
    }

    @PutMapping("/{userAchievementId}")
    @ApiOperation(value = "Update user quarter-year achievement")
    public ResponseResult editUserQuarterAchievement(@RequestBody UserAchievementRequestDto params, @PathVariable Long userAchievementId) {
        try {
            userQuarterAchievementService.editUserQuarterAchievement(params, userAchievementId);
            return ResponseBuilder.success("Successfully editing UserQuarterAchievement");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed edit userAchievement");
        }
    }
}
