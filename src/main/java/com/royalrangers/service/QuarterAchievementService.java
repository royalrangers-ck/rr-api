package com.royalrangers.service;

import com.royalrangers.dao.QuarterAchievementRepository;
import com.royalrangers.model.achievements.QuarterAchievement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuarterAchievementService {

    @Autowired
    public QuarterAchievementRepository quarterAchievementRepository;

    public void addQuarterAchievement(QuarterAchievement quarterAchievement){
        quarterAchievementRepository.saveAndFlush(quarterAchievement);
    }

    public QuarterAchievement editQuarterAchievement(QuarterAchievement quarterAchievement){
        return quarterAchievementRepository.saveAndFlush(quarterAchievement);
    }

    public void deleteQuarterAchievement(Long id){
        quarterAchievementRepository.delete(id);
    }

    public List<QuarterAchievement> getAllQuarterAchievement(){
        return quarterAchievementRepository.findAll();
    }

    public QuarterAchievement getQuarterAchievementById(Long id){
        return quarterAchievementRepository.findOne(id);
    }
}
