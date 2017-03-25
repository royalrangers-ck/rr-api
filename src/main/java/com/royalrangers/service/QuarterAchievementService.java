package com.royalrangers.service;

import com.royalrangers.repository.QuarterAchievementRepository;
import com.royalrangers.model.achievement.QuarterAchievement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuarterAchievementService {

    @Autowired
    public QuarterAchievementRepository quarterAchievementRepository;

    @Autowired
    private YearAchievementService yearAchievementService;

    public List<QuarterAchievement> getAllQuarterAchievement() {
        return quarterAchievementRepository.findAll();
    }

    public void addQuarterAchievement(Map<String, Object> params) {
        QuarterAchievement quarterAchievement = new QuarterAchievement();
        quarterAchievement.setName((String) params.get("name"));
        quarterAchievement.setDescription((String) params.get("description"));
        quarterAchievement.setRequirements((String) params.get("requirements"));
        Integer yearId = (Integer) params.get("yearAchievementId");
        quarterAchievement.setYearAchievement(yearAchievementService.getYearAchievementById(yearId.longValue()));
        quarterAchievementRepository.saveAndFlush(quarterAchievement);
    }

    public QuarterAchievement getQuarterAchievementById(Long quarterId) {
        return quarterAchievementRepository.findOne(quarterId);
    }

    public void deleteQuarterAchievement(Long quarterId) {
        quarterAchievementRepository.delete(quarterId);
    }

    public QuarterAchievement editQuarterAchievement(Map<String, Object> params, Long quarterId) {
        QuarterAchievement editQuarterAchievement = getQuarterAchievementById(quarterId);
        editQuarterAchievement.setName((String) params.get("name"));
        editQuarterAchievement.setDescription((String) params.get("description"));
        editQuarterAchievement.setRequirements((String) params.get("requirements"));
        Integer yearId = (Integer) params.get("yearAchievementId");
        editQuarterAchievement.setYearAchievement(yearAchievementService.getYearAchievementById(yearId.longValue()));
        return quarterAchievementRepository.saveAndFlush(editQuarterAchievement);
    }
}
