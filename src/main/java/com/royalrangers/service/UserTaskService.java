package com.royalrangers.service;

import com.royalrangers.enums.achivement.AchievementStatus;
import com.royalrangers.exception.UserRepositoryException;
import com.royalrangers.model.User;
import com.royalrangers.model.achievement.UserTask;
import com.royalrangers.repository.UserRepository;
import com.royalrangers.repository.UserTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserTaskService {

    @Autowired
    private UserTaskRepository userTaskRepository;

    @Autowired
    private UserRepository userRepository;

    public List<UserTask> getAllUserTaskAchievement() {
        return userTaskRepository.findAll();
    }

    public void addUserTaskAchievement(Map<String, Object> params) {
        UserTask savedUserAchievement = new UserTask();
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
        Integer taskId = (Integer) params.get("taskId");
        savedUserAchievement.setTaskId(taskId.longValue());
        userTaskRepository.saveAndFlush(savedUserAchievement);
    }

    public UserTask getUserTaskAchievementById(Long id) {
        return userTaskRepository.findOne(id);
    }

    public void deleteUserTaskAchievement(Long id) {
        userTaskRepository.delete(id);
    }

    public UserTask editUserTaskAchievement(Map<String, Object> params, Long id) {
        UserTask savedUserAchievement = getUserTaskAchievementById(id);
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
        Integer taskId = (Integer) params.get("taskId");
        savedUserAchievement.setTaskId(taskId.longValue());
        return userTaskRepository.saveAndFlush(savedUserAchievement);
    }

}
