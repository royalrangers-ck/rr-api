package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.UserAchievementBean;
import com.royalrangers.dto.achievement.UserAchievementRequestDTO;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.UserThreeYearAchievement;
import com.royalrangers.dto.achievement.UserThreeYearAchievementBean;
import com.royalrangers.repository.UserRepository;
import com.royalrangers.repository.achievement.UserThreeYearAchievementRepository;
import com.royalrangers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserThreeYearAchievementService {

    @Autowired
    private UserThreeYearAchievementRepository userThreeYearAchievementRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ThreeYearAchievementService threeYearAchievementService;

    public List<UserThreeYearAchievementBean> findAllForUser() {
        List<UserThreeYearAchievement> list = userThreeYearAchievementRepository.findByUserId(userService.getAuthenticatedUserId());
        List<UserThreeYearAchievementBean> result = new ArrayList<>();
        for (UserThreeYearAchievement item : list) {
            UserThreeYearAchievementBean userAchievementBean = new UserThreeYearAchievementBean();
            userAchievementBean.setId(item.getId());
            userAchievementBean.setCreateDate(item.getCreateDate());
            userAchievementBean.setUpdateDate(item.getUpdateDate());
            userAchievementBean.setAchievementState(item.getAchievementState());
            UserAchievementBean userBean = UserService.buildUserAchievementBean(item.getUser());
            userAchievementBean.setUser(userBean);
            userAchievementBean.setThreeYearAchievement(item.getThreeYearAchievement());
            result.add(userAchievementBean);
        }
        return result;
    }

    public void addUserThreeYearAchievement(UserAchievementRequestDTO params) {
        UserThreeYearAchievement savedUserAchievement = new UserThreeYearAchievement();
        savedUserAchievement.setCreateDate(new Date());
        savedUserAchievement.setUpdateDate(new Date());
        String achievementState = params.getState();
        savedUserAchievement.setAchievementState(AchievementState.valueOf(achievementState));
        savedUserAchievement.setUser(userService.getUserById(userService.getAuthenticatedUserId()));
        Integer threeYearId = params.getId();
        savedUserAchievement.setThreeYearAchievement(threeYearAchievementService.getThreeYearAchievementById(threeYearId.longValue()));
        userThreeYearAchievementRepository.saveAndFlush(savedUserAchievement);
    }

    public UserThreeYearAchievementBean getUserThreeYearAchievementById(Long id) {
        UserThreeYearAchievement user = userThreeYearAchievementRepository.findOne(id);
        UserThreeYearAchievementBean userYearAchievementBean = new UserThreeYearAchievementBean();
        userYearAchievementBean.setId(user.getId());
        userYearAchievementBean.setCreateDate(user.getCreateDate());
        userYearAchievementBean.setUpdateDate(user.getUpdateDate());
        userYearAchievementBean.setAchievementState(user.getAchievementState());
        UserAchievementBean userBean = UserService.buildUserAchievementBean(user.getUser());
        userYearAchievementBean.setUser(userBean);
        userYearAchievementBean.setThreeYearAchievement(user.getThreeYearAchievement());
        return userYearAchievementBean;
    }

    public void deleteUserThreeYearAchievement(Long id) {
        userThreeYearAchievementRepository.delete(id);
    }

    public void editUserThreeYearAchievement(UserAchievementRequestDTO params, Long id) {
        UserThreeYearAchievement savedUserAchievement = userThreeYearAchievementRepository.findOne(id);
        savedUserAchievement.setUpdateDate(new Date());
        String achievementState = params.getState();
        savedUserAchievement.setAchievementState(AchievementState.valueOf(achievementState));
        savedUserAchievement.setUser(userService.getUserById(userService.getAuthenticatedUserId()));
        Integer threeYearId = params.getId();
        savedUserAchievement.setThreeYearAchievement(threeYearAchievementService.getThreeYearAchievementById(threeYearId.longValue()));
        userThreeYearAchievementRepository.saveAndFlush(savedUserAchievement);
    }

    private UserThreeYearAchievementBean buildUserAchievementBean(UserThreeYearAchievement item) {
        UserThreeYearAchievementBean userAchievementBean = new UserThreeYearAchievementBean();
        userAchievementBean.setId(item.getId());
        userAchievementBean.setCreateDate(item.getCreateDate());
        userAchievementBean.setUpdateDate(item.getUpdateDate());
        userAchievementBean.setAchievementState(item.getAchievementState());
        UserAchievementBean userBean = UserService.buildUserAchievementBean(item.getUser());
        userAchievementBean.setUser(userBean);
        userAchievementBean.setThreeYearAchievement(item.getThreeYearAchievement());
        return userAchievementBean;
    }

}
