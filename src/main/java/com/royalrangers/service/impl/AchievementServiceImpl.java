package com.royalrangers.service.impl;

import com.royalrangers.dao.AchievementRepository;
import com.royalrangers.model.achievements.Achievement;
import com.royalrangers.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchievementServiceImpl implements AchievementService {

    @Autowired
    AchievementRepository achievementRepository;

    @Override
    public Achievement addAchievement(Achievement achievement) {
        return achievementRepository.saveAndFlush(achievement);
    }

    @Override
    public Achievement getAchievementById(Long id) {
        return achievementRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        achievementRepository.delete(id);
    }

    @Override
    public Achievement editAchievement(Achievement achievement) {
        return achievementRepository.saveAndFlush(achievement);
    }

    @Override
    public List<Achievement> getAll() {
        return achievementRepository.findAll();
    }
}
