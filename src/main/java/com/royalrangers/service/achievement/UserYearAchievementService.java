package com.royalrangers.service.achievement;

import com.royalrangers.bean.achievement.UserAchievementBean;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.UserYearAchievement;
import com.royalrangers.bean.achievement.YearAchievementBean;
import com.royalrangers.repository.UserRepository;
import com.royalrangers.repository.achievement.UserYearAchievementRepository;
import com.royalrangers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserYearAchievementService {

    @Autowired
    private UserYearAchievementRepository userYearAchievementRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private YearAchievementService yearAchievementService;

    public List<YearAchievementBean> findAllForUser() {
        List<UserYearAchievement> list = userYearAchievementRepository.findByUserId(userService.getAuthenticatedUserId());
        List<YearAchievementBean> result = new ArrayList<>();
        for (UserYearAchievement item : list) {
            result.add(buildUserAchievementBean(item));
        }
        return result;
    }

    public void addUserYearAchievement(Map<String, Object> params) {
        UserYearAchievement savedUserAchievement = new UserYearAchievement();
        String achievementStatus = (String) params.get("state");
        savedUserAchievement.setAchievementState(AchievementState.valueOf(achievementStatus));
        savedUserAchievement.setUser(userService.getUserById(userService.getAuthenticatedUserId()));
        Integer yearId = (Integer) params.get("yearAchievementId");
        savedUserAchievement.setYearAchievement(yearAchievementService.getYearAchievementById(yearId.longValue()));
        userYearAchievementRepository.saveAndFlush(savedUserAchievement);
    }

    public YearAchievementBean getUserYearAchievementById(Long id) {
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

    public void editUserYearAchievement(Map<String, Object> params, Long id) {
        UserYearAchievement savedUserAchievement = userYearAchievementRepository.findOne(id);
        savedUserAchievement.setUpdateDate(new Date());
        String achievementStatus = (String) params.get("state");
        savedUserAchievement.setAchievementState(AchievementState.valueOf(achievementStatus));
        Integer yearId = (Integer) params.get("yearAchievementId");
        savedUserAchievement.setYearAchievement(yearAchievementService.getYearAchievementById(yearId.longValue()));
        userYearAchievementRepository.saveAndFlush(savedUserAchievement);
    }

    private YearAchievementBean buildUserAchievementBean(UserYearAchievement item) {
        YearAchievementBean userAchievementBean = new YearAchievementBean();
        userAchievementBean.setId(item.getId());
        userAchievementBean.setAchievementState(item.getAchievementState());
        UserAchievementBean userBean = UserService.buildUserAchievementBean(item.getUser());
        userAchievementBean.setUserId(userBean.getId());
        userAchievementBean.setUserFirstName(userBean.getFirstName());
        userAchievementBean.setUserLastName(userBean.getLastName());
        userAchievementBean.setUserPlatoonId(userBean.getPlatoonId());
        userAchievementBean.setUserAvatarUrl(userBean.getUserAvatarUrl());
        userAchievementBean.setYearAchievementId(item.getYearAchievement().getId());
        userAchievementBean.setYearAchievementName(item.getYearAchievement().getName());
        userAchievementBean.setYearAchievementDescription(item.getYearAchievement().getDescription());
        userAchievementBean.setYearAchievementLogoUrl(item.getYearAchievement().getLogoUrl());
        return userAchievementBean;
    }

}
