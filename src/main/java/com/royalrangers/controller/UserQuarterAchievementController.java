package com.royalrangers.controller;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.service.UserQuarterAchievementService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserQuarterAchievementController {

    @Autowired
    private UserQuarterAchievementService userQuarterAchievementService;

    @RequestMapping(value = "/achievements/userQuarter", method = RequestMethod.GET)
    public ResponseResult getAllUserQuarterAchievement() {
        try {
            return ResponseBuilder.success(userQuarterAchievementService.getAllUserQuarterAchievements());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get all UserQuarterAchievement");
        }
    }

    @RequestMapping(value = "/achievements/userQuarter", method = RequestMethod.POST)
    public ResponseResult addUserQuarterAchievement(@RequestBody Map<String, Object> params) {
        try {
            userQuarterAchievementService.addUserQuarterAchievement(params);
            return ResponseBuilder.success("successfully added UserQuarterAchievement");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed add UserQuarterAchievement");
        }
    }

    @RequestMapping(value = "/achievements/userQuarter/{userAchievementId}", method = RequestMethod.GET)
    public ResponseResult getUserQuarterAchievementById(@PathVariable Long userAchievementId) {
        try {
            return ResponseBuilder.success(userQuarterAchievementService.getUserQuarterAchievementById(userAchievementId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get userAchievement by id");
        }
    }

    @RequestMapping(value = "/achievements/userQuarter/{userAchievementId}", method = RequestMethod.DELETE)
    public ResponseResult deleteUserQuarterAchievement(@PathVariable Long userAchievementId) {
        try {
            userQuarterAchievementService.deleteUserQuarterAchievement(userAchievementId);
            return ResponseBuilder.success("UserAchievement was success delete");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed delete userAchievement");
        }
    }

    @RequestMapping(value = "/achievements/userQuarter/{userAchievementId}", method = RequestMethod.PUT)
    public ResponseResult editUserQuarterAchievement(@RequestBody Map<String, Object> params, @PathVariable Long userAchievementId) {
        try {
            return ResponseBuilder.success(userQuarterAchievementService.editUserQuarterAchievement(params, userAchievementId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed edit userAchievement");
        }
    }
}
