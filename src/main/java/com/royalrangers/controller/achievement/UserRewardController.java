package com.royalrangers.controller.achievement;

import com.fasterxml.jackson.annotation.JsonView;
import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.achievement.RewardRequestDto;
import com.royalrangers.model.Views;
import com.royalrangers.service.achievement.UserRewardService;
import com.royalrangers.utils.ResponseBuilder;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/achievements/userReward")
public class UserRewardController {

    @Autowired
    private UserRewardService userRewardService;

    @JsonView(Views.Achievement.class)
    @GetMapping
    @ApiOperation(value = "Get a list of rewards for current user")
    public ResponseResult getAllRewardsForUser() {
        try {
            return ResponseBuilder.success(userRewardService.getAllRewardForUser());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed get all UserReward");
        }
    }

    @PostMapping
    @ApiOperation(value = "Add the reward for current user")
    public ResponseResult addUserReward(@RequestBody RewardRequestDto params) {
        try {
            userRewardService.addUserReward(params);
            return ResponseBuilder.success("Successfully added UserReward");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed add UserReward");
        }
    }

    @GetMapping("/{userRewardId}")
    @ApiOperation(value = "Get user reward info")
    public ResponseResult getUserRewardById(@PathVariable Long userRewardId) {
        try {
            return ResponseBuilder.success(userRewardService.getRewardById(userRewardId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed get UserReward by id");
        }
    }

    @DeleteMapping("/{userRewardId}")
    @ApiOperation(value = "Delete user reward")
    public ResponseResult deleteUserReward(@PathVariable Long userRewardId) {
        try {
            userRewardService.deleteUserReward(userRewardId);
            return ResponseBuilder.success("Successfully deleted UserReward");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed delete UserReward");
        }
    }

    @PutMapping("/{userRewardId}")
    @ApiOperation(value = "Update user reward")
    public ResponseResult editUserReward(@RequestBody RewardRequestDto params, @PathVariable Long userRewardId) {
        try {
            userRewardService.editUserReward(params, userRewardId);
            return ResponseBuilder.success("Successfully edited UserReward");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseBuilder.fail("Failed edit UserReward");
        }
    }

}
