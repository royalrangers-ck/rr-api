package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.AchievementRequestDTO;
import com.royalrangers.repository.achievement.YearAchievementRepository;
import com.royalrangers.model.achievement.YearAchievement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YearAchievementService {

    @Autowired
    private YearAchievementRepository yearAchievementRepository;

    @Autowired
    private ThreeYearAchievementService threeYearAchievementService;

    public List<YearAchievement> getAllYearAchievement() {
        return yearAchievementRepository.findAll();
    }

    public void addYearAchievement(AchievementRequestDTO params) {
        YearAchievement yearAchievementSaved = new YearAchievement();
        yearAchievementSaved.setName(params.getName());
        yearAchievementSaved.setDescription(params.getDescription());
        Integer id = params.getUpLevelId();
        yearAchievementSaved.setThreeYearAchievement(threeYearAchievementService.getThreeYearAchievementById(id.longValue()));
        yearAchievementSaved.setRequirements(params.getRequirements());
        yearAchievementRepository.saveAndFlush(yearAchievementSaved);
    }

    public YearAchievement getYearAchievementById(Long id) {
        return yearAchievementRepository.findOne(id);
    }

    public void deleteYearAchievement(Long id) {
        yearAchievementRepository.delete(id);
    }

    public YearAchievement editYearAchievement(AchievementRequestDTO params, Long yearId) {
        YearAchievement editYearData = getYearAchievementById(yearId);
        Integer editThreeYearId = params.getUpLevelId();
        editYearData.setName(params.getName());
        editYearData.setDescription(params.getDescription());
        editYearData.setLogoUrl(params.getLogoUrl());
        editYearData.setRequirements(params.getRequirements());
        editYearData.setThreeYearAchievement(threeYearAchievementService.getThreeYearAchievementById(editThreeYearId.longValue()));
        return yearAchievementRepository.saveAndFlush(editYearData);
    }
}