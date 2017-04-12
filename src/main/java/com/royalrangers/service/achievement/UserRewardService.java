package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.RewardRequestDto;
import com.royalrangers.dto.achievement.RewardResponseDto;
import com.royalrangers.exception.AchievementException;
import com.royalrangers.model.achievement.UserReward;
import com.royalrangers.repository.achievement.RewardRepository;
import com.royalrangers.repository.achievement.UserRewardRepository;
import com.royalrangers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<RewardResponseDto> getAllRewardForUser(){
        List<UserReward> userRewards = userRewardRepository.findByUserId(userService.getAuthenticatedUserId());
        List<RewardResponseDto> result = new ArrayList<>();
        for (UserReward user : userRewards) {
            result.add(buildUserRewardBean(user));
        }
        return result;
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

    public RewardResponseDto getRewardById(Long id){
        UserReward userReward = userRewardRepository.findOne(id);
        return buildUserRewardBean(userReward);
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

    private RewardResponseDto buildUserRewardBean(UserReward userReward){
        RewardResponseDto rewardResponseDto = new RewardResponseDto();
        rewardResponseDto.setId(userReward.getId());
        rewardResponseDto.setUserId(userReward.getUser().getId());
        rewardResponseDto.setUser(UserService.buildUserAchievementBean(userReward.getUser()));
        rewardResponseDto.setRewardId(userReward.getReward().getId());
        rewardResponseDto.setRewardName(userReward.getReward().getName());
        rewardResponseDto.setRewardDescription(userReward.getReward().getDescription());
        rewardResponseDto.setRewardLogoUrl(userReward.getReward().getLogoUrl());
        rewardResponseDto.setRewardMark(userReward.getReward().getRewardMark());
        rewardResponseDto.setRewardType(userReward.getReward().getRewardType());
        return rewardResponseDto;
    }

}
