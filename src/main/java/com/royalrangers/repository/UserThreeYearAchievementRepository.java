package com.royalrangers.repository;

import com.royalrangers.model.achievement.UserThreeYearAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserThreeYearAchievementRepository extends JpaRepository<UserThreeYearAchievement, Long>{
}
