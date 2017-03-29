package com.royalrangers.controller;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.service.YearAchievementService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/achievements/year")
public class YearAchievementController {

    @Autowired
    private YearAchievementService yearAchievementService;

    @GetMapping
    public ResponseResult getAllYearAchievement() {
        try {
            return ResponseBuilder.success(yearAchievementService.getAllYearAchievement());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get all yearAchievements");
        }
    }

    @PostMapping
    public ResponseResult addYearAchievement(@RequestBody Map<String, Object> params) {
        try {
            yearAchievementService.addYearAchievement(params);
            return ResponseBuilder.success("Successful addition of a yearAchievements");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed add yearAchievements");
        }
    }

    @GetMapping("/{yearId}")
    public ResponseResult getYearAchievementById(@PathVariable Long yearId) {
        try {
            return ResponseBuilder.success(yearAchievementService.getYearAchievementById(yearId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get yearAchievements by id");
        }
    }

    @DeleteMapping("/{yearId}")
    public ResponseResult deleteYearAchievement(@PathVariable Long yearId) {
        try {
            yearAchievementService.deleteYearAchievement(yearId);
            return ResponseBuilder.success("Successful delete yearAchievements");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed delete yearAchievements");
        }
    }

    @PutMapping("/{yearId}")
    public ResponseResult editYearAchievement(@RequestBody Map<String, Object> params, @PathVariable Long yearId) {
        try {
            return ResponseBuilder.success(yearAchievementService.editYearAchievement(params, yearId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed edit yearAchievements");
        }
    }
}