package com.royalrangers.controller.achievement;

import com.royalrangers.dto.ResponseResult;
import com.royalrangers.service.achievement.PlatoonAchievementService;
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
        return platoonAchievementService.getMedalRewards();
    }

    @GetMapping("/star")
    public ResponseResult getStars() {
        return platoonAchievementService.getStarRewards();
    }

    @GetMapping("/lath")
    public ResponseResult getLaths() {
        return platoonAchievementService.getLathRewards();
    }

    @GetMapping("/trip")
    public ResponseResult getTrips() {
        return platoonAchievementService.getTripRewards();
    }

    @GetMapping("/camp")
    public ResponseResult getCamps() {
        return platoonAchievementService.getCampRewards();
    }

    @GetMapping("/twelve")
    public ResponseResult getTwelveYearAchievements() {
        return platoonAchievementService.getTwelveYearAchievements();
    }

    @GetMapping("/three")
    public ResponseResult getThreeYearAchievements() {
        return platoonAchievementService.getThreeYearAchievements();
    }

    @GetMapping("/year")
    public ResponseResult getYearAchievements() {
        return platoonAchievementService.getYearAchievements();
    }

    @GetMapping("/quarter")
    public ResponseResult getQuarterYearAchievements() {
        return platoonAchievementService.getQuarterYearAchievements();
    }
}
