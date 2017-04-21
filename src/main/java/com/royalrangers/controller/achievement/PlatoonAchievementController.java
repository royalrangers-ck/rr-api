package com.royalrangers.controller.achievement;

import com.fasterxml.jackson.annotation.JsonView;
import com.royalrangers.dto.ResponseResult;
import com.royalrangers.model.Views;
import com.royalrangers.service.UserService;
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

    @Autowired
    UserService userService;

    @GetMapping("/approved/medal")
    public ResponseResult getMedals() {
        return platoonAchievementService.getMedalRewards();
    }

    @GetMapping("/approved/star")
    public ResponseResult getStars() {
        return platoonAchievementService.getStarRewards();
    }

    @GetMapping("/approved/lath")
    public ResponseResult getLaths() {
        return platoonAchievementService.getLathRewards();
    }

    @GetMapping("/approved/trip")
    public ResponseResult getTrips() {
        return platoonAchievementService.getTripRewards();
    }

    @GetMapping("/approved/camp")
    public ResponseResult getCamps() {
        return platoonAchievementService.getCampRewards();
    }

    @GetMapping("/approved/twelve")
    public ResponseResult getTwelveYearAchievements() {
        return platoonAchievementService.getTwelveYearAchievements();
    }

    @GetMapping("/approved/three")
    public ResponseResult getThreeYearAchievements() {
        return platoonAchievementService.getThreeYearAchievements();
    }

    @GetMapping("/approved/year")
    public ResponseResult getYearAchievements() {
        return platoonAchievementService.getYearAchievements();
    }

    @GetMapping("/approved/quarter")
    public ResponseResult getQuarterYearAchievements() {
        return platoonAchievementService.getQuarterYearAchievements();
    }
    @JsonView(Views.Profile.class)
    @GetMapping("/users")
    public ResponseResult getUsers() {
        return userService.getUsersByPlatoon();
    }

    @GetMapping("/inProgress/twelve")
    public ResponseResult getInProgressTwelveYearAchievements() {
        return platoonAchievementService.getInProgressTwelveYearAchievements();
    }

    @GetMapping("/inProgress/three")
    public ResponseResult getInProgressThreeYearAchievements() {
        return platoonAchievementService.getInProgressThreeYearAchievements();
    }

    @GetMapping("/inProgress/year")
    public ResponseResult getInProgressYearAchievements() {
        return platoonAchievementService.getInProgressYearAchievements();
    }

    @GetMapping("/inProgress/quarter")
    public ResponseResult getInProgressQuarterYearAchievements() {
        return platoonAchievementService.getInProgressQuarterYearAchievements();
    }

    @GetMapping("/inProgress/star")
    public ResponseResult getInProgressStars() {
        return platoonAchievementService.getInProgressStarRewards();
    }

    @GetMapping("/inProgress/medal")
    public ResponseResult getInProgressMedals() {
        return platoonAchievementService.getInProgressMedalRewards();
    }

    @GetMapping("/inProgress/lath")
    public ResponseResult getInProgressLaths() {
        return platoonAchievementService.getInProgressLathRewards();
    }

    @GetMapping("/inProgress/trip")
    public ResponseResult getInProgressTrips() {
        return platoonAchievementService.getInProgressTripRewards();
    }

    @GetMapping("/inProgress/camp")
    public ResponseResult getInProgressCamps() {
        return platoonAchievementService.getInProgressCampRewards();
    }
}
