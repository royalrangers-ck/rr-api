package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.UserAchievementRequestDto;
import com.royalrangers.dto.achievement.UserTestRequestDto;
import com.royalrangers.dto.achievement.UserTestResponseDto;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.UserTest;
import com.royalrangers.repository.achievement.UserTestRepository;
import com.royalrangers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserTestService {

    @Autowired
    private UserTestRepository userTestRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TestService testService;

    public List<UserTestResponseDto> findAllForUser() {
        List<UserTest> list = userTestRepository.findByUserId(userService.getAuthenticatedUserId());
        List<UserTestResponseDto> result = new ArrayList<>();
        for (UserTest item : list) {
            result.add(buildUserAchievementBean(item));
        }
        return result;
    }

    public List<UserTestResponseDto> findAllByPlatoon(Long id) {
        List<UserTest> list = userTestRepository.findByUser_PlatoonId(id);
        List<UserTestResponseDto> result = new ArrayList<>();
        for (UserTest item : list) {
            result.add(buildUserAchievementBean(item));
        }
        return result;
    }

        public void addUserTest(UserTestRequestDto params) {
        UserTest savedUserAchievement = new UserTest();
        savedUserAchievement.setAchievementState(AchievementState.IN_PROGRESS);
        savedUserAchievement.setUser(userService.getUserById(userService.getAuthenticatedUserId()));
        Integer testId = params.getTestId();
        savedUserAchievement.setTest(testService.getTestById(testId.longValue()));
        userTestRepository.saveAndFlush(savedUserAchievement);
    }

    public UserTestResponseDto getUserTestById(Long id) {
        UserTest user = userTestRepository.findOne(id);
        return buildUserAchievementBean(user);
    }

    public List<UserTest> getUserTestsByTestId(Long testId) {
        List<UserTest> resultList = userTestRepository.findAllByTest(testId);
        return resultList;
    }

    public void deleteUserTest(Long id) {
        userTestRepository.delete(id);
    }

    public void editUserTest(UserAchievementRequestDto params, Long id) {
        UserTest savedUserAchievement = userTestRepository.findOne(id);
        savedUserAchievement.setUpdateDate(new Date());
        String achievementState = params.getState();
        savedUserAchievement.setAchievementState(AchievementState.valueOf(achievementState));
        Integer testId = params.getId();
        savedUserAchievement.setTest(testService.getTestById(testId.longValue()));
        userTestRepository.saveAndFlush(savedUserAchievement);
    }

    private UserTestResponseDto buildUserAchievementBean(UserTest item) {
        UserTestResponseDto userAchievementBean = new UserTestResponseDto();
        userAchievementBean.setId(item.getId());
        userAchievementBean.setCreateDate(item.getCreateDate());
        userAchievementBean.setUpdateDate(item.getUpdateDate());
        userAchievementBean.setAchievementState(item.getAchievementState());
        userAchievementBean.setUser(UserService.buildUserAchievementBean(item.getUser()));
        userAchievementBean.setTestName(item.getTest().getName());
        userAchievementBean.setTestDescription(item.getTest().getDescription());
        userAchievementBean.setTestType(item.getTest().getTestType());
        return userAchievementBean;
    }

}
