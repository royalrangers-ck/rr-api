package com.royalrangers.dao;

import com.royalrangers.model.achievement.YearAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearAchievementRepository extends JpaRepository<YearAchievement, Long> {
}
