package com.royalrangers.service.achievement;

import com.royalrangers.bean.achievement.UserAchievementBean;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.UserQuarterAchievement;
import com.royalrangers.bean.achievement.QuarterAchievementBean;
import com.royalrangers.repository.achievement.UserQuarterAchievementRepository;
import com.royalrangers.repository.UserRepository;
import com.royalrangers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserQuarterAchievementService {

    @Autowired
    private UserQuarterAchievementRepository userQuarterAchievementRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private QuarterAchievementService quarterAchievementService;

    public List<QuarterAchievementBean> findAllForUser() {
        List<UserQuarterAchievement> list = userQuarterAchievementRepository.findByUserId(userService.getAuthenticatedUserId());
        List<QuarterAchievementBean> result = new ArrayList<>();
        for (UserQuarterAchievement item : list) {
            result.add(buildUserAchievementBean(item));
        }
        return result;
    }

    public void addUserQuarterAchievement(Map<String, Object> params) {
        UserQuarterAchievement savedUserAchievement = new UserQuarterAchievement();
        String achievementState = (String) params.get("state");
        savedUserAchievement.setAchievementState(AchievementState.valueOf(achievementState));
        savedUserAchievement.setUser(userService.getUserById(userService.getAuthenticatedUserId()));
        Integer quarterId = (Integer) params.get("quarterAchievementId");
        savedUserAchievement.setQuarterAchievement(quarterAchievementService.getQuarterAchievementById(quarterId.longValue()));
        userQuarterAchievementRepository.saveAndFlush(savedUserAchievement);
    }

    public QuarterAchievementBean getUserQuarterAchievementById(Long id) {
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

    public void editUserQuarterAchievement(Map<String, Object> params, Long id) {
        UserQuarterAchievement savedUserAchievement = userQuarterAchievementRepository.findOne(id);
        savedUserAchievement.setUpdateDate(new Date());
        String achievementState = (String) params.get("state");
        savedUserAchievement.setAchievementState(AchievementState.valueOf(achievementState));
        Integer quarterId = (Integer) params.get("quarterAchievementId");
        savedUserAchievement.setQuarterAchievement(quarterAchievementService.getQuarterAchievementById(quarterId.longValue()));
        userQuarterAchievementRepository.saveAndFlush(savedUserAchievement);
    }

    private QuarterAchievementBean buildUserAchievementBean(UserQuarterAchievement item) {
        QuarterAchievementBean userAchievementBean = new QuarterAchievementBean();
        userAchievementBean.setId(item.getId());
        UserAchievementBean userBean = UserService.buildUserAchievementBean(item.getUser());
        userAchievementBean.setId(userBean.getId());
        userAchievementBean.setUserFirstName(userBean.getFirstName());
        userAchievementBean.setUserLastName(userBean.getLastName());
        userAchievementBean.setUserPlatoonId(userBean.getPlatoonId());
        userAchievementBean.setUserAvatarUrl(userBean.getUserAvatarUrl());
        userAchievementBean.setQuarterAchievementId(item.getQuarterAchievement().getId());
        userAchievementBean.setQuarterAchievementName(item.getQuarterAchievement().getName());
        userAchievementBean.setQuarterAchievementDescription(item.getQuarterAchievement().getDescription());
        userAchievementBean.setQuarterAchievementLogoUrl(item.getQuarterAchievement().getLogoUrl());
        userAchievementBean.setAchievementState(item.getAchievementState());
        return userAchievementBean;
    }

}
