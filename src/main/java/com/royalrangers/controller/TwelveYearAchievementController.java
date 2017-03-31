package com.royalrangers.controller;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.service.TwelveYearAchievementService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/achievements/twelveYear")
public class TwelveYearAchievementController {

    @Autowired
    private TwelveYearAchievementService twelveYearAchievementService;

    @GetMapping
    public ResponseResult getAllTwelveYearAchievement() {
        try {
            return ResponseBuilder.success(twelveYearAchievementService.getAllTwelveYearAchievement());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get TwelveYearAchievement");
        }
    }

    @PostMapping
    public ResponseResult addTwelveYearAchievement(@RequestBody Map<String, Object> params) {
        try {
            twelveYearAchievementService.addTwelveYearAchievement(params);
            return ResponseBuilder.success("Successful addition of a TwelveYearAchievement");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed add TwelveYearAchievement");
        }
    }

    @GetMapping("/{twelveYearId}")
    public ResponseResult getTwelveYearAchievementById(@PathVariable Long twelveYearId) {
        try {
            return ResponseBuilder.success(twelveYearAchievementService.getTwelveYearAchievementById(twelveYearId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get TwelveYearAchievement by id");
        }
    }

    @DeleteMapping("/{twelveYearId}")
    public ResponseResult deleteTwelveYearAchievement(@PathVariable Long twelveYearId) {
        try {
            twelveYearAchievementService.deleteTwelveYearAchievement(twelveYearId);
            return ResponseBuilder.success("Successful delete TwelveYearAchievement");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed delete TwelveYearAchievement");
        }
    }

    @PutMapping("/{twelveYearId}")
    public ResponseResult editTwelveYearAchievement(@RequestBody Map<String, Object> params, @PathVariable Long twelveYearId) {
        try {
            return ResponseBuilder.success(twelveYearAchievementService.editTwelveYearAchievement(params, twelveYearId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed delete TwelveYearAchievement");
        }
    }
}