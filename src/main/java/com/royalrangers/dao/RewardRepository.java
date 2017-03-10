package com.royalrangers.dao;

import com.royalrangers.model.achievements.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Long>{
}
