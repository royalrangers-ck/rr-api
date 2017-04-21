package com.royalrangers.controller.achievement;

import com.fasterxml.jackson.annotation.JsonView;
import com.royalrangers.dto.ResponseResult;
import com.royalrangers.model.Views;
import com.royalrangers.service.UserService;
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

    @Autowired
    UserService userService;

    @GetMapping("/approved/medal")
    public ResponseResult getMedals() {
        return ResponseBuilder.success(platoonAchievementService.getMedalRewards());
    }

    @GetMapping("/approved/star")
    public ResponseResult getStars() {
        return ResponseBuilder.success(platoonAchievementService.getStarRewards());
    }

    @GetMapping("/approved/lath")
    public ResponseResult getLaths() {
        return ResponseBuilder.success(platoonAchievementService.getLathRewards());
    }

    @GetMapping("/approved/trip")
    public ResponseResult getTrips() {
        return ResponseBuilder.success(platoonAchievementService.getTripRewards());
    }

    @GetMapping("/approved/camp")
    public ResponseResult getCamps() {
        return ResponseBuilder.success(platoonAchievementService.getCampRewards());
    }

    @GetMapping("/approved/twelve")
    public ResponseResult getTwelveYearAchievements() {
        return ResponseBuilder.success(platoonAchievementService.getTwelveYearAchievements());
    }

    @GetMapping("/approved/three")
    public ResponseResult getThreeYearAchievements() {
        return ResponseBuilder.success(platoonAchievementService.getThreeYearAchievements());
    }

    @GetMapping("/approved/year")
    public ResponseResult getYearAchievements() {
        return ResponseBuilder.success(platoonAchievementService.getYearAchievements());
    }

    @GetMapping("/approved/quarter")
    public ResponseResult getQuarterYearAchievements() {
        return ResponseBuilder.success(platoonAchievementService.getQuarterYearAchievements());
    }

    @JsonView(Views.Profile.class)
    @GetMapping("/users")
    public ResponseResult getUsers() {
        return ResponseBuilder.success(userService.getUsersByPlatoon());
    }

    @GetMapping("/inProgress/twelve")
    public ResponseResult getInProgressTwelveYearAchievements() {
        return ResponseBuilder.success(platoonAchievementService.getInProgressTwelveYearAchievements());
    }

    @GetMapping("/inProgress/three")
    public ResponseResult getInProgressThreeYearAchievements() {
        return ResponseBuilder.success(platoonAchievementService.getInProgressThreeYearAchievements());
    }

    @GetMapping("/inProgress/year")
    public ResponseResult getInProgressYearAchievements() {
        return ResponseBuilder.success(platoonAchievementService.getInProgressYearAchievements());
    }

    @GetMapping("/inProgress/quarter")
    public ResponseResult getInProgressQuarterYearAchievements() {
        return ResponseBuilder.success(platoonAchievementService.getInProgressQuarterYearAchievements());
    }

    @GetMapping("/inProgress/star")
    public ResponseResult getInProgressStars() {
        return ResponseBuilder.success(platoonAchievementService.getInProgressStarRewards());
    }

    @GetMapping("/inProgress/medal")
    public ResponseResult getInProgressMedals() {
        return ResponseBuilder.success(platoonAchievementService.getInProgressMedalRewards());
    }

    @GetMapping("/inProgress/lath")
    public ResponseResult getInProgressLaths() {
        return ResponseBuilder.success(platoonAchievementService.getInProgressLathRewards());
    }

    @GetMapping("/inProgress/trip")
    public ResponseResult getInProgressTrips() {
        return ResponseBuilder.success(platoonAchievementService.getInProgressTripRewards());
    }

    @GetMapping("/inProgress/camp")
    public ResponseResult getInProgressCamps() {
        return ResponseBuilder.success(platoonAchievementService.getInProgressCampRewards());
    }
}
