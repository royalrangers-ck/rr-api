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
    private PlatoonAchievementService platoonAchievementService;

    @Autowired
    private UserService userService;

    @GetMapping("/approved/star")
    public ResponseResult getApprovedStarRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getApprovedStarRewards());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get star rewards");
        }
    }

    @GetMapping("/approved/medal")
    public ResponseResult getApprovedMedalRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getApprovedMedalRewards());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get medal rewards");
        }
    }

    @GetMapping("/approved/lath")
    public ResponseResult getApprovedLathRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getApprovedLathRewards());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get lath rewards");
        }
    }

    @GetMapping("/approved/trip")
    public ResponseResult getApprovedTripRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getApprovedTripRewards());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get trip rewards");
        }
    }

    @GetMapping("/approved/camp")
    public ResponseResult getApprovedCampRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getApprovedCampRewards());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get camp rewards");
        }
    }

    @GetMapping("/approved/twelve")
    public ResponseResult getApprovedTwelveYearAchievements() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getApprovedTwelveYearAchievements());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get twelve year achievements");
        }
    }

    @GetMapping("/approved/three")
    public ResponseResult getApprovedThreeYearAchievements() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getApprovedThreeYearAchievements());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get three year achievements");
        }
    }

    @GetMapping("/approved/year")
    public ResponseResult getApprovedYearAchievements() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getApprovedYearAchievements());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get year achievements");
        }
    }

    @GetMapping("/approved/quarter")
    public ResponseResult getApprovedQuarterAchievements() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getApprovedQuarterAchievements());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get quarter achievements");
        }
    }

    @JsonView(Views.Profile.class)
    @GetMapping("/users")
    public ResponseResult getUsers() {
        try {
            return ResponseBuilder.success(userService.getUsersByPlatoon());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get users for platoon");
        }
    }

    @GetMapping("/inProgress/twelve")
    public ResponseResult getInProgressTwelveYearAchievements() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getInProgressTwelveYearAchievements());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get twelve year achievements");
        }
    }

    @GetMapping("/inProgress/three")
    public ResponseResult getInProgressThreeYearAchievements() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getInProgressThreeYearAchievements());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get three year achievements");
        }
    }

    @GetMapping("/inProgress/year")
    public ResponseResult getInProgressYearAchievements() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getInProgressYearAchievements());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get year achievements");
        }
    }

    @GetMapping("/inProgress/quarter")
    public ResponseResult getInProgressQuarterAchievements() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getInProgressQuarterAchievements());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get quarter achievements");
        }
    }

    @GetMapping("/inProgress/star")
    public ResponseResult getInProgressStarRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getInProgressStarRewards());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get star rewards");
        }
    }

    @GetMapping("/inProgress/medal")
    public ResponseResult getInProgressMedalRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getInProgressMedalRewards());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get medal rewards");
        }
    }

    @GetMapping("/inProgress/lath")
    public ResponseResult getInProgressLathRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getInProgressLathRewards());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get lath rewards");
        }
    }

    @GetMapping("/inProgress/trip")
    public ResponseResult getInProgressTripRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getInProgressTripRewards());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get trip rewards");
        }
    }

    @GetMapping("/inProgress/camp")
    public ResponseResult getInProgressCampRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getInProgressCampRewards());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get camp rewards");
        }
    }
}
