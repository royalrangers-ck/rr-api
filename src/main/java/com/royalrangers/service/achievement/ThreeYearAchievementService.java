package com.royalrangers.service.achievement;

import com.royalrangers.repository.achievement.ThreeYearAchievementRepository;
import com.royalrangers.model.achievement.ThreeYearAchievement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ThreeYearAchievementService {

    @Autowired
    private ThreeYearAchievementRepository threeYearAchievementRepository;

    @Autowired
    private TwelveYearAchievementService twelveYearAchievementService;

    public List<ThreeYearAchievement> getAllThreeYearAchievement() {
        return threeYearAchievementRepository.findAll();
    }

    public void addThreeYearAchievement(Map<String, Object> params) {
        ThreeYearAchievement threeYearAchievementSaved = new ThreeYearAchievement();
        threeYearAchievementSaved.setName((String) params.get("name"));
        threeYearAchievementSaved.setDescription((String) params.get("description"));
        Integer id = (Integer) params.get("twelveYearAchievement");
        threeYearAchievementSaved.setTwelveYearAchievement(twelveYearAchievementService.getTwelveYearAchievementById(id.longValue()));
        threeYearAchievementSaved.setRequirements((String) params.get("requirements"));
        threeYearAchievementRepository.saveAndFlush(threeYearAchievementSaved);
    }

    public ThreeYearAchievement getThreeYearAchievementById(Long id) {
        return threeYearAchievementRepository.findOne(id);
    }

    public void deleteThreeYearAchievement(Long id) {
        threeYearAchievementRepository.delete(id);
    }

    public ThreeYearAchievement editThreeYearAchievement(Map<String, Object> params, Long threeYearId) {
        ThreeYearAchievement threeYearData = getThreeYearAchievementById(threeYearId);
        Integer twelveYearsId = (Integer) params.get("twelveYearAchievement");
        threeYearData.setTwelveYearAchievement(twelveYearAchievementService.getTwelveYearAchievementById(twelveYearsId.longValue()));
        threeYearData.setName((String) params.get("name"));
        threeYearData.setUpdateDate(new Date());
        threeYearData.setDescription((String) params.get("description"));
        threeYearData.setRequirements((String) params.get("requirements"));
        threeYearData.setLogoUrl((String) params.get("logoUrl"));
        return threeYearAchievementRepository.saveAndFlush(threeYearData);
    }
}