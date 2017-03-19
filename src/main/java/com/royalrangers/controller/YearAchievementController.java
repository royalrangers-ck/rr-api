package com.royalrangers.controller;

import com.royalrangers.model.achievement.YearAchievement;
import com.royalrangers.service.YearAchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class YearAchievementController {

    @Autowired
    private YearAchievementService yearAchievementService;

    @RequestMapping(value = "/achievements/yearAchievements", method = RequestMethod.GET)
    public Map<String, Object> getAllYearAchievement(){
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        try{
            result.put("data", yearAchievementService.getAllYearAchievement());
            result.put("success", true);
        } catch (Exception ex){
            data.put("message", "Failed get all yearAchievements");
            result.put("data", data);
            result.put("success", false);
        }
        return result;
    }

    @RequestMapping(value = "/achievements/yearAchievements", method = RequestMethod.POST)
    public Map<String, Object> addYearAchievement(@RequestBody YearAchievement yearAchievement){
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        try{
            yearAchievementService.addYearAchievement(yearAchievement);
            data.put("message", "Successful addition of a yearAchievements");
            result.put("data", data);
            result.put("success", true);
        } catch (Exception ex){
            data.put("message", "Failed add yearAchievements");
            result.put("data", data);
            result.put("success", false);
        }
        return result;
    }

    @RequestMapping(value = "/achievements/yearAchievements/{yearAchievementsId}", method = RequestMethod.PUT)
    public Map<String, Object> editYearAchievement(@RequestBody YearAchievement yearAchievement){
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        try{
            result.put("data", yearAchievementService.editYearAchievement(yearAchievement));
            result.put("success", true);
        } catch (Exception ex){
            data.put("message", "Failed edit yearAchievements");
            result.put("data", data);
            result.put("success", false);
        }
        return result;
    }

    @RequestMapping(value = "/achievements/yearAchievements/{yearAchievementsId}", method = RequestMethod.GET)
    public Map<String, Object> getYearAchievementById(@PathVariable Long yearAchievementsId){
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        try{
            result.put("data", yearAchievementService.getYearAchievementById(yearAchievementsId));
            result.put("success", true);
        } catch (Exception ex){
            data.put("message", "Failed get yearAchievements by id");
            result.put("data", data);
            result.put("success", false);
        }
        return result;
    }

    @RequestMapping(value = "/achievements/yearAchievements/{yearAchievementsId}", method = RequestMethod.DELETE)
    public Map<String, Object> deleteYearAchievement(@PathVariable Long yearAchievementsId){
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        try{
            yearAchievementService.deleteYearAchievement(yearAchievementsId);
            data.put("message", "Successful delete yearAchievements");
            result.put("data", data);
            result.put("success", true);
        } catch (Exception ex){
            data.put("message", "Failed delete yearAchievements");
            result.put("data", data);
            result.put("success", false);
        }
        return result;
    }
}
