package com.royalrangers.repository.achievement;

import com.royalrangers.model.achievement.UserQuarterAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserQuarterAchievementRepository extends JpaRepository<UserQuarterAchievement, Long>{
    List<UserQuarterAchievement> findByUserId(Long id);
    List<UserQuarterAchievement> findAllByQuarterAchievement(Long QuarterAchievementId);
}
