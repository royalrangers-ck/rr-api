package com.royalrangers.service.achievement;

import com.royalrangers.bean.achievement.RewardBean;
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
import java.util.Map;

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

    public List<RewardBean> getAllRewardForUser(){
        List<UserReward> userRewards = userRewardRepository.findByUserId(userService.getAuthenticatedUserId());
        List<RewardBean> result = new ArrayList<>();
        for (UserReward user : userRewards) {
            result.add(buildUserRewardBean(user));
        }
        return result;
    }

    public void addUserReward(Map<String, Object> params){
        UserReward savedUserReward = new UserReward();
        Integer rewardId = (Integer) params.get("rewardId");
        if(!rewardRepository.exists(rewardId.longValue())) {
            throw new AchievementException("Not found reward with id " + rewardId);
        }
        savedUserReward.setReward(rewardService.getRewardById(rewardId.longValue()));
        savedUserReward.setUser(userService.getUserById(userService.getAuthenticatedUserId()));
        userRewardRepository.saveAndFlush(savedUserReward);
    }

    public RewardBean getRewardById(Long id){
        UserReward userReward = userRewardRepository.findOne(id);
        return buildUserRewardBean(userReward);
    }

    public void deleteUserReward(Long id){
        userRewardRepository.delete(id);
    }

    public void editUserReward(Map<String, Object> params, Long id){
        UserReward savedUserReward = userRewardRepository.findOne(id);
        savedUserReward.setUpdateDate(new Date());
        Integer rewardId = (Integer) params.get("rewardId");
        savedUserReward.setReward(rewardService.getRewardById(rewardId.longValue()));
        userRewardRepository.saveAndFlush(savedUserReward);
    }

    private RewardBean buildUserRewardBean(UserReward userReward){
        RewardBean rewardBean = new RewardBean();
        rewardBean.setId(userReward.getId());
        rewardBean.setUserId(userReward.getUser().getId());
        rewardBean.setUserFirstName(userReward.getUser().getFirstName());
        rewardBean.setUserLastName(userReward.getUser().getLastName());
        rewardBean.setUserPlatoonId(userReward.getUser().getPlatoon().getId());
        rewardBean.setUserAvatarUrl(userReward.getUser().getAvatarUrl());
        rewardBean.setRewardId(userReward.getReward().getId());
        rewardBean.setRewardName(userReward.getReward().getName());
        rewardBean.setRewardDescription(userReward.getReward().getDescription());
        rewardBean.setRewardLogoUrl(userReward.getReward().getLogoUrl());
        rewardBean.setRewardMark(userReward.getReward().getRewardMark());
        rewardBean.setRewardType(userReward.getReward().getRewardType());
        return rewardBean;
    }

}
