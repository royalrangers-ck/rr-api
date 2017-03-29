package com.royalrangers.repository;

import com.royalrangers.model.achievement.UserYearAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserYearAchievementRepository extends JpaRepository<UserYearAchievement, Long>{
}
