package com.royalrangers.dao;

import com.royalrangers.model.achievements.ThreeYearAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreeYearAchievementRepository extends JpaRepository<ThreeYearAchievement, Long>{
}
