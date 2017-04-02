package com.royalrangers.repository.achievement;

import com.royalrangers.model.achievement.QuarterAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuarterAchievementRepository extends JpaRepository<QuarterAchievement, Long>{
}
