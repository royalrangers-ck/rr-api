package com.royalrangers.controller;

import com.royalrangers.model.achievement.QuarterAchievement;
import com.royalrangers.service.QuarterAchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class QuarterAchievementController {

    @Autowired
    private QuarterAchievementService quarterAchievementService;

    @RequestMapping(value = "/achievements/quarterAchievements", method = RequestMethod.POST)
    public Map<String, Object> addQuarterAchievement(@RequestBody QuarterAchievement quarterAchievement) {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        try {
            quarterAchievementService.addQuarterAchievement(quarterAchievement);
            data.put("message", "Adding QuarterAchievement was a success");
            result.put("data", data);
            result.put("success", true);
        } catch (Exception ex) {
            data.put("message", "Failed added QuarterAchievement");
            result.put("data", data);
            result.put("success", false);
        }
        return result;
    }

    @RequestMapping(value = "/achievements/quarterAchievements/{quarterAchievementId}", method = RequestMethod.PUT)
    public Map<String, Object> editQuarterAchievement(@RequestBody QuarterAchievement quarterAchievement){
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        try{
            result.put("data", quarterAchievementService.editQuarterAchievement(quarterAchievement));
            result.put("success", true);
        } catch (Exception ex) {
            data.put("message", "Failed edit QuarterAchievement");
            result.put("data", data);
            result.put("success", false);
        }
        return result;
    }

    @RequestMapping(value = "/achievements/quarterAchievements/{quarterAchievementId}", method = RequestMethod.DELETE)
    public Map<String, Object> deleteQuarterAchievement(@PathVariable Long quarterAchievementId){
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        try{
            quarterAchievementService.deleteQuarterAchievement(quarterAchievementId);
            data.put("message", "Delete QuarterAchievement was a success");
            result.put("data", data);
            result.put("success", true);
        } catch (Exception ex) {
            data.put("message", "Failed deleted QuarterAchievement");
            result.put("data", data);
            result.put("success", false);
        }
        return result;
    }
    @RequestMapping(value = "/achievements/quarterAchievements/{quarterAchievementId}", method = RequestMethod.GET)
    public Map<String, Object> getQuarterAchievementById(@PathVariable Long quarterAchievementId){
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        try{
            result.put("data", quarterAchievementService.getQuarterAchievementById(quarterAchievementId));
            result.put("success", true);
        } catch (Exception ex) {
            data.put("message", "Failed get QuarterAchievement by id");
            result.put("data", data);
            result.put("success", false);
        }
        return result;
    }

    @RequestMapping(value = "/achievements/quarterAchievements", method = RequestMethod.GET)
    public Map<String, Object> getAllQuarterAchievement(){
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        try{
            result.put("data", quarterAchievementService.getAllQuarterAchievement());
            result.put("success", true);
        } catch (Exception ex) {
            data.put("message", "Failed added QuarterAchievement");
            result.put("data", data);
            result.put("success", false);
        }
        return result;
    }
}
