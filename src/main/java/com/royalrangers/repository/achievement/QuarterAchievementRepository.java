package com.royalrangers.repository.achievement;

import com.royalrangers.enums.UserAgeGroup;
import com.royalrangers.model.achievement.QuarterAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuarterAchievementRepository extends JpaRepository<QuarterAchievement, Long>{
    public List<QuarterAchievement> findByUserAgeGroup(UserAgeGroup userAgeGroup);
}
