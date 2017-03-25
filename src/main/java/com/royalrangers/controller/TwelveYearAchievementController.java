package com.royalrangers.controller;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.model.achievement.TwelveYearAchievement;
import com.royalrangers.service.TwelveYearAchievementService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TwelveYearAchievementController {

    @Autowired
    private TwelveYearAchievementService twelveYearAchievementService;

    @RequestMapping(value = "/achievements/twelveYear", method = RequestMethod.GET)
    public ResponseResult getAllTwelveYearAchievement() {
        try {
            return ResponseBuilder.success(twelveYearAchievementService.getAllTwelveYearAchievement());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get TwelveYearAchievement");
        }
    }

    @RequestMapping(value = "/achievements/twelveYear", method = RequestMethod.POST)
    public ResponseResult addTwelveYearAchievement(@RequestBody Map<String, Object> params) {
        try {
            twelveYearAchievementService.addTwelveYearAchievement(params);
            return ResponseBuilder.success("Successful addition of a TwelveYearAchievement");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed add TwelveYearAchievement");
        }
    }

    @RequestMapping(value = "/achievements/twelveYear/{twelveYearId}", method = RequestMethod.GET)
    public ResponseResult getTwelveYearAchievementById(@PathVariable Long twelveYearId) {
        try {
            return ResponseBuilder.success(twelveYearAchievementService.getTwelveYearAchievementById(twelveYearId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get TwelveYearAchievement by id");
        }
    }

    @RequestMapping(value = "/achievements/twelveYear/{twelveYearId}", method = RequestMethod.DELETE)
    public ResponseResult deleteTwelveYearAchievement(@PathVariable Long twelveYearId) {
        try {
            twelveYearAchievementService.deleteTwelveYearAchievement(twelveYearId);
            return ResponseBuilder.success("Successful delete TwelveYearAchievement");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed delete TwelveYearAchievement");
        }
    }

    @RequestMapping(value = "/achievements/twelveYear/{twelveYearId}", method = RequestMethod.PUT)
    public ResponseResult editTwelveYearAchievement(@RequestBody Map<String, Object> params, @PathVariable Long twelveYearId) {
        try {
            return ResponseBuilder.success(twelveYearAchievementService.editTwelveYearAchievement(params, twelveYearId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed delete TwelveYearAchievement");
        }
    }

}