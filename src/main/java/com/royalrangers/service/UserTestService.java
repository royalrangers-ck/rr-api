package com.royalrangers.service;

import com.royalrangers.enums.achivement.AchievementStatus;
import com.royalrangers.exception.UserRepositoryException;
import com.royalrangers.model.achievement.UserTest;
import com.royalrangers.repository.UserRepository;
import com.royalrangers.repository.UserTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserTestService {

    @Autowired
    private UserTestRepository userTestRepository;

    @Autowired
    private UserRepository userRepository;

    public List<UserTest> getAllUserTestAchievement() {
        return userTestRepository.findAll();
    }

    public void addUserTestAchievement(Map<String, Object> params) {
        UserTest savedUserAchievement = new UserTest();
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
        Integer testId = (Integer) params.get("testId");
        savedUserAchievement.setTestId(testId.longValue());
        userTestRepository.saveAndFlush(savedUserAchievement);
    }

    public UserTest getUserTestAchievementById(Long id) {
        return userTestRepository.findOne(id);
    }

    public void deleteUserTestAchievement(Long id) {
        userTestRepository.delete(id);
    }

    public UserTest editUserTestAchievement(Map<String, Object> params, Long id) {
        UserTest savedUserAchievement = getUserTestAchievementById(id);
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
        Integer testId = (Integer) params.get("testId");
        savedUserAchievement.setTestId(testId.longValue());
        return userTestRepository.saveAndFlush(savedUserAchievement);
    }

}
