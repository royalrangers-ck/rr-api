package com.royalrangers.controller.achievement;

import com.fasterxml.jackson.annotation.JsonView;
import com.royalrangers.dto.ResponseResult;
import com.royalrangers.model.Views;
import com.royalrangers.service.UserService;
import com.royalrangers.service.achievement.PlatoonAchievementService;
import com.royalrangers.utils.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseResult getApprovedStarRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getApprovedStarRewards());
        } catch (Exception ex) {
            log.debug("Failed get all approved star rewards for current platoon", ex);
            return ResponseBuilder.fail("Failed get all approved star rewards for current platoon");
        }
    }

    @GetMapping("/approved/medal")
    public ResponseResult getApprovedMedalRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getApprovedMedalRewards());
        } catch (Exception ex) {
            log.debug("Failed get all approved medal rewards for current platoon", ex);
            return ResponseBuilder.fail("Failed get all approved medal rewards for current platoon");
        }
    }

    @GetMapping("/approved/lath")
    public ResponseResult getApprovedLathRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getApprovedLathRewards());
        } catch (Exception ex) {
            log.debug("Failed get all approved lath rewards for current platoon", ex);
            return ResponseBuilder.fail("Failed get all approved lath rewards for current platoon");
        }
    }

    @GetMapping("/approved/trip")
    public ResponseResult getApprovedTripRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getApprovedTripRewards());
        } catch (Exception ex) {
            log.debug("Failed get all approved trip rewards for current platoon", ex);
            return ResponseBuilder.fail("Failed get all approved trip rewards for current platoon");
        }
    }

    @GetMapping("/approved/camp")
    public ResponseResult getApprovedCampRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getApprovedCampRewards());
        } catch (Exception ex) {
            log.debug("Failed get all approved camp rewards for current platoon", ex);
            return ResponseBuilder.fail("Failed get all approved camp rewards for current platoon");
        }
    }

    @GetMapping("/approved/twelve")
    public ResponseResult getApprovedTwelveYearAchievements() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getApprovedTwelveYearAchievements());
        } catch (Exception ex) {
            log.debug("Failed get all approved twelve year achievements for current platoon", ex);
            return ResponseBuilder.fail("Failed get all approved twelve year achievements for current platoon");
        }
    }

    @GetMapping("/approved/three")
    public ResponseResult getApprovedThreeYearAchievements() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getApprovedThreeYearAchievements());
        } catch (Exception ex) {
            log.debug("Failed get all approved three year achievements for current platoon", ex);
            return ResponseBuilder.fail("Failed get all approved three year achievements for current platoon");
        }
    }

    @GetMapping("/approved/year")
    public ResponseResult getApprovedYearAchievements() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getApprovedYearAchievements());
        } catch (Exception ex) {
            log.debug("Failed get all approved year achievements for current platoon", ex);
            return ResponseBuilder.fail("Failed get all approved year achievements for current platoon");
        }
    }

    @GetMapping("/approved/quarter")
    public ResponseResult getApprovedQuarterAchievements() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getApprovedQuarterAchievements());
        } catch (Exception ex) {
            log.debug("Failed get all approved quarter achievements for current platoon", ex);
            return ResponseBuilder.fail("Failed get all approved quarter achievements for current platoon");
        }
    }

    @JsonView(Views.Profile.class)
    @GetMapping("/users")
    public ResponseResult getUsers() {
        try {
            return ResponseBuilder.success(userService.getUsersByPlatoon());
        } catch (Exception ex) {
            log.debug("Failed all get users for current platoon", ex);
            return ResponseBuilder.fail("Failed get all users for current platoon");
        }
    }

    @GetMapping("/inProgress/twelve")
    public ResponseResult getInProgressTwelveYearAchievements() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getInProgressTwelveYearAchievements());
        } catch (Exception ex) {
            log.debug("Failed get all in progress twelve year achievements for current platoon", ex);
            return ResponseBuilder.fail("Failed get all in progress twelve year achievements for current platoon");
        }
    }

    @GetMapping("/inProgress/three")
    public ResponseResult getInProgressThreeYearAchievements() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getInProgressThreeYearAchievements());
        } catch (Exception ex) {
            log.debug("Failed get all in progress three year achievements for current platoon", ex);
            return ResponseBuilder.fail("Failed get all in progress three year achievements for current platoon");
        }
    }

    @GetMapping("/inProgress/year")
    public ResponseResult getInProgressYearAchievements() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getInProgressYearAchievements());
        } catch (Exception ex) {
            log.debug("Failed get all in progress year achievements for current platoon", ex);
            return ResponseBuilder.fail("Failed get all in progress year achievements for current platoon");
        }
    }

    @GetMapping("/inProgress/quarter")
    public ResponseResult getInProgressQuarterAchievements() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getInProgressQuarterAchievements());
        } catch (Exception ex) {
            log.debug("Failed get all in progress quarter achievements for current platoon", ex);
            return ResponseBuilder.fail("Failed get all in progress quarter achievements for current platoon");
        }
    }

    @GetMapping("/inProgress/star")
    public ResponseResult getInProgressStarRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getInProgressStarRewards());
        } catch (Exception ex) {
            log.debug("Failed get all in progress star rewards for current platoon", ex);
            return ResponseBuilder.fail("Failed get all in progress star rewards for current platoon");
        }
    }

    @GetMapping("/inProgress/medal")
    public ResponseResult getInProgressMedalRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getInProgressMedalRewards());
        } catch (Exception ex) {
            log.debug("Failed get all in progress medal rewards for current platoon", ex);
            return ResponseBuilder.fail("Failed get all in progress medal rewards for current platoon");
        }
    }

    @GetMapping("/inProgress/lath")
    public ResponseResult getInProgressLathRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getInProgressLathRewards());
        } catch (Exception ex) {
            log.debug("Failed get all in progress lath rewards for current platoon", ex);
            return ResponseBuilder.fail("Failed get all in progress lath rewards for current platoon");
        }
    }

    @GetMapping("/inProgress/trip")
    public ResponseResult getInProgressTripRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getInProgressTripRewards());
        } catch (Exception ex) {
            log.debug("Failed get all in progress  trip rewards for current platoon", ex);
            return ResponseBuilder.fail("Failed get all in progress trip rewards for current platoon");
        }
    }

    @GetMapping("/inProgress/camp")
    public ResponseResult getInProgressCampRewards() {
        try {
            return ResponseBuilder.success(platoonAchievementService.getInProgressCampRewards());
        } catch (Exception ex) {
            log.debug("Failed get camp all in progress rewards for current platoon", ex);
            return ResponseBuilder.fail("Failed get all in progress camp rewards for current platoon");
        }
    }
}
