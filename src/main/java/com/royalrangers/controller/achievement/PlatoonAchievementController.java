package com.royalrangers.controller.achievement;

import com.royalrangers.dto.ResponseResult;
import com.royalrangers.service.achievement.PlatoonAchievementService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/platoon/achievements")
public class PlatoonAchievementController {
    @Autowired
    PlatoonAchievementService platoonAchievementService;

    @GetMapping("/medal")
    public ResponseResult getMedals() {
        return ResponseBuilder.success(platoonAchievementService.findAllMedalRewards());
    }

    @GetMapping("/star")
    public ResponseResult getStars() {
        return ResponseBuilder.success(platoonAchievementService.findAllStarRewards());
    }

    @GetMapping("/lath")
    public ResponseResult getLaths() {
        return ResponseBuilder.success(platoonAchievementService.findAllLathRewards());
    }

    @GetMapping("/trip")
    public ResponseResult getTrips() {
        return ResponseBuilder.success(platoonAchievementService.findAllTripRewards());
    }

    @GetMapping("/camp")
    public ResponseResult getCamps() {
        return ResponseBuilder.success(platoonAchievementService.findAllCampRewards());
    }

    @GetMapping("/twelve")
    public ResponseResult getTwelveYearAchievements() {
        return ResponseBuilder.success(platoonAchievementService.findAllTwelveYearAchievements());
    }

    @GetMapping("/three")
    public ResponseResult getThreeYearAchievements() {
        return ResponseBuilder.success(platoonAchievementService.findAllThreeYearAchievements());
    }

    @GetMapping("/year")
    public ResponseResult getYearAchievements() {
        return ResponseBuilder.success(platoonAchievementService.findAllYearAchievements());
    }

    @GetMapping("/quarter")
    public ResponseResult getQuarterYearAchievements() {
        return ResponseBuilder.success(platoonAchievementService.findAllQuarterYearAchievements());
    }
}
