package com.royalrangers.service.achievement;

import com.royalrangers.repository.achievement.YearAchievementRepository;
import com.royalrangers.model.achievement.YearAchievement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class YearAchievementService {

    @Autowired
    private YearAchievementRepository yearAchievementRepository;

    @Autowired
    private ThreeYearAchievementService threeYearAchievementService;

    public List<YearAchievement> getAllYearAchievement() {
        return yearAchievementRepository.findAll();
    }

    public void addYearAchievement(Map<String, Object> params) {
        YearAchievement yearAchievementSaved = new YearAchievement();
        yearAchievementSaved.setName((String) params.get("name"));
        yearAchievementSaved.setDescription((String) params.get("description"));
        Integer id = (Integer) params.get("threeYearAchievement");
        yearAchievementSaved.setThreeYearAchievement(threeYearAchievementService.getThreeYearAchievementById(id.longValue()));
        yearAchievementSaved.setRequirements((String) params.get("requirements"));
        yearAchievementRepository.saveAndFlush(yearAchievementSaved);
    }

    public YearAchievement getYearAchievementById(Long id) {
        return yearAchievementRepository.findOne(id);
    }

    public void deleteYearAchievement(Long id) {
        yearAchievementRepository.delete(id);
    }

    public YearAchievement editYearAchievement(Map<String, Object> params, Long yearId) {
        YearAchievement editYearData = getYearAchievementById(yearId);
        Integer editThreeYearId = (Integer) params.get("threeYearAchievement");
        editYearData.setName((String) params.get("name"));
        editYearData.setUpdateDate(new Date());
        editYearData.setDescription((String) params.get("description"));
        editYearData.setLogoUrl((String) params.get("logoUrl"));
        editYearData.setRequirements((String) params.get("requirements"));
        editYearData.setThreeYearAchievement(threeYearAchievementService.getThreeYearAchievementById(editThreeYearId.longValue()));
        return yearAchievementRepository.saveAndFlush(editYearData);
    }
}