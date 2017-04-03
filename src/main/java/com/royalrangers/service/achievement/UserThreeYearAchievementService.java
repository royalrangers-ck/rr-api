package com.royalrangers.service.achievement;

import com.royalrangers.bean.achievement.AchievementBean;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.UserThreeYearAchievement;
import com.royalrangers.bean.achievement.ThreeYearAchievementBean;
import com.royalrangers.repository.UserRepository;
import com.royalrangers.repository.achievement.UserThreeYearAchievementRepository;
import com.royalrangers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    public List<ThreeYearAchievementBean> findAllForUser() {
        List<UserThreeYearAchievement> list = userThreeYearAchievementRepository.findByUserId(userService.getAuthenticatedUserId());
        List<ThreeYearAchievementBean> result = new ArrayList<>();
        for (UserThreeYearAchievement item : list) {
            result.add(buildUserAchievementBean(item));
        }
        return result;
    }

    public void addUserThreeYearAchievement(Map<String, Object> params) {
        UserThreeYearAchievement savedUserAchievement = new UserThreeYearAchievement();
        String achievementState = (String) params.get("state");
        savedUserAchievement.setAchievementState(AchievementState.valueOf(achievementState));
        savedUserAchievement.setUser(userService.getUserById(userService.getAuthenticatedUserId()));
        Integer threeYearId = (Integer) params.get("threeYearAchievementId");
        savedUserAchievement.setThreeYearAchievement(threeYearAchievementService.getThreeYearAchievementById(threeYearId.longValue()));
        userThreeYearAchievementRepository.saveAndFlush(savedUserAchievement);
    }

    public ThreeYearAchievementBean getUserThreeYearAchievementById(Long id) {
        UserThreeYearAchievement user = userThreeYearAchievementRepository.findOne(id);
        return buildUserAchievementBean(user);
    }

    public void deleteUserThreeYearAchievement(Long id) {
        userThreeYearAchievementRepository.delete(id);
    }

    public void editUserThreeYearAchievement(Map<String, Object> params, Long id) {
        UserThreeYearAchievement savedUserAchievement = userThreeYearAchievementRepository.findOne(id);
        savedUserAchievement.setUpdateDate(new Date());
        String achievementState = (String) params.get("state");
        savedUserAchievement.setAchievementState(AchievementState.valueOf(achievementState));
        savedUserAchievement.setUser(userService.getUserById(userService.getAuthenticatedUserId()));
        Integer threeYearId = (Integer) params.get("threeYearAchievementId");
        savedUserAchievement.setThreeYearAchievement(threeYearAchievementService.getThreeYearAchievementById(threeYearId.longValue()));
        userThreeYearAchievementRepository.saveAndFlush(savedUserAchievement);
    }

    private ThreeYearAchievementBean buildUserAchievementBean(UserThreeYearAchievement item) {
        ThreeYearAchievementBean userAchievementBean = new ThreeYearAchievementBean();
        userAchievementBean.setId(item.getId());
        userAchievementBean.setAchievementState(item.getAchievementState());
        AchievementBean userBean = UserService.buildUserAchievementBean(item.getUser());
        userAchievementBean.setUser(userBean);
        userAchievementBean.setThreeYearAchievement(item.getThreeYearAchievement());
        return userAchievementBean;
    }

}
