package com.royalrangers.service;

import com.royalrangers.enums.achivement.AchievementStatus;
import com.royalrangers.exception.UserRepositoryException;
import com.royalrangers.model.achievement.UserQuarterAchievement;
import com.royalrangers.repository.UserQuarterAchievementRepository;
import com.royalrangers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserQuarterAchievementService {

    @Autowired
    private UserQuarterAchievementRepository userQuarterAchievementRepository;

    @Autowired
    private UserRepository userRepository;

    public List<UserQuarterAchievement> getAllUserQuarterAchievements() {
        return userQuarterAchievementRepository.findAll();
    }

    public void addUserQuarterAchievement(Map<String, Object> params) {
        UserQuarterAchievement savedUserAchievement = new UserQuarterAchievement();
        savedUserAchievement.setCreateDate(new Date());
        savedUserAchievement.setUpdateDate(new Date());
        Integer achievementStatus = (Integer) params.get("status");
        savedUserAchievement.setAchievementStatus(AchievementStatus.values()[achievementStatus]);
        Integer userId = (Integer) params.get("userId");
        Integer userReviewerId = (Integer) params.get("reviewerId");
        if (!userRepository.exists(userId.longValue()) || !userRepository.exists(userReviewerId.longValue())) {
            throw new UserRepositoryException("Not found user with id");
        }
        savedUserAchievement.setUserId(userId.longValue());
        savedUserAchievement.setReviewerId(userReviewerId.longValue());
        Integer quarterId = (Integer) params.get("quarterAchievementId");
        savedUserAchievement.setQuarterAchievementId(quarterId.longValue());
        userQuarterAchievementRepository.saveAndFlush(savedUserAchievement);
    }

    public UserQuarterAchievement getUserQuarterAchievementById(Long id) {
        return userQuarterAchievementRepository.findOne(id);
    }

    public void deleteUserQuarterAchievement(Long id) {
        userQuarterAchievementRepository.delete(id);
    }

    public UserQuarterAchievement editUserQuarterAchievement(Map<String, Object> params, Long id) {
        UserQuarterAchievement savedUserAchievement = getUserQuarterAchievementById(id);
        savedUserAchievement.setUpdateDate(new Date());
        Integer achievementStatus = (Integer) params.get("status");
        savedUserAchievement.setAchievementStatus(AchievementStatus.values()[achievementStatus]);
        Integer userId = (Integer) params.get("userId");
        Integer userReviewerId = (Integer) params.get("reviewerId");
        if (!userRepository.exists(userId.longValue()) || !userRepository.exists(userReviewerId.longValue())) {
            throw new UserRepositoryException("Not found user with id");
        }
        savedUserAchievement.setUserId(userId.longValue());
        savedUserAchievement.setReviewerId(userReviewerId.longValue());
        Integer quarterId = (Integer) params.get("quarterAchievementId");
        savedUserAchievement.setQuarterAchievementId(quarterId.longValue());
        return userQuarterAchievementRepository.saveAndFlush(savedUserAchievement);
    }

}
