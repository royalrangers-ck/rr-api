package com.royalrangers.service;

import com.royalrangers.dao.ThreeYearAchievementRepository;
import com.royalrangers.model.achievement.ThreeYearAchievement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThreeYearAchievementService {

    @Autowired
    private ThreeYearAchievementRepository threeYearAchievementRepository;

    public List<ThreeYearAchievement> getAllThreeYearAchievement(){
        return threeYearAchievementRepository.findAll();
    }

    public ThreeYearAchievement getThreeYearAchievementById(Long id){
        return threeYearAchievementRepository.findOne(id);
    }

    public void deleteThreeYearAchievement (Long id){
        threeYearAchievementRepository.delete(id);
    }

    public void addThreeYearAchievement(ThreeYearAchievement threeYearAchievement){
        threeYearAchievementRepository.saveAndFlush(threeYearAchievement);
    }

    public ThreeYearAchievement editThreeYearAchievement(ThreeYearAchievement threeYearAchievement){
        return threeYearAchievementRepository.saveAndFlush(threeYearAchievement);
    }
}
