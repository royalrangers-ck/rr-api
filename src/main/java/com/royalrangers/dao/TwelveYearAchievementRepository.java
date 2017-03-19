package com.royalrangers.dao;

import com.royalrangers.model.achievement.TwelveYearAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwelveYearAchievementRepository extends JpaRepository<TwelveYearAchievement, Long> {
}
