package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.UserAchievementRequestDto;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.UserYearAchievement;
import com.royalrangers.repository.achievement.UserYearAchievementRepository;
import com.royalrangers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserYearAchievementService {

    @Autowired
    private UserYearAchievementRepository userYearAchievementRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private YearAchievementService yearAchievementService;

    public List<UserYearAchievement> findAllForUser() {
        return userYearAchievementRepository.findByUserId(userService.getAuthenticatedUserId());
    }

    public UserYearAchievement addUserYearAchievement(UserAchievementRequestDto params) {
        UserYearAchievement savedUserAchievement = new UserYearAchievement();
        String achievementStatus = params.getState();
        savedUserAchievement.setAchievementState(AchievementState.valueOf(achievementStatus));
        savedUserAchievement.setUser(userService.getUserById(userService.getAuthenticatedUserId()));
        Integer yearId = params.getId();
        savedUserAchievement.setYearAchievement(yearAchievementService.getYearAchievementById(yearId.longValue()));
        userYearAchievementRepository.saveAndFlush(savedUserAchievement);
        return savedUserAchievement;
    }

    public UserYearAchievement getUserYearAchievementById(Long id) {
        return userYearAchievementRepository.findOne(id);
    }

    public List<UserYearAchievement> getUserYearAchievementByAchievementId(Long achievementId) {
        return userYearAchievementRepository.findAllByYearAchievement(achievementId);
    }

    public void deleteUserYearAchievement(Long id) {
        userYearAchievementRepository.delete(id);
    }

    public UserYearAchievement editUserYearAchievement(UserAchievementRequestDto params, Long id) {
        UserYearAchievement savedUserAchievement = userYearAchievementRepository.findOne(id);
        savedUserAchievement.setUpdateDate(new Date());
        savedUserAchievement.setAchievementState(AchievementState.valueOf(params.getState()));
        Integer yearId = params.getId();
        savedUserAchievement.setYearAchievement(yearAchievementService.getYearAchievementById(yearId.longValue()));
        return userYearAchievementRepository.saveAndFlush(savedUserAchievement);
    }
}
