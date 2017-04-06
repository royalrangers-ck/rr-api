package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.AchievementRequestDTO;
import com.royalrangers.repository.achievement.ThreeYearAchievementRepository;
import com.royalrangers.model.achievement.ThreeYearAchievement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThreeYearAchievementService {

    @Autowired
    private ThreeYearAchievementRepository threeYearAchievementRepository;

    @Autowired
    private TwelveYearAchievementService twelveYearAchievementService;

    public List<ThreeYearAchievement> getAllThreeYearAchievement() {
        return threeYearAchievementRepository.findAll();
    }

    public void addThreeYearAchievement(AchievementRequestDTO params) {
        ThreeYearAchievement threeYearAchievementSaved = new ThreeYearAchievement();
        threeYearAchievementSaved.setName(params.getName());
        threeYearAchievementSaved.setDescription(params.getDescription());
        Integer id = params.getUpLevelId();
        threeYearAchievementSaved.setTwelveYearAchievement(twelveYearAchievementService.getTwelveYearAchievementById(id.longValue()));
        threeYearAchievementSaved.setRequirements(params.getRequirements());
        threeYearAchievementRepository.saveAndFlush(threeYearAchievementSaved);
    }

    public ThreeYearAchievement getThreeYearAchievementById(Long id) {
        return threeYearAchievementRepository.findOne(id);
    }

    public void deleteThreeYearAchievement(Long id) {
        threeYearAchievementRepository.delete(id);
    }

    public ThreeYearAchievement editThreeYearAchievement(AchievementRequestDTO params, Long threeYearId) {
        ThreeYearAchievement threeYearData = getThreeYearAchievementById(threeYearId);
        Integer twelveYearsId = params.getUpLevelId();
        threeYearData.setTwelveYearAchievement(twelveYearAchievementService.getTwelveYearAchievementById(twelveYearsId.longValue()));
        threeYearData.setName(params.getName());
        threeYearData.setDescription(params.getDescription());
        threeYearData.setRequirements(params.getRequirements());
        threeYearData.setLogoUrl(params.getLogoUrl());
        return threeYearAchievementRepository.saveAndFlush(threeYearData);
    }
}