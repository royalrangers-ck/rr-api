package com.royalrangers.service;

import com.royalrangers.model.achievements.Achievement;

import java.util.List;

public interface AchievementService {
    public Achievement addAchievement(Achievement achievement);

    public Achievement getAchievementById(Long id);

    public void delete(Long id);

    public Achievement editAchievement(Achievement achievement);

    public List<Achievement> getAll();
}
