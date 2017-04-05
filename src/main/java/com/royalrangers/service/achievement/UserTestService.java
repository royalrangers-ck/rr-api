package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.UserAchievementBean;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.UserTest;
import com.royalrangers.dto.achievement.UserTestBean;
import com.royalrangers.repository.UserRepository;
import com.royalrangers.repository.achievement.UserTestRepository;
import com.royalrangers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserTestService {

    @Autowired
    private UserTestRepository userTestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TestService testService;

    public List<UserTestBean> findAllForUser() {
        List<UserTest> list = userTestRepository.findByUserId(userService.getAuthenticatedUserId());
        List<UserTestBean> result = new ArrayList<>();
        for (UserTest item : list) {
            result.add(buildUserAchievementBean(item));
        }
        return result;
    }

    public void addUserTest(Map<String, Object> params) {
        UserTest savedUserAchievement = new UserTest();
        savedUserAchievement.setCreateDate(new Date());
        savedUserAchievement.setUpdateDate(new Date());
        String achievementState = (String) params.get("state");
        savedUserAchievement.setAchievementState(AchievementState.valueOf(achievementState));
        savedUserAchievement.setUser(userService.getUserById(userService.getAuthenticatedUserId()));
        Integer testId = (Integer) params.get("testId");
        savedUserAchievement.setTest(testService.getTestById(testId.longValue()));
        userTestRepository.saveAndFlush(savedUserAchievement);
    }

    public UserTestBean getUserTestById(Long id) {
        UserTest user = userTestRepository.findOne(id);
        return buildUserAchievementBean(user);
    }

    public void deleteUserTest(Long id) {
        userTestRepository.delete(id);
    }

    public void editUserTest(Map<String, Object> params, Long id) {
        UserTest savedUserAchievement = userTestRepository.findOne(id);
        savedUserAchievement.setUpdateDate(new Date());
        String achievementState = (String) params.get("state");
        savedUserAchievement.setAchievementState(AchievementState.valueOf(achievementState));
        Integer testId = (Integer) params.get("testId");
        savedUserAchievement.setTest(testService.getTestById(testId.longValue()));
        userTestRepository.saveAndFlush(savedUserAchievement);
    }

    private UserTestBean buildUserAchievementBean(UserTest item) {
        UserTestBean userAchievementBean = new UserTestBean();
        userAchievementBean.setId(item.getId());
        userAchievementBean.setCreateDate(item.getCreateDate());
        userAchievementBean.setUpdateDate(item.getUpdateDate());
        userAchievementBean.setAchievementState(item.getAchievementState());
        UserAchievementBean userBean = UserService.buildUserAchievementBean(item.getUser());
        userAchievementBean.setUser(userBean);
        userAchievementBean.setTest(item.getTest());
        return userAchievementBean;
    }

}
