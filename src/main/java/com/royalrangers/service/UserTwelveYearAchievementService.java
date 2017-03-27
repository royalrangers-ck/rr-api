package com.royalrangers.service;

import com.royalrangers.enums.achivement.AchievementStatus;
import com.royalrangers.exception.UserRepositoryException;
import com.royalrangers.model.achievement.TwelveYearAchievement;
import com.royalrangers.model.achievement.UserTwelveYearAchievement;
import com.royalrangers.repository.UserRepository;
import com.royalrangers.repository.UserTwelveYearAchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserTwelveYearAchievementService {

    @Autowired
    private UserTwelveYearAchievementRepository userTwelveYearAchievementRepository;

    @Autowired
    private UserRepository userRepository;

    public List<UserTwelveYearAchievement> getAllUserWithAchievement() {
        return userTwelveYearAchievementRepository.findAll();
    }

    public void addUserTwelveYearAchievement(Map<String, Object> params) {
        UserTwelveYearAchievement savedUserAchievement = new UserTwelveYearAchievement();
        savedUserAchievement.setCreateDate(new Date());
        savedUserAchievement.setUpdateDate(new Date());
        Integer achievementStatus = (Integer) params.get("status");
        savedUserAchievement.setAchievementStatus(AchievementStatus.values()[achievementStatus]);
        Integer userId = (Integer) params.get("userId");
        Integer userReviewerId = (Integer) params.get("reviewerId");
        if (!userRepository.exists(userId.longValue()) || !userRepository.exists(userReviewerId.longValue())) {
            throw new UserRepositoryException("Not found user with id " + userId);
        }
        savedUserAchievement.setUserId(userId.longValue());
        savedUserAchievement.setReviewerId(userReviewerId.longValue());
        Integer twelveYearId = (Integer) params.get("twelveYearAchievementId");
        savedUserAchievement.setTwelveYearAchievementId(twelveYearId.longValue());
        userTwelveYearAchievementRepository.saveAndFlush(savedUserAchievement);
    }

    public UserTwelveYearAchievement getUserTwelveYearAchievementById(Long id) {
        return userTwelveYearAchievementRepository.findOne(id);
    }

    public void delete(Long id) {
        userTwelveYearAchievementRepository.delete(id);
    }

    public UserTwelveYearAchievement editTwelveYearAchievement(Map<String, Object> params, Long id) {
        UserTwelveYearAchievement editUserAchievement = getUserTwelveYearAchievementById(id);
        editUserAchievement.setUpdateDate(new Date());
        Integer userId = (Integer) params.get("userId");
        Integer userReviewerId = (Integer) params.get("reviewerId");
        if (!userRepository.exists(userId.longValue()) || !userRepository.exists(userReviewerId.longValue())) {
            throw new UserRepositoryException("Not found user with id " + userId);
        }
        editUserAchievement.setUserId(userId.longValue());
        editUserAchievement.setReviewerId(userReviewerId.longValue());
        Integer twelveYearId = (Integer) params.get("twelveYearAchievementId");
        editUserAchievement.setTwelveYearAchievementId(twelveYearId.longValue());
        return userTwelveYearAchievementRepository.saveAndFlush(editUserAchievement);
    }

}
