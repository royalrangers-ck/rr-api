package com.royalrangers.controller.achievement;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.model.achievement.Reward;
import com.royalrangers.service.achievement.RewardService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/achievements/reward")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @GetMapping
    public ResponseResult getAllReward() {
        try {
            return ResponseBuilder.success(rewardService.getAllReward());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get all rewards");
        }
    }

    @PostMapping
    public ResponseResult addReward(@RequestBody Reward reward) {
        try {
            rewardService.addReward(reward);
            return ResponseBuilder.success("Successful addition of a reward");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed add reward");
        }
    }

    @GetMapping("/{rewardId}")
    public ResponseResult getRewardById(@PathVariable Long rewardId) {
        try {
            return ResponseBuilder.success(rewardService.getRewardById(rewardId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get reward by id");
        }
    }

    @DeleteMapping("/{rewardId}")
    public ResponseResult deleteReward(@PathVariable Long rewardId) {
        try {
            rewardService.deleteReward(rewardId);
            return ResponseBuilder.success("Delete Reward was a success");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed deleted Reward");
        }
    }

    @PutMapping("/{rewardId}")
    public ResponseResult editReward(@RequestBody Reward reward, @PathVariable Long rewardId) {
        try {
            return ResponseBuilder.success(rewardService.editReward(reward, rewardId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed edit Reward");
        }
    }
}