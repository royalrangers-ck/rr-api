package com.royalrangers.repository.achievement;

import com.royalrangers.model.achievement.UserThreeYearAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserThreeYearAchievementRepository extends JpaRepository<UserThreeYearAchievement, Long>{
    List<UserThreeYearAchievement> findByUserId(Long id);
    List<UserThreeYearAchievement> findAllByThreeYearAchievement(Long threeYearAchievementId);
}
