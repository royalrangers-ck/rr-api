package com.royalrangers.repository.achievement;

import com.royalrangers.model.achievement.UserReward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRewardRepository extends JpaRepository<UserReward, Long> {
    List<UserReward> findByUserId(Long id);
    List<UserReward> findByUser_PlatoonId(Long id);

}
