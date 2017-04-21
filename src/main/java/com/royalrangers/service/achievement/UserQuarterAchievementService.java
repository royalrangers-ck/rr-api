package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.UserAchievementRequestDto;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.UserQuarterAchievement;
import com.royalrangers.repository.achievement.UserQuarterAchievementRepository;
import com.royalrangers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserQuarterAchievementService {

    @Autowired
    private UserQuarterAchievementRepository userQuarterAchievementRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private QuarterAchievementService quarterAchievementService;

    public List<UserQuarterAchievement> findAllForUser() {
        return userQuarterAchievementRepository.findByUserId(userService.getAuthenticatedUserId());
     }

    public void addUserQuarterAchievement(UserAchievementRequestDto params) {
        UserQuarterAchievement savedUserAchievement = new UserQuarterAchievement();
        String achievementState = params.getState();
        savedUserAchievement.setAchievementState(AchievementState.valueOf(achievementState));
        savedUserAchievement.setUser(userService.getUserById(userService.getAuthenticatedUserId()));
        Integer quarterId = params.getId();
        savedUserAchievement.setQuarterAchievement(quarterAchievementService.getQuarterAchievementById(quarterId.longValue()));
        userQuarterAchievementRepository.saveAndFlush(savedUserAchievement);
    }

    public UserQuarterAchievement getUserQuarterAchievementById(Long id) {
        return userQuarterAchievementRepository.findOne(id);
    }

    public List<UserQuarterAchievement> getUserQuarterAchievementByAchievementId(Long achievementId) {
        List<UserQuarterAchievement> resultList =
                userQuarterAchievementRepository.findAllByQuarterAchievement(achievementId);
        return resultList;
    }

    public void deleteUserQuarterAchievement(Long id) {
        userQuarterAchievementRepository.delete(id);
    }

    public void editUserQuarterAchievement(UserAchievementRequestDto params, Long id) {
        UserQuarterAchievement savedUserAchievement = userQuarterAchievementRepository.findOne(id);
        savedUserAchievement.setUpdateDate(new Date());
        String achievementState = params.getState();
        savedUserAchievement.setAchievementState(AchievementState.valueOf(achievementState));
        Integer quarterId = params.getId();
        savedUserAchievement.setQuarterAchievement(quarterAchievementService.getQuarterAchievementById(quarterId.longValue()));
        userQuarterAchievementRepository.saveAndFlush(savedUserAchievement);
    }
}
