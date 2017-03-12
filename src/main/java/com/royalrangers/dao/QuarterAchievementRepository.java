package com.royalrangers.dao;

import com.royalrangers.model.achievements.QuarterAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuarterAchievementRepository extends JpaRepository<QuarterAchievement, Long>{
}
