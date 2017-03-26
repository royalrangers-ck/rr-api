package com.royalrangers.controller;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.model.achievement.Reward;
import com.royalrangers.service.RewardService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @RequestMapping(value = "/achievements/reward", method = RequestMethod.GET)
    public ResponseResult getAllReward() {
        try {
            return ResponseBuilder.success(rewardService.getAllReward());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get all rewards");
        }
    }

    @RequestMapping(value = "/achievements/reward", method = RequestMethod.POST)
    public ResponseResult addReward(@RequestBody Reward reward) {
        try {
            rewardService.addReward(reward);
            return ResponseBuilder.success("Successful addition of a reward");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed add reward");
        }
    }

    @RequestMapping(value = "/achievements/reward/{rewardId}", method = RequestMethod.GET)
    public ResponseResult getRewardById(@PathVariable Long rewardId) {
        try {
            return ResponseBuilder.success(rewardService.getRewardById(rewardId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get reward by id");
        }
    }

    @RequestMapping(value = "/achievements/reward/{rewardId}", method = RequestMethod.DELETE)
    public ResponseResult deleteReward(@PathVariable Long rewardId) {
        try {
            rewardService.deleteReward(rewardId);
            return ResponseBuilder.success("Delete Reward was a success");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed deleted Reward");
        }
    }

    @RequestMapping(value = "/achievements/reward/{rewardId}", method = RequestMethod.PUT)
    public ResponseResult editReward(@RequestBody Reward reward, @PathVariable Long rewardId) {
        try {
            return ResponseBuilder.success(rewardService.editReward(reward, rewardId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed edit Reward");
        }
    }
}
