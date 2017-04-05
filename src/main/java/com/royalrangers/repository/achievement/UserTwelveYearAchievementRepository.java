package com.royalrangers.repository.achievement;

import com.royalrangers.model.achievement.UserTwelveYearAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTwelveYearAchievementRepository extends JpaRepository<UserTwelveYearAchievement, Long>{
    List<UserTwelveYearAchievement> findByUserId(Long id);
    List<UserTwelveYearAchievement> findAllByTwelveYearAchievement(Long twelveYearAchievementId);
}
