package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.UserAchievementRequestDto;
import com.royalrangers.dto.achievement.UserTestRequestDto;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.Task;
import com.royalrangers.model.achievement.UserTest;
import com.royalrangers.repository.achievement.UserTestRepository;
import com.royalrangers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private UserTaskService userTaskService;

    public List<UserTest> findAllForUser() {
        return userTestRepository.findByUserId(userService.getAuthenticatedUserId());
    }

    public void addUserTest(UserTestRequestDto params) {
        UserTest savedUserAchievement = new UserTest();
        savedUserAchievement.setAchievementState(AchievementState.IN_PROGRESS);
        savedUserAchievement.setUser(userService.getUserById(userService.getAuthenticatedUserId()));
        Integer testId = params.getTestId();
        savedUserAchievement.setTest(testService.getTestById(testId.longValue()));
            List<Task> tasks = testService.getTestById(testId.longValue()).getTaskList();
            tasks.forEach(task -> userTaskService.addTaskForUser(task));
        userTestRepository.saveAndFlush(savedUserAchievement);
    }

    public UserTest getUserTestById(Long id) {
        return userTestRepository.findOne(id);
    }

    public List<UserTest> getSubmittedUsersTestsByPlatoon() {
        return userTestRepository.findByUserPlatoonIdAndAchievementState(userService.getAuthenticatedUser().getPlatoon().getId(), AchievementState.SUBMITTED);
    }

    public List<UserTest> getUserTestsByTestId(Long testId) {
        return userTestRepository.findAllByTest(testId);
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
}
