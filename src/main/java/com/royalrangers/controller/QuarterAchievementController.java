package com.royalrangers.controller;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.model.achievement.QuarterAchievement;
import com.royalrangers.service.QuarterAchievementService;
import com.royalrangers.service.YearAchievementService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class QuarterAchievementController {

    @Autowired
    private QuarterAchievementService quarterAchievementService;

    @Autowired
    private YearAchievementService yearAchievementService;

    @RequestMapping(value = "/achievements/quarter", method = RequestMethod.GET)
    public ResponseResult getAllQuarterAchievement() {
        try {
            return ResponseBuilder.success(quarterAchievementService.getAllQuarterAchievement());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed added QuarterAchievement");
        }
    }

    @RequestMapping(value = "/achievements/quarter", method = RequestMethod.POST)
    public ResponseResult addQuarterAchievement(@RequestBody Map<String, Object> params) {
        try {
            quarterAchievementService.addQuarterAchievement(params);
            return ResponseBuilder.success("Adding QuarterAchievement was a success");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed added QuarterAchievement");
        }
    }

    @RequestMapping(value = "/achievements/quarter/{quarterId}", method = RequestMethod.GET)
    public ResponseResult getQuarterAchievementById(@PathVariable Long quarterId) {
        try {
            return ResponseBuilder.success(quarterAchievementService.getQuarterAchievementById(quarterId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get QuarterAchievement by id");
        }
    }

    @RequestMapping(value = "/achievements/quarter/{quarterId}", method = RequestMethod.DELETE)
    public ResponseResult deleteQuarterAchievement(@PathVariable Long quarterId) {
        try {
            quarterAchievementService.deleteQuarterAchievement(quarterId);
            return ResponseBuilder.success("Delete QuarterAchievement was a success");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed deleted QuarterAchievement");
        }
    }

    @RequestMapping(value = "/achievements/quarter/{quarterId}", method = RequestMethod.PUT)
    public ResponseResult editQuarterAchievement(@RequestBody Map<String, Object> params, @PathVariable Long quarterId) {
        try {
            return ResponseBuilder.success(quarterAchievementService.editQuarterAchievement(params, quarterId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed edit QuarterAchievement");
        }
    }

}