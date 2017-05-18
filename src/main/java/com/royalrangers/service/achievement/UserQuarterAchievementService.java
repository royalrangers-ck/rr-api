package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.UserAchievementRequestDto;
import com.royalrangers.enums.UserAgeGroup;
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

    public void addUserQuarterAchievement(UserAgeGroup userAgeGroup) {
        UserQuarterAchievement savedUserAchievement = new UserQuarterAchievement();
        savedUserAchievement.setUserAgeGroup(userAgeGroup);
        savedUserAchievement.setAchievementState(AchievementState.IN_PROGRESS);
        savedUserAchievement.setUser(userService.getUserById(userService.getAuthenticatedUserId()));
        Integer quarterId;
        if (findAllForUser().size() == 0) {
            quarterId = 1;
        } else {
            quarterId = findAllForUser().size() + 1;
        }
        savedUserAchievement.setQuarterAchievement(quarterAchievementService.getQuarterAchievementById(quarterId.longValue()));
        savedUserAchievement.getQuarterAchievement().setUserAgeGroup(userAgeGroup);
        userQuarterAchievementRepository.saveAndFlush(savedUserAchievement);
    }

    public UserQuarterAchievement getUserQuarterAchievementById(Long id) {
        return userQuarterAchievementRepository.findOne(id);
    }

    public List<UserQuarterAchievement> getUserQuarterAchievementByAchievementId(Long achievementId) {
        return userQuarterAchievementRepository.findAllByQuarterAchievement(achievementId);
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

    public void autoEditQuarterAchievement(AchievementState achievementState, UserAgeGroup userAgeGroup) {
        List<UserQuarterAchievement> userQuarterAchievementList = userQuarterAchievementRepository.findByUserAgeGroup(userAgeGroup);
        UserQuarterAchievement savedUserAchievement = userQuarterAchievementList.get(userQuarterAchievementList.size() - 1);
        savedUserAchievement.setUpdateDate(new Date());
        savedUserAchievement.setAchievementState(achievementState);
        userQuarterAchievementRepository.saveAndFlush(savedUserAchievement);
    }
}
