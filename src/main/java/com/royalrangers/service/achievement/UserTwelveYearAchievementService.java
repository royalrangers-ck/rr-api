package com.royalrangers.service.achievement;

import com.royalrangers.bean.achievement.UserAchievementBean;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.UserTwelveYearAchievement;
import com.royalrangers.bean.achievement.TwelveYearAchievementBean;
import com.royalrangers.repository.UserRepository;
import com.royalrangers.repository.achievement.UserTwelveYearAchievementRepository;
import com.royalrangers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    public List<TwelveYearAchievementBean> findAllForUser(){
        List<UserTwelveYearAchievement> list = userTwelveYearAchievementRepository.findByUserId(userService.getAuthenticatedUserId());
        List<TwelveYearAchievementBean> result = new ArrayList<>();
        for (UserTwelveYearAchievement item : list) {
            result.add(buildUserAchievementBean(item));
        }
        return result;
    }

    public void addUserTwelveYearAchievement(Map<String, Object> params) {
        UserTwelveYearAchievement savedUserAchievement = new UserTwelveYearAchievement();
        String achievementState = (String) params.get("state");
        savedUserAchievement.setAchievementState(AchievementState.valueOf(achievementState));
        savedUserAchievement.setUser(userService.getUserById(userService.getAuthenticatedUserId()));
        Integer twelveYearId = (Integer) params.get("twelveYearAchievementId");
        savedUserAchievement.setTwelveYearAchievement(twelveYearAchievementService.getTwelveYearAchievementById(twelveYearId.longValue()));
        userTwelveYearAchievementRepository.saveAndFlush(savedUserAchievement);
    }

    public TwelveYearAchievementBean getUserTwelveYearAchievementById(Long id) {
        UserTwelveYearAchievement user = userTwelveYearAchievementRepository.findOne(id);
        return buildUserAchievementBean(user);
    }

    public void delete(Long id) {
        userTwelveYearAchievementRepository.delete(id);
    }

    public void editUserTwelveYearAchievement(Map<String, Object> params, Long id) {
        UserTwelveYearAchievement editUserAchievement = userTwelveYearAchievementRepository.findOne(id);
        editUserAchievement.setUpdateDate(new Date());
        String achievementState = (String) params.get("state");
        editUserAchievement.setAchievementState(AchievementState.valueOf(achievementState));
        Integer twelveYearId = (Integer) params.get("twelveYearAchievementId");
        editUserAchievement.setTwelveYearAchievement(twelveYearAchievementService.getTwelveYearAchievementById(twelveYearId.longValue()));
        userTwelveYearAchievementRepository.saveAndFlush(editUserAchievement);
    }

    private TwelveYearAchievementBean buildUserAchievementBean(UserTwelveYearAchievement item) {
        TwelveYearAchievementBean userAchievementBean = new TwelveYearAchievementBean();
        userAchievementBean.setId(item.getId());
        userAchievementBean.setAchievementState(item.getAchievementState());
        UserAchievementBean userBean = UserService.buildUserAchievementBean(item.getUser());
        userAchievementBean.setUserId(userBean.getId());
        userAchievementBean.setUserFirstName(userBean.getFirstName());
        userAchievementBean.setUserLastName(userBean.getLastName());
        userAchievementBean.setUserPlatoonId(userBean.getPlatoonId());
        userAchievementBean.setUserAvatarUrl(userBean.getUserAvatarUrl());
        userAchievementBean.setTwelveYearAchievementId(item.getTwelveYearAchievement().getId());
        userAchievementBean.setTwelveYearAchievementName(item.getTwelveYearAchievement().getName());
        userAchievementBean.setTwelveYearAchievementDescription(item.getTwelveYearAchievement().getDescription());
        userAchievementBean.setTwelveYearAchievementLogoUrl(item.getTwelveYearAchievement().getLogoUrl());
        return userAchievementBean;
    }

}
