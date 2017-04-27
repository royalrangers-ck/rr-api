package com.royalrangers.repository.achievement;

import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.enums.achivement.RewardType;
import com.royalrangers.model.achievement.UserReward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRewardRepository extends JpaRepository<UserReward, Long> {
    List<UserReward> findByUserId(Long id);
    List<UserReward> findByUserPlatoonIdAndAchievementStateAndReward_RewardType(Long id, AchievementState state, RewardType rewardType);
}
