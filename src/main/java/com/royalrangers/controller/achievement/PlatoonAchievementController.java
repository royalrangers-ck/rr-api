package com.royalrangers.controller.achievement;

import com.fasterxml.jackson.annotation.JsonView;
import com.royalrangers.dto.ResponseResult;
import com.royalrangers.model.Views;
import com.royalrangers.service.UserService;
import com.royalrangers.service.achievement.PlatoonAchievementService;
import com.royalrangers.utils.ResponseBuilder;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/platoon/achievements")
public class PlatoonAchievementController {

    @Autowired
    private PlatoonAchievementService platoonAchievementService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Get all platoon approved medal rewards")
    @GetMapping("/approved/medal")
    public ResponseResult getMedals() {
        return ResponseBuilder.success(platoonAchievementService.getMedalRewards());
    }

    @ApiOperation(value = "Get all platoon approved star rewards")
    @GetMapping("/approved/star")
    public ResponseResult getStars() {
        return ResponseBuilder.success(platoonAchievementService.getStarRewards());
    }

    @ApiOperation(value = "Get all platoon approved lath rewards")
    @GetMapping("/approved/lath")
    public ResponseResult getLaths() {
        return ResponseBuilder.success(platoonAchievementService.getLathRewards());
    }

    @ApiOperation(value = "Get all platoon approved trip rewards")
    @GetMapping("/approved/trip")
    public ResponseResult getTrips() {
        return ResponseBuilder.success(platoonAchievementService.getTripRewards());
    }

    @ApiOperation(value = "Get all platoon approved camp rewards")
    @GetMapping("/approved/camp")
    public ResponseResult getCamps() {
        return ResponseBuilder.success(platoonAchievementService.getCampRewards());
    }

    @ApiOperation(value = "Get all platoon approved twelve year achievements")
    @GetMapping("/approved/twelve")
    public ResponseResult getTwelveYearAchievements() {
        return ResponseBuilder.success(platoonAchievementService.getTwelveYearAchievements());
    }

    @ApiOperation(value = "Get all platoon approved three year achievements")
    @GetMapping("/approved/three")
    public ResponseResult getThreeYearAchievements() {
        return ResponseBuilder.success(platoonAchievementService.getThreeYearAchievements());
    }

    @ApiOperation(value = "Get all platoon approved year achievements")
    @GetMapping("/approved/year")
    public ResponseResult getYearAchievements() {
        return ResponseBuilder.success(platoonAchievementService.getYearAchievements());
    }

    @ApiOperation(value = "Get all platoon approved quarter year achievements")
    @GetMapping("/approved/quarter")
    public ResponseResult getQuarterYearAchievements() {
        return ResponseBuilder.success(platoonAchievementService.getQuarterYearAchievements());
    }

    @ApiOperation(value = "Get all platoon users")
    @JsonView(Views.Profile.class)
    @GetMapping("/users")
    public ResponseResult getUsers() {
        return ResponseBuilder.success(userService.getUsersByPlatoon());
    }

    @ApiOperation(value = "Get all platoon in progress twelve year achievements")
    @GetMapping("/inProgress/twelve")
    public ResponseResult getInProgressTwelveYearAchievements() {
        return ResponseBuilder.success(platoonAchievementService.getInProgressTwelveYearAchievements());
    }

    @ApiOperation(value = "Get all platoon three year achievements that are in progress")
    @GetMapping("/inProgress/three")
    public ResponseResult getInProgressThreeYearAchievements() {
        return ResponseBuilder.success(platoonAchievementService.getInProgressThreeYearAchievements());
    }

    @ApiOperation(value = "Get all platoon year achievements that are in progress")
    @GetMapping("/inProgress/year")
    public ResponseResult getInProgressYearAchievements() {
        return ResponseBuilder.success(platoonAchievementService.getInProgressYearAchievements());
    }

    @ApiOperation(value = "Get all platoon quarter year achievements that are in progress")
    @GetMapping("/inProgress/quarter")
    public ResponseResult getInProgressQuarterYearAchievements() {
        return ResponseBuilder.success(platoonAchievementService.getInProgressQuarterYearAchievements());
    }

    @ApiOperation(value = "Get all platoon star rewards that are in progress")
    @GetMapping("/inProgress/star")
    public ResponseResult getInProgressStars() {
        return ResponseBuilder.success(platoonAchievementService.getInProgressStarRewards());
    }

    @ApiOperation(value = "Get all platoon medal rewards that are in progress")
    @GetMapping("/inProgress/medal")
    public ResponseResult getInProgressMedals() {
        return ResponseBuilder.success(platoonAchievementService.getInProgressMedalRewards());
    }

    @ApiOperation(value = "Get all platoon lath rewards that are in progress")
    @GetMapping("/inProgress/lath")
    public ResponseResult getInProgressLaths() {
        return ResponseBuilder.success(platoonAchievementService.getInProgressLathRewards());
    }

    @ApiOperation(value = "Get all platoon trip rewards that are in progress")
    @GetMapping("/inProgress/trip")
    public ResponseResult getInProgressTrips() {
        return ResponseBuilder.success(platoonAchievementService.getInProgressTripRewards());
    }

    @ApiOperation(value = "Get all platoon camp rewards that are in progress")
    @GetMapping("/inProgress/camp")
    public ResponseResult getInProgressCamps() {
        return ResponseBuilder.success(platoonAchievementService.getInProgressCampRewards());
    }
}
