package com.royalrangers.repository;

import com.royalrangers.model.achievement.UserTwelveYearAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTwelveYearAchievementRepository extends JpaRepository<UserTwelveYearAchievement, Long>{
}
