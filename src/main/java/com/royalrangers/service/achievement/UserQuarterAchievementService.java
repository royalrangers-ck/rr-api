package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.UserAchievementBean;
import com.royalrangers.dto.achievement.UserAchievementRequestDto;
import com.royalrangers.dto.achievement.UserQuarterAchievementBean;
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

    public List<UserQuarterAchievementBean> findAllForUser() {
        List<UserQuarterAchievement> list = userQuarterAchievementRepository.findByUserId(userService.getAuthenticatedUserId());
        List<UserQuarterAchievementBean> result = new ArrayList<>();
        for (UserQuarterAchievement item : list) {
            result.add(buildUserAchievementBean(item));
        }
        return result;
    }

    public void addUserQuarterAchievement(UserAchievementRequestDto params) {
        UserQuarterAchievement savedUserAchievement = new UserQuarterAchievement();
        savedUserAchievement.setCreateDate(new Date());
        savedUserAchievement.setUpdateDate(new Date());
        String achievementState = params.getState();
        savedUserAchievement.setAchievementState(AchievementState.valueOf(achievementState));
        savedUserAchievement.setUser(userService.getUserById(userService.getAuthenticatedUserId()));
        Integer quarterId = params.getId();
        savedUserAchievement.setQuarterAchievement(quarterAchievementService.getQuarterAchievementById(quarterId.longValue()));
        userQuarterAchievementRepository.saveAndFlush(savedUserAchievement);
    }

    public UserQuarterAchievementBean getUserQuarterAchievementById(Long id) {
        UserQuarterAchievement user = userQuarterAchievementRepository.findOne(id);
        return buildUserAchievementBean(user);
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

    private UserQuarterAchievementBean buildUserAchievementBean(UserQuarterAchievement item) {
        UserQuarterAchievementBean userAchievementBean = new UserQuarterAchievementBean();
        userAchievementBean.setId(item.getId());
        userAchievementBean.setCreateDate(item.getCreateDate());
        userAchievementBean.setUpdateDate(item.getUpdateDate());
        userAchievementBean.setAchievementState(item.getAchievementState());
        UserAchievementBean userBean = UserService.buildUserAchievementBean(item.getUser());
        userAchievementBean.setUser(userBean);
        userAchievementBean.setQuarterAchievement(item.getQuarterAchievement());
        return userAchievementBean;
    }

}
