package com.royalrangers.controller;

import com.royalrangers.model.achievements.Achievement;
import com.royalrangers.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AchievementController {
    @Autowired
    AchievementService achievementService;


    @RequestMapping(value = "/achievements", method = RequestMethod.POST)
    public void addAchievement(@RequestBody Achievement achievement){
        achievementService.addAchievement(achievement);
    }

    @RequestMapping(value = "/achievements", method = RequestMethod.GET)
    public List<Achievement> getAllAchievements(){
        return achievementService.getAll();
    }

    @RequestMapping(value = "/achievements/{id}", method = RequestMethod.GET)
    public Achievement getAchievementById(@PathVariable Long id){
        return achievementService.getAchievementById(id);
    }

    @RequestMapping(value = "/achievements/{id}", method = RequestMethod.DELETE)
    public void deleteAchievement(@PathVariable Long id){
        achievementService.delete(id);
    }

    @RequestMapping(value = "/achievements/{id}", method = RequestMethod.PUT)
    public Achievement editAchievement(@PathVariable Long id, @RequestBody Achievement achievement){
        return achievementService.editAchievement(achievement);
    }
}
