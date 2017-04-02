package com.royalrangers.service.achievement;

import com.royalrangers.repository.achievement.TwelveYearAchievementRepository;
import com.royalrangers.model.achievement.TwelveYearAchievement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TwelveYearAchievementService {

    @Autowired
    private TwelveYearAchievementRepository twelveYearAchievementRepository;

    public List<TwelveYearAchievement> getAllTwelveYearAchievement() {
        return twelveYearAchievementRepository.findAll();
    }

    public void addTwelveYearAchievement(Map<String, Object> params) {
        TwelveYearAchievement twelveYearAchievement = new TwelveYearAchievement();
        twelveYearAchievement.setName((String) params.get("name"));
        twelveYearAchievement.setDescription((String) params.get("description"));
        twelveYearAchievement.setLogoUrl((String) params.get("logoUrl"));
        twelveYearAchievement.setRequirements((String) params.get("requirements"));
        twelveYearAchievementRepository.saveAndFlush(twelveYearAchievement);
    }

    public TwelveYearAchievement getTwelveYearAchievementById(Long id) {
        return twelveYearAchievementRepository.findOne(id);
    }

    public void deleteTwelveYearAchievement(Long id) {
        twelveYearAchievementRepository.delete(id);
    }

    public TwelveYearAchievement editTwelveYearAchievement(Map<String, Object> params, Long twelveYearId) {
        TwelveYearAchievement twelveYearAchievement = getTwelveYearAchievementById(twelveYearId);
        twelveYearAchievement.setName((String) params.get("name"));
        twelveYearAchievement.setDescription((String) params.get("description"));
        twelveYearAchievement.setLogoUrl((String) params.get("logoUrl"));
        twelveYearAchievement.setRequirements((String) params.get("requirements"));
        return twelveYearAchievementRepository.saveAndFlush(twelveYearAchievement);
    }

}