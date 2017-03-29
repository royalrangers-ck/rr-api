package com.royalrangers.repository;

import com.royalrangers.model.achievement.UserQuarterAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserQuarterAchievementRepository extends JpaRepository<UserQuarterAchievement, Long>{
}
