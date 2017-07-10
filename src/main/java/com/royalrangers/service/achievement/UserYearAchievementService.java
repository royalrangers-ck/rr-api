package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.UserAchievementRequestDto;
import com.royalrangers.dto.achievement.UserTestRequestDto;
import com.royalrangers.dto.achievement.UserYearAchievementDto;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.User;
import com.royalrangers.model.achievement.*;
import com.royalrangers.repository.achievement.UserYearAchievementRepository;
import com.royalrangers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
public class UserYearAchievementService {

    @Autowired
    private UserYearAchievementRepository userYearAchievementRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserTestService userTestService;

    @Autowired
    private TestService testService;

    @Autowired
    private YearAchievementService yearAchievementService;

    public List<UserYearAchievement> findAllForUser() {
        return userYearAchievementRepository.findByUserId(userService.getAuthenticatedUserId());
    }

    public UserYearAchievement addUserYearAchievement(YearAchievement yearAchievement, User user) {
        UserYearAchievement savedUserAchievement = new UserYearAchievement();
        savedUserAchievement.setAchievementState(AchievementState.NOT_STARTED);
        savedUserAchievement.setUser(user);
        savedUserAchievement.setYearAchievement(yearAchievement);
        userYearAchievementRepository.saveAndFlush(savedUserAchievement);
        return savedUserAchievement;
    }

    public UserYearAchievement getUserYearAchievementById(Long id) {
        return userYearAchievementRepository.findOne(id);
    }

    public List<UserYearAchievement> getUserYearAchievementByAchievementId(Long achievementId) {
        return userYearAchievementRepository.findAllByYearAchievement(achievementId);
    }

    public void deleteUserYearAchievement(Long id) {
        userYearAchievementRepository.delete(id);
    }

    public UserYearAchievement editUserYearAchievement(UserAchievementRequestDto params, Long id) {
        UserYearAchievement savedUserAchievement = userYearAchievementRepository.findOne(id);
        savedUserAchievement.setUpdateDate(new Date());
        savedUserAchievement.setAchievementState(AchievementState.valueOf(params.getState()));
        Integer yearId = params.getId();
        savedUserAchievement.setYearAchievement(yearAchievementService.getYearAchievementById(yearId.longValue()));
        return userYearAchievementRepository.saveAndFlush(savedUserAchievement);
    }

    public void addYearForSectionUsers(UserYearAchievementDto userYearAchievementDto) {
        List<User> usersFromSection = userService.getUsersBySectionId(userYearAchievementDto.getSectionId());
        if (usersFromSection.size() != 0) {
            Stream.of(usersFromSection.toArray()).forEach(userElement -> {
                User user = (User) userElement;
                UserYearAchievement userYearAchievement = checkUserYear(user, userYearAchievementDto.getYearId());
                Stream.of(userYearAchievementDto.getAdditionalTestIds().toArray()).forEach(testId -> {
                    Test test = testService.getTestById((Long) testId);
                    checkUserTest(test, user, userYearAchievement);
                });
            });
        }
    }

    private UserYearAchievement checkUserYear(User user, Long yearId) {
        UserYearAchievement userQuarterAchievement = userYearAchievementRepository.findByUserIdAndYearAchievementId(user.getId(), yearId);
        if (userQuarterAchievement == null) {
            addUserYearAchievement(yearAchievementService.getYearAchievementById(yearId), user);
        }
        return userQuarterAchievement;
    }

    private void checkUserTest(Test test, User user, UserYearAchievement userYearAchievement) {
        UserTest userTest = userTestService.getUserTestByUserIdAndTestId(user.getId(), test.getId());
        if (userTest != null) {
            userTest.setUserYearAchievement(userYearAchievement);
        } else {
            UserTestRequestDto userTestRequestDto = new UserTestRequestDto();
            userTestRequestDto.setTestId(test.getId());
            userTestRequestDto.setUserId(user.getId());
            UserTest savedUserTest = userTestService.addUserTest(userTestRequestDto);
            savedUserTest.setUserYearAchievement(userYearAchievement);
        }
    }

}
