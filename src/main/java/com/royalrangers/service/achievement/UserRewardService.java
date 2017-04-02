package com.royalrangers.service.achievement;

import com.royalrangers.bean.achievement.UserRewardBean;
import com.royalrangers.model.achievement.UserReward;
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

    public List<UserRewardBean> getAllRewardForUser(){
        List<UserReward> userRewards = userRewardRepository.findByUserId(userService.getAuthenticatedUserId());
        List<UserRewardBean> result = new ArrayList<>();
        for (UserReward user : userRewards) {
            result.add(buildUserRewardBean(user));
        }
        return result;
    }

    public void addUserReward(Map<String, Object> params){
        UserReward savedUserReward = new UserReward();
        Integer rewardId = (Integer) params.get("rewardId");
        savedUserReward.setReward(rewardService.getRewardById(rewardId.longValue()));
        savedUserReward.setUser(userService.getUserById(userService.getAuthenticatedUserId()));
        userRewardRepository.saveAndFlush(savedUserReward);
    }

    public UserRewardBean getRewardById(Long id){
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

    private UserRewardBean buildUserRewardBean(UserReward userReward){
        UserRewardBean userRewardBean = new UserRewardBean();
        userRewardBean.setId(userReward.getId());
        userRewardBean.setCreateDate(userReward.getCreateDate());
        userRewardBean.setUpdateDate(userReward.getUpdateDate());
        userRewardBean.setUser(UserService.buildUserAchievementBean(userReward.getUser()));
        userRewardBean.setReward(userReward.getReward());
        return userRewardBean;
    }

}
