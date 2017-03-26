package com.royalrangers.controller;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.service.ThreeYearAchievementService;
import com.royalrangers.service.TwelveYearAchievementService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ThreeYearAchievementController {

    @Autowired
    private ThreeYearAchievementService threeYearAchievementService;

    @Autowired
    private TwelveYearAchievementService twelveYearAchievementService;

    @RequestMapping(value = "/achievements/threeYear", method = RequestMethod.GET)
    public ResponseResult getAllThreeYearAchievement() {
        try {
            return ResponseBuilder.success(threeYearAchievementService.getAllThreeYearAchievement());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get all threeYearAchievements");
        }
    }

    @RequestMapping(value = "/achievements/threeYear", method = RequestMethod.POST)
    public ResponseResult addThreeYearAchievement(@RequestBody Map<String, Object> params) {
        try {
            threeYearAchievementService.addThreeYearAchievement(params);
            return ResponseBuilder.success("Successful addition of a threeYearAchievements");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed add threeYearAchievements");
        }
    }

    @RequestMapping(value = "/achievements/threeYear/{threeYearId}", method = RequestMethod.GET)
    public ResponseResult getThreeYearAchievementById(@PathVariable Long threeYearId) {
        try {
            return ResponseBuilder.success(threeYearAchievementService.getThreeYearAchievementById(threeYearId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get threeYearAchievements by id");
        }
    }

    @RequestMapping(value = "/achievements/threeYear/{threeYearId}", method = RequestMethod.DELETE)
    public ResponseResult deleteThreeYearAchievement(@PathVariable Long threeYearId) {
        try {
            threeYearAchievementService.deleteThreeYearAchievement(threeYearId);
            return ResponseBuilder.success("Successful delete threeYearAchievements");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed delete threeYearAchievements");
        }
    }

    @RequestMapping(value = "/achievements/threeYear/{threeYearId}", method = RequestMethod.PUT)
    public ResponseResult editThreeYearAchievement(@RequestBody Map<String, Object> params, @PathVariable Long threeYearId) {
        try {
            return ResponseBuilder.success(threeYearAchievementService.editThreeYearAchievement(params, threeYearId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed edit threeYearAchievements");
        }
    }

}