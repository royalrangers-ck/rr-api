package com.royalrangers.service;

import com.royalrangers.enums.achivement.AchievementStatus;
import com.royalrangers.exception.UserRepositoryException;
import com.royalrangers.model.achievement.UserThreeYearAchievement;
import com.royalrangers.repository.UserRepository;
import com.royalrangers.repository.UserThreeYearAchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserThreeYearAchievementService {

    @Autowired
    private UserThreeYearAchievementRepository userThreeYearAchievementRepository;

    @Autowired
    private UserRepository userRepository;

    public List<UserThreeYearAchievement> getAllUserThreeYearAchievements(){
        return userThreeYearAchievementRepository.findAll();
    }

    public void addUserThreeYearAchievement(Map<String, Object> params){
        UserThreeYearAchievement savedUserAchievement = new UserThreeYearAchievement();
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
        Integer threeYearId = (Integer) params.get("threeYearAchievementId");
        savedUserAchievement.setThreeYearAchievementId(threeYearId.longValue());
        userThreeYearAchievementRepository.saveAndFlush(savedUserAchievement);
    }

    public UserThreeYearAchievement getUserThreeYearAchievementById(Long id){
        return userThreeYearAchievementRepository.findOne(id);
    }

    public void deleteUserThreeYearAchievement(Long id){
        userThreeYearAchievementRepository.delete(id);
    }

    public UserThreeYearAchievement editUserThreeYearAchievement(Map<String, Object> params, Long id){
        UserThreeYearAchievement savedUserAchievement = getUserThreeYearAchievementById(id);
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
        Integer threeYearId = (Integer) params.get("threeYearAchievementId");
        savedUserAchievement.setThreeYearAchievementId(threeYearId.longValue());
        return userThreeYearAchievementRepository.saveAndFlush(savedUserAchievement);
    }

}
