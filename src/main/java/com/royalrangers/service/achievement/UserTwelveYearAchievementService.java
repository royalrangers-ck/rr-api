package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.UserAchievementRequestDto;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.UserTwelveYearAchievement;
import com.royalrangers.repository.achievement.UserTwelveYearAchievementRepository;
import com.royalrangers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserTwelveYearAchievementService {

    @Autowired
    private UserTwelveYearAchievementRepository userTwelveYearAchievementRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TwelveYearAchievementService twelveYearAchievementService;

    public List<UserTwelveYearAchievement> findAllForUser(){
        return userTwelveYearAchievementRepository.findByUserId(userService.getAuthenticatedUserId());
    }

    public void addUserTwelveYearAchievement(UserAchievementRequestDto params) {
        UserTwelveYearAchievement savedUserAchievement = new UserTwelveYearAchievement();
        String achievementState = params.getState();
        savedUserAchievement.setAchievementState(AchievementState.valueOf(achievementState));
        savedUserAchievement.setUser(userService.getUserById(userService.getAuthenticatedUserId()));
        Integer twelveYearId = params.getId();
        savedUserAchievement.setTwelveYearAchievement(twelveYearAchievementService.getTwelveYearAchievementById(twelveYearId.longValue()));
        userTwelveYearAchievementRepository.saveAndFlush(savedUserAchievement);
    }

    public UserTwelveYearAchievement getUserTwelveYearAchievementById(Long id) {
        return userTwelveYearAchievementRepository.findOne(id);
    }

    public List<UserTwelveYearAchievement> getUserTwelveYearAchievementByAchievementId(Long achievementId) {
        return userTwelveYearAchievementRepository.findAllByTwelveYearAchievement(achievementId);
    }

    public void delete(Long id) {
        userTwelveYearAchievementRepository.delete(id);
    }

    public void editUserTwelveYearAchievement(UserAchievementRequestDto params, Long id) {
        UserTwelveYearAchievement editUserAchievement = userTwelveYearAchievementRepository.findOne(id);
        editUserAchievement.setUpdateDate(new Date());
        String achievementState = params.getState();
        editUserAchievement.setAchievementState(AchievementState.valueOf(achievementState));
        Integer twelveYearId = params.getId();
        editUserAchievement.setTwelveYearAchievement(twelveYearAchievementService.getTwelveYearAchievementById(twelveYearId.longValue()));
        userTwelveYearAchievementRepository.saveAndFlush(editUserAchievement);
    }
}
