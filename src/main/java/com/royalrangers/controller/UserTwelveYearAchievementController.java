package com.royalrangers.controller;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.service.UserTwelveYearAchievementService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserTwelveYearAchievementController {

    @Autowired
    private UserTwelveYearAchievementService userTwelveYearAchievementService;

    @RequestMapping(value = "/achievements/userTwelveYear", method = RequestMethod.GET)
    public ResponseResult getAllUserTwelveYearAchievement() {
        try {
            return ResponseBuilder.success(userTwelveYearAchievementService.getAllUserWithAchievement());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get all user achievement");
        }
    }

    @RequestMapping(value = "/achievements/userTwelveYear", method = RequestMethod.POST)
    public ResponseResult addUserTwelveYearAchievement(@RequestBody Map<String, Object> params) {
        try {
            userTwelveYearAchievementService.addUserTwelveYearAchievement(params);
            return ResponseBuilder.success("successfully added userTwelveYearAchievement");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed add userAchievement");
        }
    }

    @RequestMapping(value = "/achievements/userTwelveYear/{userAchievementId}", method = RequestMethod.GET)
    public ResponseResult getUserTwelveYearAchievementById(@PathVariable Long userAchievementId) {
        try {
            return ResponseBuilder.success(userTwelveYearAchievementService.getUserTwelveYearAchievementById(userAchievementId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get userAchievement by id");
        }
    }

    @RequestMapping(value = "/achievements/userTwelveYear/{userAchievementId}", method = RequestMethod.DELETE)
    public ResponseResult deleteUserTwelveYearAchievement(@PathVariable Long userAchievementId) {
        try {
            userTwelveYearAchievementService.delete(userAchievementId);
            return ResponseBuilder.success("UserAchievement was success delete");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed delete userAchievement");
        }
    }

    @RequestMapping(value = "/achievements/userTwelveYear/{userAchievementId}", method = RequestMethod.PUT)
    public ResponseResult editUserTwelveYearAchievement(@RequestBody Map<String, Object> params, @PathVariable Long userAchievementId) {
        try {
            return ResponseBuilder.success(userTwelveYearAchievementService.editTwelveYearAchievement(params, userAchievementId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed edit userAchievement");
        }
    }

}
