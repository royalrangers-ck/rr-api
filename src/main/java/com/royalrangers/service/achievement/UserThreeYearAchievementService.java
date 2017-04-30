package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.UserAchievementRequestDto;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.UserThreeYearAchievement;
import com.royalrangers.repository.achievement.UserThreeYearAchievementRepository;
import com.royalrangers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserThreeYearAchievementService {

    @Autowired
    private UserThreeYearAchievementRepository userThreeYearAchievementRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ThreeYearAchievementService threeYearAchievementService;

    public List<UserThreeYearAchievement> findAllForUser() {
        return userThreeYearAchievementRepository.findByUserId(userService.getAuthenticatedUserId());
    }

    public void addUserThreeYearAchievement(UserAchievementRequestDto params) {
        UserThreeYearAchievement savedUserAchievement = new UserThreeYearAchievement();
        String achievementState = params.getState();
        savedUserAchievement.setAchievementState(AchievementState.valueOf(achievementState));
        savedUserAchievement.setUser(userService.getUserById(userService.getAuthenticatedUserId()));
        Integer threeYearId = params.getId();
        savedUserAchievement.setThreeYearAchievement(threeYearAchievementService.getThreeYearAchievementById(threeYearId.longValue()));
        userThreeYearAchievementRepository.saveAndFlush(savedUserAchievement);
    }

    public UserThreeYearAchievement getUserThreeYearAchievementById(Long id) {
        return userThreeYearAchievementRepository.findOne(id);
    }

    public List<UserThreeYearAchievement> getUserThreeYearAchievementByAchievementId(Long achievementId) {
        return userThreeYearAchievementRepository.findAllByThreeYearAchievement(achievementId);
    }

    public void deleteUserThreeYearAchievement(Long id) {
        userThreeYearAchievementRepository.delete(id);
    }

    public void editUserThreeYearAchievement(UserAchievementRequestDto params, Long id) {
        UserThreeYearAchievement savedUserAchievement = userThreeYearAchievementRepository.findOne(id);
        savedUserAchievement.setUpdateDate(new Date());
        String achievementState = params.getState();
        savedUserAchievement.setAchievementState(AchievementState.valueOf(achievementState));
        savedUserAchievement.setUser(userService.getUserById(userService.getAuthenticatedUserId()));
        Integer threeYearId = params.getId();
        savedUserAchievement.setThreeYearAchievement(threeYearAchievementService.getThreeYearAchievementById(threeYearId.longValue()));
        userThreeYearAchievementRepository.saveAndFlush(savedUserAchievement);
    }
}
