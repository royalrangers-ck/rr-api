package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.UserAchievementRequestDto;
import com.royalrangers.dto.achievement.UserQuarterResponseDto;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.UserQuarterAchievement;
import com.royalrangers.repository.achievement.UserQuarterAchievementRepository;
import com.royalrangers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<UserQuarterResponseDto> findAllForUser() {
        List<UserQuarterAchievement> list = userQuarterAchievementRepository.findByUserId(userService.getAuthenticatedUserId());
        List<UserQuarterResponseDto> result = new ArrayList<>();
        for (UserQuarterAchievement item : list) {
            result.add(buildUserAchievementBean(item));
        }
        return result;
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

    public UserQuarterResponseDto getUserQuarterAchievementById(Long id) {
        UserQuarterAchievement user = userQuarterAchievementRepository.findOne(id);
        return buildUserAchievementBean(user);
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

    private UserQuarterResponseDto buildUserAchievementBean(UserQuarterAchievement item) {
        UserQuarterResponseDto userAchievementBean = new UserQuarterResponseDto();
        userAchievementBean.setId(item.getId());
        userAchievementBean.setUser(UserService.buildUserAchievementBean(item.getUser()));
        userAchievementBean.setQuarterAchievementName(item.getQuarterAchievement().getName());
        userAchievementBean.setQuarterAchievementDescription(item.getQuarterAchievement().getDescription());
        userAchievementBean.setQuarterAchievementLogoUrl(item.getQuarterAchievement().getLogoUrl());
        return userAchievementBean;
    }

}
