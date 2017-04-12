package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.UserAchievementRequestDto;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.UserTwelveYearAchievement;
import com.royalrangers.dto.achievement.UserTwelveYearResponseDto;
import com.royalrangers.repository.UserRepository;
import com.royalrangers.repository.achievement.UserTwelveYearAchievementRepository;
import com.royalrangers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserTwelveYearAchievementService {

    @Autowired
    private UserTwelveYearAchievementRepository userTwelveYearAchievementRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TwelveYearAchievementService twelveYearAchievementService;

    public List<UserTwelveYearResponseDto> findAllForUser(){
        List<UserTwelveYearAchievement> list = userTwelveYearAchievementRepository.findByUserId(userService.getAuthenticatedUserId());
        List<UserTwelveYearResponseDto> result = new ArrayList<>();
        for (UserTwelveYearAchievement item : list) {
            result.add(buildUserAchievementBean(item));
        }
        return result;
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

    public UserTwelveYearResponseDto getUserTwelveYearAchievementById(Long id) {
        UserTwelveYearAchievement user = userTwelveYearAchievementRepository.findOne(id);
        return buildUserAchievementBean(user);
    }

    public List<UserTwelveYearAchievement> getUserTwelveYearAchievementByAchievementId(Long achievementId) {
        List<UserTwelveYearAchievement> resultList =
                userTwelveYearAchievementRepository.findAllByTwelveYearAchievement(achievementId);
        return resultList;
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

    private UserTwelveYearResponseDto buildUserAchievementBean(UserTwelveYearAchievement item) {
        UserTwelveYearResponseDto userAchievementBean = new UserTwelveYearResponseDto();
        userAchievementBean.setId(item.getId());
        userAchievementBean.setCreateDate(item.getCreateDate());
        userAchievementBean.setUpdateDate(item.getUpdateDate());
        userAchievementBean.setAchievementState(item.getAchievementState());
        userAchievementBean.setUser(UserService.buildUserAchievementBean(item.getUser()));
        userAchievementBean.setTwelveYearAchievementName(item.getTwelveYearAchievement().getName());
        userAchievementBean.setTwelveYearAchievementDescription(item.getTwelveYearAchievement().getDescription());
        userAchievementBean.setTwelveYearAchievementLogoUrl(item.getTwelveYearAchievement().getLogoUrl());
        return userAchievementBean;
    }

}
