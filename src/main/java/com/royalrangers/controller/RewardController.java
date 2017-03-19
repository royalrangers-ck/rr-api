package com.royalrangers.controller;

import com.royalrangers.model.achievement.Reward;
import com.royalrangers.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @RequestMapping(value = "/achievements/rewards", method = RequestMethod.GET)
    public Map<String, Object> getAllReward(){
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        try{
            result.put("data", rewardService.getAllReward());
            result.put("success", true);
        } catch (Exception ex) {
            data.put("message", "Failed get all rewards");
            result.put("data", data);
            result.put("success", false);
        }
        return result;
    }

    @RequestMapping(value = "/achievements/rewards", method = RequestMethod.POST)
    public Map<String, Object> addReward(@RequestBody Reward reward){
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        try{
            rewardService.addReward(reward);
            data.put("message", "Successful addition of a reward");
            result.put("data", data);
            result.put("success", true);
        } catch (Exception ex) {
            data.put("message", "Failed add rewards");
            result.put("data", data);
            result.put("success", false);
        }
        return result;
    }

    @RequestMapping(value = "/achievements/rewards/{rewardId}", method = RequestMethod.GET)
    public Map<String, Object> getRewardById(@PathVariable Long rewardId){
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        try{
            result.put("data", rewardService.getRewardById(rewardId));
            result.put("success", true);
        } catch (Exception ex) {
            data.put("message", ex.getMessage());
            result.put("data", data);
            result.put("success", false);
        }

        return result;
    }

    @RequestMapping(value = "/achievements/rewards/{rewardId}", method = RequestMethod.DELETE)
    public Map<String, Object> deleteReward(@PathVariable Long rewardId){
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
            try{
                rewardService.deleteReward(rewardId);
                data.put("message", "Delete Reward was a success");
                result.put("data", data);
                result.put("success", true);
            } catch (Exception ex) {
                data.put("message", "Failed deleted Reward");
                result.put("data", data);
                result.put("success", false);
            }
        return result;
    }

    @RequestMapping(value = "/achievements/rewards/{rewardId}", method = RequestMethod.PUT)
    public Map<String, Object> editReward(@RequestBody Reward reward){
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
            try{
                result.put("data", rewardService.editReward(reward));
                result.put("success", true);
            } catch (Exception ex) {
                data.put("message", "Failed edit Reward");
                result.put("data", data);
                result.put("success", false);
            }
        return result;
    }
}
