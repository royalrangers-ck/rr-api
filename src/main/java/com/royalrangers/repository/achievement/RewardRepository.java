package com.royalrangers.repository.achievement;

import com.royalrangers.model.achievement.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Long>{
}
