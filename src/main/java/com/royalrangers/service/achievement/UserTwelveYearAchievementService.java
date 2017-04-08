package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.UserAchievementBean;
import com.royalrangers.dto.achievement.UserAchievementRequestDto;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.UserTwelveYearAchievement;
import com.royalrangers.dto.achievement.UserTwelveYearAchievementBean;
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

    public List<UserTwelveYearAchievementBean> findAllForUser(){
        List<UserTwelveYearAchievement> list = userTwelveYearAchievementRepository.findByUserId(userService.getAuthenticatedUserId());
        List<UserTwelveYearAchievementBean> result = new ArrayList<>();
        for (UserTwelveYearAchievement item : list) {
            result.add(buildUserAchievementBean(item));
        }
        return result;
    }

    public void addUserTwelveYearAchievement(UserAchievementRequestDto params) {
        UserTwelveYearAchievement savedUserAchievement = new UserTwelveYearAchievement();
        savedUserAchievement.setCreateDate(new Date());
        savedUserAchievement.setUpdateDate(new Date());
        String achievementState = params.getState();
        savedUserAchievement.setAchievementState(AchievementState.valueOf(achievementState));
        savedUserAchievement.setUser(userService.getUserById(userService.getAuthenticatedUserId()));
        Integer twelveYearId = params.getId();
        savedUserAchievement.setTwelveYearAchievement(twelveYearAchievementService.getTwelveYearAchievementById(twelveYearId.longValue()));
        userTwelveYearAchievementRepository.saveAndFlush(savedUserAchievement);
    }

    public UserTwelveYearAchievementBean getUserTwelveYearAchievementById(Long id) {
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

    private UserTwelveYearAchievementBean buildUserAchievementBean(UserTwelveYearAchievement item) {
        UserTwelveYearAchievementBean userAchievementBean = new UserTwelveYearAchievementBean();
        userAchievementBean.setId(item.getId());
        userAchievementBean.setCreateDate(item.getCreateDate());
        userAchievementBean.setUpdateDate(item.getUpdateDate());
        userAchievementBean.setAchievementState(item.getAchievementState());
        UserAchievementBean userBean = UserService.buildUserAchievementBean(item.getUser());
        userAchievementBean.setUser(userBean);
        userAchievementBean.setTwelveYearAchievement(item.getTwelveYearAchievement());
        return userAchievementBean;
    }

}
