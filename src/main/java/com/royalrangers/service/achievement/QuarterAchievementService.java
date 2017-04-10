package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.AchievementRequestDto;
import com.royalrangers.repository.achievement.QuarterAchievementRepository;
import com.royalrangers.model.achievement.QuarterAchievement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuarterAchievementService {

    @Autowired
    public QuarterAchievementRepository quarterAchievementRepository;

    @Autowired
    private YearAchievementService yearAchievementService;

    public List<QuarterAchievement> getAllQuarterAchievement() {
        return quarterAchievementRepository.findAll();
    }

    public void addQuarterAchievement(AchievementRequestDto params) {
        QuarterAchievement quarterAchievement = new QuarterAchievement();
        quarterAchievement.setName(params.getName());
        quarterAchievement.setDescription(params.getDescription());
        quarterAchievement.setRequirements(params.getRequirements());
        Integer yearId = params.getUpLevelId();
        quarterAchievement.setYearAchievement(yearAchievementService.getYearAchievementById(yearId.longValue()));
        quarterAchievementRepository.saveAndFlush(quarterAchievement);
    }

    public QuarterAchievement getQuarterAchievementById(Long quarterId) {
        return quarterAchievementRepository.findOne(quarterId);
    }

    public void deleteQuarterAchievement(Long quarterId) {
        quarterAchievementRepository.delete(quarterId);
    }

    public QuarterAchievement editQuarterAchievement(AchievementRequestDto params, Long quarterId) {
        QuarterAchievement editQuarterAchievement = getQuarterAchievementById(quarterId);
        editQuarterAchievement.setName(params.getName());
        editQuarterAchievement.setDescription(params.getDescription());
        editQuarterAchievement.setRequirements(params.getRequirements());
        Integer yearId = params.getUpLevelId();
        editQuarterAchievement.setYearAchievement(yearAchievementService.getYearAchievementById(yearId.longValue()));
        return quarterAchievementRepository.saveAndFlush(editQuarterAchievement);
    }
}
