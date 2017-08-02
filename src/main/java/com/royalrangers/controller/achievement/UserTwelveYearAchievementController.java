package com.royalrangers.controller.achievement;

import com.fasterxml.jackson.annotation.JsonView;
import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.achievement.UserAchievementRequestDto;
import com.royalrangers.model.Views;
import com.royalrangers.service.achievement.UserTwelveYearAchievementService;
import com.royalrangers.utils.ResponseBuilder;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/achievements/userTwelveYear")
public class UserTwelveYearAchievementController {

    @Autowired
    private UserTwelveYearAchievementService userTwelveYearAchievementService;

    @JsonView(Views.Achievement.class)
    @GetMapping
    @ApiOperation(value = "Get a list of twelve-year achievements for current user")
    public ResponseResult findByUserId() {
        try {
            return ResponseBuilder.success(userTwelveYearAchievementService.findAllForUser());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed get all user achievement");
        }
    }

    @PostMapping
    @ApiOperation(value = "Add the twelve-year achievement for current user")
    public ResponseResult addUserTwelveYearAchievement(@RequestBody UserAchievementRequestDto params) {
        try {
            log.info("Add UserTwelveYearAchievement " + params.getId());
            return ResponseBuilder.success(userTwelveYearAchievementService.addUserTwelveYearAchievement(params));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed add userAchievement");
        }
    }

    @JsonView(Views.Achievement.class)
    @GetMapping("/{userAchievementId}")
    @ApiOperation(value = "Get twelve-year achievement info")
    public ResponseResult getUserTwelveYearAchievementById(@PathVariable Long userAchievementId) {
        try {
            return ResponseBuilder.success(userTwelveYearAchievementService.getUserTwelveYearAchievementById(userAchievementId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed get userAchievement by id");
        }
    }

    @DeleteMapping("/{userAchievementId}")
    @ApiOperation(value = "Delete current user twelve-year achievement")
    public ResponseResult deleteUserTwelveYearAchievement(@PathVariable Long userAchievementId) {
        try {
            userTwelveYearAchievementService.delete(userAchievementId);
            return ResponseBuilder.success("UserAchievement was success delete");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed delete userAchievement");
        }
    }

    @PutMapping("/{userAchievementId}")
    @ApiOperation(value = "Update current user twelve-year achievement")
    public ResponseResult editUserTwelveYearAchievement(@RequestBody UserAchievementRequestDto params, @PathVariable Long userAchievementId) {
        try {
            return ResponseBuilder.success(userTwelveYearAchievementService.editUserTwelveYearAchievement(params, userAchievementId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed edit userAchievement");
        }
    }
}
