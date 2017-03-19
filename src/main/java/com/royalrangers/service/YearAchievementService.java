package com.royalrangers.service;

import com.royalrangers.dao.YearAchievementRepository;
import com.royalrangers.model.achievement.YearAchievement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YearAchievementService {

    @Autowired
    private YearAchievementRepository yearAchievementRepository;

    public List<YearAchievement> getAllYearAchievement(){
        return yearAchievementRepository.findAll();
    }

    public YearAchievement getYearAchievementById(Long id){
        return yearAchievementRepository.findOne(id);
    }

    public void deleteYearAchievement (Long id){
        yearAchievementRepository.delete(id);
    }

    public void addYearAchievement(YearAchievement yearAchievement){
        yearAchievementRepository.saveAndFlush(yearAchievement);
    }

    public YearAchievement editYearAchievement(YearAchievement yearAchievement){
        return yearAchievementRepository.saveAndFlush(yearAchievement);
    }
}
