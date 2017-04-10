package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.UserAchievementRequestDto;
import com.royalrangers.dto.achievement.UserYearResponseDto;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.UserYearAchievement;
import com.royalrangers.repository.achievement.UserYearAchievementRepository;
import com.royalrangers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<UserYearResponseDto> findAllForUser() {
        List<UserYearAchievement> list = userYearAchievementRepository.findByUserId(userService.getAuthenticatedUserId());
        List<UserYearResponseDto> result = new ArrayList<>();
        for (UserYearAchievement item : list) {
            result.add(buildUserAchievementBean(item));
        }
        return result;
    }

    public void addUserYearAchievement(UserAchievementRequestDto params) {
        UserYearAchievement savedUserAchievement = new UserYearAchievement();
        String achievementStatus = params.getState();
        savedUserAchievement.setAchievementState(AchievementState.valueOf(achievementStatus));
        savedUserAchievement.setUser(userService.getUserById(userService.getAuthenticatedUserId()));
        Integer yearId = params.getId();
        savedUserAchievement.setYearAchievement(yearAchievementService.getYearAchievementById(yearId.longValue()));
        userYearAchievementRepository.saveAndFlush(savedUserAchievement);
    }

    public UserYearResponseDto getUserYearAchievementById(Long id) {
        UserYearAchievement user = userYearAchievementRepository.findOne(id);
        return buildUserAchievementBean(user);
    }

    public List<UserYearAchievement> getUserYearAchievementByAchievementId(Long achievementId) {
        List<UserYearAchievement> resultList =
                userYearAchievementRepository.findAllByYearAchievement(achievementId);
        return resultList;
    }

    public void deleteUserYearAchievement(Long id) {
        userYearAchievementRepository.delete(id);
    }

    public void editUserYearAchievement(UserAchievementRequestDto params, Long id) {
        UserYearAchievement savedUserAchievement = userYearAchievementRepository.findOne(id);
        savedUserAchievement.setUpdateDate(new Date());
        savedUserAchievement.setAchievementState(AchievementState.valueOf(params.getState()));
        Integer yearId = params.getId();
        savedUserAchievement.setYearAchievement(yearAchievementService.getYearAchievementById(yearId.longValue()));
        userYearAchievementRepository.saveAndFlush(savedUserAchievement);
    }

    private UserYearResponseDto buildUserAchievementBean(UserYearAchievement item) {
        UserYearResponseDto userAchievementBean = new UserYearResponseDto();
        userAchievementBean.setId(item.getId());
        userAchievementBean.setCreateDate(item.getCreateDate());
        userAchievementBean.setUpdateDate(item.getUpdateDate());
        userAchievementBean.setAchievementState(item.getAchievementState());
        userAchievementBean.setUser(UserService.buildUserAchievementBean(item.getUser()));
        userAchievementBean.setYearAchievementName(item.getYearAchievement().getName());
        userAchievementBean.setYearAchievementDescription(item.getYearAchievement().getDescription());
        userAchievementBean.setYearAchievementLogoUrl(item.getYearAchievement().getLogoUrl());
        return userAchievementBean;
    }

}
