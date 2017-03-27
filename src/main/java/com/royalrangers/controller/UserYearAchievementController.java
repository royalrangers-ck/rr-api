package com.royalrangers.controller;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.service.UserYearAchievementService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserYearAchievementController {

    @Autowired
    private UserYearAchievementService userYearAchievementService;

    @RequestMapping(value = "/achievements/userYear", method = RequestMethod.GET)
    public ResponseResult getAllUserYearAchievement() {
        try {
            return ResponseBuilder.success(userYearAchievementService.getAllUserYearAchievements());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get all user achievement");
        }
    }

    @RequestMapping(value = "/achievements/userYear", method = RequestMethod.POST)
    public ResponseResult addUserYearAchievement(@RequestBody Map<String, Object> params) {
        try {
            userYearAchievementService.addUserYearAchievement(params);
            return ResponseBuilder.success("successfully added userYearAchievement");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed add userAchievement");
        }
    }

    @RequestMapping(value = "/achievements/userYear/{userAchievementId}", method = RequestMethod.GET)
    public ResponseResult getUserYearAchievementById(@PathVariable Long userAchievementId) {
        try {
            return ResponseBuilder.success(userYearAchievementService.getUserYearAchievementById(userAchievementId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get userAchievement by id");
        }
    }

    @RequestMapping(value = "/achievements/userYear/{userAchievementId}", method = RequestMethod.DELETE)
    public ResponseResult deleteUserYearAchievement(@PathVariable Long userAchievementId) {
        try {
            userYearAchievementService.deleteUserYearAchievement(userAchievementId);
            return ResponseBuilder.success("UserAchievement was success delete");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed delete userAchievement");
        }
    }

    @RequestMapping(value = "/achievements/userYear/{userAchievementId}", method = RequestMethod.PUT)
    public ResponseResult editUserYearAchievement(@RequestBody Map<String, Object> params, @PathVariable Long userAchievementId) {
        try {
            return ResponseBuilder.success(userYearAchievementService.editUserYearAchievement(params, userAchievementId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed edit userAchievement");
        }
    }
}
