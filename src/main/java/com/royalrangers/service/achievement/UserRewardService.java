package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.RewardRequestDto;
import com.royalrangers.exception.AchievementException;
import com.royalrangers.model.achievement.UserReward;
import com.royalrangers.repository.achievement.RewardRepository;
import com.royalrangers.repository.achievement.UserRewardRepository;
import com.royalrangers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserRewardService {

    @Autowired
    private UserRewardRepository userRewardRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RewardService rewardService;

    @Autowired
    private RewardRepository rewardRepository;

    public List<UserReward> getAllRewardForUser(){
        return userRewardRepository.findByUserId(userService.getAuthenticatedUserId());
    }

    public void addUserReward(RewardRequestDto params){
        UserReward savedUserReward = new UserReward();
        Integer rewardId = params.getRewardId();
        if(!rewardRepository.exists(rewardId.longValue())) {
            throw new AchievementException("Not found reward with id " + rewardId);
        }
        savedUserReward.setReward(rewardService.getRewardById(rewardId.longValue()));
        savedUserReward.setUser(userService.getUserById(userService.getAuthenticatedUserId()));
        userRewardRepository.saveAndFlush(savedUserReward);
    }

    public UserReward getRewardById(Long id){
        return userRewardRepository.findOne(id);
    }

    public void deleteUserReward(Long id){
        userRewardRepository.delete(id);
    }

    public void editUserReward(RewardRequestDto params, Long id){
        UserReward savedUserReward = userRewardRepository.findOne(id);
        savedUserReward.setUpdateDate(new Date());
        Integer rewardId = params.getRewardId();
        savedUserReward.setReward(rewardService.getRewardById(rewardId.longValue()));
        userRewardRepository.saveAndFlush(savedUserReward);
    }
}
