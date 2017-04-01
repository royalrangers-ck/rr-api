package com.royalrangers.service.achievement;

import com.royalrangers.repository.achievement.RewardRepository;
import com.royalrangers.model.achievement.Reward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardService {
    @Autowired
    public RewardRepository rewardRepository;

    public List<Reward> getAllReward() {
        return rewardRepository.findAll();
    }

    public void addReward(Reward reward) {
        rewardRepository.saveAndFlush(reward);
    }

    public Reward getRewardById(Long id) {
        return rewardRepository.findOne(id);
    }

    public void deleteReward(Long id) {
        rewardRepository.delete(id);
    }

    public Reward editReward(Reward reward, Long rewardId) {
        Reward editReward = getRewardById(rewardId);
        editReward = reward;
        return rewardRepository.saveAndFlush(editReward);
    }
}
