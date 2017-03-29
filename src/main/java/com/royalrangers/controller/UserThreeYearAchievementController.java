package com.royalrangers.controller;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.service.UserThreeYearAchievementService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserThreeYearAchievementController {

    @Autowired
    private UserThreeYearAchievementService userThreeYearAchievementService;

    @RequestMapping(value = "/achievements/userThreeYear", method = RequestMethod.GET)
    public ResponseResult getAllTreeYearAchievement() {
        try {
            return ResponseBuilder.success(userThreeYearAchievementService.getAllUserThreeYearAchievements());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get all UserThreeYearAchievement");
        }
    }

    @RequestMapping(value = "/achievements/userThreeYear", method = RequestMethod.POST)
    public ResponseResult addTreeYearAchievement(@RequestBody Map<String, Object> params) {
        try {
            userThreeYearAchievementService.addUserThreeYearAchievement(params);
            return ResponseBuilder.success("successfully added userThreeYearAchievement");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed add userThreeYearAchievement");
        }
    }

    @RequestMapping(value = "/achievements/userThreeYear/{userAchievementId}", method = RequestMethod.GET)
    public ResponseResult getUserThreeYearAchievementById(@PathVariable Long userAchievementId) {
        try {
            return ResponseBuilder.success(userThreeYearAchievementService.getUserThreeYearAchievementById(userAchievementId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get userAchievement by id");
        }
    }

    @RequestMapping(value = "/achievements/userThreeYear/{userAchievementId}", method = RequestMethod.DELETE)
    public ResponseResult deleteUserThreeYearAchievement(@PathVariable Long userAchievementId) {
        try {
            userThreeYearAchievementService.deleteUserThreeYearAchievement(userAchievementId);
            return ResponseBuilder.success("UserAchievement was success delete");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed delete userAchievement");
        }
    }

    @RequestMapping(value = "/achievements/userThreeYear/{userAchievementId}", method = RequestMethod.PUT)
    public ResponseResult editUserThreeYearAchievement(@RequestBody Map<String, Object> params, @PathVariable Long userAchievementId) {
        try {
            userThreeYearAchievementService.editUserThreeYearAchievement(params, userAchievementId);
            return ResponseBuilder.success("successfully editing UserThreeYearAchievement");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed edit userAchievement");
        }
    }

}
