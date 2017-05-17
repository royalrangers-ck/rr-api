package com.royalrangers.repository.achievement;

import com.royalrangers.enums.UserAgeGroup;
import com.royalrangers.model.achievement.YearAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YearAchievementRepository extends JpaRepository<YearAchievement, Long> {
    public List<YearAchievement> findByUserAgeGroup(UserAgeGroup userAgeGroup);
}
