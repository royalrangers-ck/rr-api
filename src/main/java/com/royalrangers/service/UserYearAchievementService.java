package com.royalrangers.service;

import com.royalrangers.enums.achivement.AchievementStatus;
import com.royalrangers.exception.UserRepositoryException;
import com.royalrangers.model.achievement.UserYearAchievement;
import com.royalrangers.repository.UserRepository;
import com.royalrangers.repository.UserYearAchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserYearAchievementService {

    @Autowired
    private UserYearAchievementRepository userYearAchievementRepository;

    @Autowired
    private UserRepository userRepository;

    public List<UserYearAchievement> getAllUserYearAchievements() {
        return userYearAchievementRepository.findAll();
    }

    public void addUserYearAchievement(Map<String, Object> params) {
        UserYearAchievement savedUserAchievement = new UserYearAchievement();
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
        Integer yearId = (Integer) params.get("yearAchievementId");
        savedUserAchievement.setYearAchievementId(yearId.longValue());
        userYearAchievementRepository.saveAndFlush(savedUserAchievement);
    }

    public UserYearAchievement getUserYearAchievementById(Long id) {
        return userYearAchievementRepository.findOne(id);
    }

    public void deleteUserYearAchievement(Long id) {
        userYearAchievementRepository.delete(id);
    }

    public UserYearAchievement editUserYearAchievement(Map<String, Object> params, Long id) {
        UserYearAchievement savedUserAchievement = getUserYearAchievementById(id);
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
        Integer yearId = (Integer) params.get("yearAchievementId");
        savedUserAchievement.setYearAchievementId(yearId.longValue());
        return userYearAchievementRepository.saveAndFlush(savedUserAchievement);
    }

}
