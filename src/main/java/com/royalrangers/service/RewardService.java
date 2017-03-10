package com.royalrangers.service;

import com.royalrangers.dao.RewardRepository;
import com.royalrangers.model.achievements.Reward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardService {
    @Autowired
    public RewardRepository rewardRepository;

    public void addReward(Reward reward){
        rewardRepository.saveAndFlush(reward);
    }

    public Reward editReward(Reward reward){
        return rewardRepository.saveAndFlush(reward);
    }

    public void deleteReward(Long id){
        rewardRepository.delete(id);
    }

    public List<Reward> getAllReward(){
        return rewardRepository.findAll();
    }

    public Reward getRewardById(Long id){
        return rewardRepository.findOne(id);
    }
}
