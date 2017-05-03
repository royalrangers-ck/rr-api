package com.royalrangers.controller.achievement;

import com.fasterxml.jackson.annotation.JsonView;
import com.royalrangers.dto.ResponseResult;
import com.royalrangers.model.Views;
import com.royalrangers.service.UserService;
import com.royalrangers.service.achievement.PlatoonAchievementService;
import com.royalrangers.utils.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/platoon/achievements")
public class PlatoonAchievementController {

    @Autowired
    private PlatoonAchievementService platoonAchievementService;

    @Autowired
    private UserService userService;

    @GetMapping("/approved/star")
    @ApiOperation(value = "Get list of all platoon approved star rewards")
    public ResponseResult getApprovedStarRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getApprovedStarRewards());
        } catch (Exception ex) {
            log.error("Failed get all approved star rewards for current platoon", ex);
            return ResponseBuilder.fail("Failed get all approved star rewards for current platoon");
        }
    }

    @GetMapping("/approved/medal")
    @ApiOperation(value = "Get all platoon approved medal rewards")
    public ResponseResult getApprovedMedalRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getApprovedMedalRewards());
        } catch (Exception ex) {
            log.error("Failed get all approved medal rewards for current platoon", ex);
            return ResponseBuilder.fail("Failed get all approved medal rewards for current platoon");
        }
    }

    @GetMapping("/approved/lath")
    @ApiOperation(value = "Get all platoon approved lath rewards")
    public ResponseResult getApprovedLathRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getApprovedLathRewards());
        } catch (Exception ex) {
            log.error("Failed get all approved lath rewards for current platoon", ex);
            return ResponseBuilder.fail("Failed get all approved lath rewards for current platoon");
        }
    }

    @GetMapping("/approved/trip")
    @ApiOperation(value = "Get all platoon approved trip rewards")
    public ResponseResult getApprovedTripRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getApprovedTripRewards());
        } catch (Exception ex) {
            log.error("Failed get all approved trip rewards for current platoon", ex);
            return ResponseBuilder.fail("Failed get all approved trip rewards for current platoon");
        }
    }

    @GetMapping("/approved/camp")
    @ApiOperation(value = "Get all platoon approved camp rewards")
    public ResponseResult getApprovedCampRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getApprovedCampRewards());
        } catch (Exception ex) {
            log.error("Failed get all approved camp rewards for current platoon", ex);
            return ResponseBuilder.fail("Failed get all approved camp rewards for current platoon");
        }
    }

    @GetMapping("/approved/twelve")
    @ApiOperation(value = "Get all platoon approved twelve-year achievements")
    public ResponseResult getApprovedTwelveYearAchievements() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getApprovedTwelveYearAchievements());
        } catch (Exception ex) {
            log.error("Failed get all approved twelve year achievements for current platoon", ex);
            return ResponseBuilder.fail("Failed get all approved twelve year achievements for current platoon");
        }
    }

    @GetMapping("/approved/three")
    @ApiOperation(value = "Get all platoon approved three-year achievements")
    public ResponseResult getApprovedThreeYearAchievements() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getApprovedThreeYearAchievements());
        } catch (Exception ex) {
            log.error("Failed get all approved three year achievements for current platoon", ex);
            return ResponseBuilder.fail("Failed get all approved three year achievements for current platoon");
        }
    }

    @GetMapping("/approved/year")
    @ApiOperation(value = "Get all platoon approved year achievements")
    public ResponseResult getApprovedYearAchievements() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getApprovedYearAchievements());
        } catch (Exception ex) {
            log.error("Failed get all approved year achievements for current platoon", ex);
            return ResponseBuilder.fail("Failed get all approved year achievements for current platoon");
        }
    }

    @GetMapping("/approved/quarter")
    @ApiOperation(value = "Get all platoon approved quarter-year achievements")
    public ResponseResult getApprovedQuarterAchievements() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getApprovedQuarterAchievements());
        } catch (Exception ex) {
            log.error("Failed get all approved quarter achievements for current platoon", ex);
            return ResponseBuilder.fail("Failed get all approved quarter achievements for current platoon");
        }
    }


    @JsonView(Views.Profile.class)
    @GetMapping("/users")
    @ApiOperation(value = "Get all platoon users")
    public ResponseResult getUsers() {
        try {
            return ResponseBuilder.success(userService.getUsersByPlatoon());
        } catch (Exception ex) {
            log.error("Failed all get users for current platoon", ex);
            return ResponseBuilder.fail("Failed get all users for current platoon");
        }
    }

    @GetMapping("/inProgress/twelve")
    @ApiOperation(value = "Get all platoon in progress twelve-year achievements")
    public ResponseResult getInProgressTwelveYearAchievements() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getInProgressTwelveYearAchievements());
        } catch (Exception ex) {
            log.error("Failed get all in progress twelve year achievements for current platoon", ex);
            return ResponseBuilder.fail("Failed get all in progress twelve year achievements for current platoon");
        }
    }

    @GetMapping("/inProgress/three")
    @ApiOperation(value = "Get all platoon three-year achievements that are in progress")
    public ResponseResult getInProgressThreeYearAchievements() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getInProgressThreeYearAchievements());
        } catch (Exception ex) {
            log.error("Failed get all in progress three year achievements for current platoon", ex);
            return ResponseBuilder.fail("Failed get all in progress three year achievements for current platoon");
        }
    }

    @GetMapping("/inProgress/year")
    @ApiOperation(value = "Get all platoon year achievements that are in progress")
    public ResponseResult getInProgressYearAchievements() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getInProgressYearAchievements());
        } catch (Exception ex) {
            log.error("Failed get all in progress year achievements for current platoon", ex);
            return ResponseBuilder.fail("Failed get all in progress year achievements for current platoon");
        }
    }

    @GetMapping("/inProgress/quarter")
    @ApiOperation(value = "Get all platoon quarter-year achievements that are in progress")
    public ResponseResult getInProgressQuarterAchievements() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getInProgressQuarterAchievements());
        } catch (Exception ex) {
            log.error("Failed get all in progress quarter achievements for current platoon", ex);
            return ResponseBuilder.fail("Failed get all in progress quarter achievements for current platoon");
        }
    }

    @GetMapping("/inProgress/star")
    @ApiOperation(value = "Get all platoon star rewards that are in progress")
    public ResponseResult getInProgressStarRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getInProgressStarRewards());
        } catch (Exception ex) {
            log.error("Failed get all in progress star rewards for current platoon", ex);
            return ResponseBuilder.fail("Failed get all in progress star rewards for current platoon");
        }
    }

    @GetMapping("/inProgress/medal")
    @ApiOperation(value = "Get all platoon medal rewards that are in progress")
    public ResponseResult getInProgressMedalRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getInProgressMedalRewards());
        } catch (Exception ex) {
            log.error("Failed get all in progress medal rewards for current platoon", ex);
            return ResponseBuilder.fail("Failed get all in progress medal rewards for current platoon");
        }
    }

    @GetMapping("/inProgress/lath")
    @ApiOperation(value = "Get all platoon lath rewards that are in progress")
    public ResponseResult getInProgressLathRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getInProgressLathRewards());
        } catch (Exception ex) {
            log.error("Failed get all in progress lath rewards for current platoon", ex);
            return ResponseBuilder.fail("Failed get all in progress lath rewards for current platoon");
        }
    }

    @GetMapping("/inProgress/trip")
    @ApiOperation(value = "Get all platoon trip rewards that are in progress")
    public ResponseResult getInProgressTripRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getInProgressTripRewards());
        } catch (Exception ex) {
            log.error("Failed get all in progress  trip rewards for current platoon", ex);
            return ResponseBuilder.fail("Failed get all in progress trip rewards for current platoon");
        }
    }

    @GetMapping("/inProgress/camp")
    @ApiOperation(value = "Get all platoon camp rewards that are in progress")
    public ResponseResult getInProgressCampRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getInProgressCampRewards());
        } catch (Exception ex) {
            log.error("Failed get camp all in progress rewards for current platoon", ex);
            return ResponseBuilder.fail("Failed get all in progress camp rewards for current platoon");
        }
    }
}
