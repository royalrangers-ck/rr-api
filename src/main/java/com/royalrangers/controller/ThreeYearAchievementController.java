package com.royalrangers.controller;

import com.royalrangers.dao.ThreeYearAchievementRepository;
import com.royalrangers.model.achievements.ThreeYearAchievement;
import com.royalrangers.service.ThreeYearAchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ThreeYearAchievementController {

    @Autowired
    private ThreeYearAchievementService threeYearAchievementService;

    @RequestMapping(value = "/achievements/threeYearAchievements", method = RequestMethod.GET)
    public Map<String, Object> getAllThreeYearAchievement(){
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        try{
            result.put("data", threeYearAchievementService.getAllThreeYearAchievement());
            result.put("success", true);
        } catch (Exception ex){
            data.put("message", "Failed get all threeYearAchievements");
            result.put("data", data);
            result.put("success", false);
        }
        return result;
    }

    @RequestMapping(value = "/achievements/threeYearAchievements", method = RequestMethod.POST)
    public Map<String, Object> addThreeYearAchievement(@RequestBody ThreeYearAchievement threeYearAchievement){
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        try{
            threeYearAchievementService.addThreeYearAchievement(threeYearAchievement);
            data.put("message", "Successful addition of a threeYearAchievements");
            result.put("data", data);
            result.put("success", true);
        } catch (Exception ex){
            data.put("message", "Failed add threeYearAchievements");
            result.put("data", data);
            result.put("success", false);
        }
        return result;
    }

    @RequestMapping(value = "/achievements/threeYearAchievements/{threeYearAchievementsId}", method = RequestMethod.PUT)
    public Map<String, Object> editThreeYearAchievement(@RequestBody ThreeYearAchievement threeYearAchievement){
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        try{
            result.put("data", threeYearAchievementService.editThreeYearAchievement(threeYearAchievement));
            result.put("success", true);
        } catch (Exception ex){
            data.put("message", "Failed edit threeYearAchievements");
            result.put("data", data);
            result.put("success", false);
        }
        return result;
    }

    @RequestMapping(value = "/achievements/threeYearAchievements/{threeYearAchievementsId}", method = RequestMethod.GET)
    public Map<String, Object> getThreeYearAchievementById(@PathVariable Long threeYearAchievementsId){
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        try{
            result.put("data", threeYearAchievementService.getThreeYearAchievementById(threeYearAchievementsId));
            result.put("success", true);
        } catch (Exception ex){
            data.put("message", "Failed get threeYearAchievements by id");
            result.put("data", data);
            result.put("success", false);
        }
        return result;
    }

    @RequestMapping(value = "/achievements/threeYearAchievements/{threeYearAchievementsId}", method = RequestMethod.DELETE)
    public Map<String, Object> deleteThreeYearAchievement(@PathVariable Long threeYearAchievementsId){
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        try{
            threeYearAchievementService.deleteThreeYearAchievement(threeYearAchievementsId);
            data.put("message", "Successful delete threeYearAchievements");
            result.put("data", data);
            result.put("success", true);
        } catch (Exception ex){
            data.put("message", "Failed delete threeYearAchievements");
            result.put("data", data);
            result.put("success", false);
        }
        return result;
    }

}
