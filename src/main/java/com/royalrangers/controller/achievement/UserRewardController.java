package com.royalrangers.controller.achievement;

import com.fasterxml.jackson.annotation.JsonView;
import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.achievement.RewardRequestDto;
import com.royalrangers.model.Views;
import com.royalrangers.service.achievement.UserRewardService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/achievements/userReward")
public class UserRewardController {

    @Autowired
    private UserRewardService userRewardService;

    @JsonView(Views.Achievement.class)
    @GetMapping
    public ResponseResult getAllRewardsForUser() {
        try {
            return ResponseBuilder.success(userRewardService.getAllRewardForUser());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get all UserReward");
        }
    }

    @PostMapping
    public ResponseResult addUserReward(@RequestBody RewardRequestDto params) {
        try {
            userRewardService.addUserReward(params);
            return ResponseBuilder.success("Successfully added UserReward");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed add UserReward");
        }
    }

    @GetMapping("/{userRewardId}")
    public ResponseResult getUserRewardById(@PathVariable Long userRewardId) {
        try {
            return ResponseBuilder.success(userRewardService.getRewardById(userRewardId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get UserReward by id");
        }
    }

    @DeleteMapping("/{userRewardId}")
    public ResponseResult deleteUserReward(@PathVariable Long userRewardId) {
        try {
            userRewardService.deleteUserReward(userRewardId);
            return ResponseBuilder.success("Successfully deleted UserReward");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed delete UserReward");
        }
    }

    @PutMapping("/{userRewardId}")
    public ResponseResult editUserReward(@RequestBody RewardRequestDto params, @PathVariable Long userRewardId) {
        try {
            userRewardService.editUserReward(params, userRewardId);
            return ResponseBuilder.success("Successfully edited UserReward");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed edit UserReward");
        }
    }

}
