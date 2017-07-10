package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.UserAchievementRequestDto;
import com.royalrangers.dto.achievement.UserQuarterAchievementDto;
import com.royalrangers.dto.achievement.UserTestRequestDto;
import com.royalrangers.enums.UserAgeGroup;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.User;
import com.royalrangers.model.achievement.QuarterAchievement;
import com.royalrangers.model.achievement.Test;
import com.royalrangers.model.achievement.UserQuarterAchievement;
import com.royalrangers.model.achievement.UserTest;
import com.royalrangers.repository.achievement.UserQuarterAchievementRepository;
import com.royalrangers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
public class UserQuarterAchievementService {

    @Autowired
    private UserQuarterAchievementRepository userQuarterAchievementRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TestService testService;

    @Autowired
    private UserTestService userTestService;

    @Autowired
    private QuarterAchievementService quarterAchievementService;

    public List<UserQuarterAchievement> findAllForUser() {
        return userQuarterAchievementRepository.findByUserId(userService.getAuthenticatedUserId());
    }

    public void addUserQuarterAchievement(QuarterAchievement quarterAchievement, User user) {
        UserQuarterAchievement savedUserAchievement = new UserQuarterAchievement();
        savedUserAchievement.setUserAgeGroup(quarterAchievement.getUserAgeGroup());
        savedUserAchievement.setAchievementState(AchievementState.NOT_STARTED);
        savedUserAchievement.setUser(user);
        savedUserAchievement.setQuarterAchievement(quarterAchievement);
        savedUserAchievement.setUserAgeGroup(quarterAchievement.getUserAgeGroup());
        userQuarterAchievementRepository.saveAndFlush(savedUserAchievement);
    }

    public UserQuarterAchievement getUserQuarterAchievementById(Long id) {
        return userQuarterAchievementRepository.findOne(id);
    }

    public List<UserQuarterAchievement> getUserQuarterAchievementByAchievementId(Long achievementId) {
        return userQuarterAchievementRepository.findAllByQuarterAchievement(achievementId);
    }

    public void deleteUserQuarterAchievement(Long id) {
        userQuarterAchievementRepository.delete(id);
    }

    public UserQuarterAchievement editUserQuarterAchievement(UserAchievementRequestDto params, Long id) {
        UserQuarterAchievement savedUserAchievement = userQuarterAchievementRepository.findOne(id);
        savedUserAchievement.setUpdateDate(new Date());
        String achievementState = params.getState();
        savedUserAchievement.setAchievementState(AchievementState.valueOf(achievementState));
        Integer quarterId = params.getId();
        savedUserAchievement.setQuarterAchievement(quarterAchievementService.getQuarterAchievementById(quarterId.longValue()));
        return userQuarterAchievementRepository.saveAndFlush(savedUserAchievement);
    }

    public void addQuarterForSectionUsers(UserQuarterAchievementDto userQuarterAchievementDto) {
        List<User> usersFromSection = userService.getUsersBySectionId(userQuarterAchievementDto.getSectionId());
        UserQuarterAchievement userQuarter;
        if (usersFromSection.size() != 0) {
            Stream.of(usersFromSection.toArray()).forEach(userElement -> {
                User user = (User) userElement;
                UserQuarterAchievement userQuarterAchievement = checkUserQuarter(user, userQuarterAchievementDto.getQuarterId());
                Stream.of(userQuarterAchievementDto.getTestIds().toArray()).forEach(testId -> {
                    Test test = testService.getTestById((Long) testId);
                    checkUserTest(test, user, userQuarterAchievement);
                });
            });
        }
    }

    private UserQuarterAchievement checkUserQuarter(User user, Long quarterId){
        UserQuarterAchievement userQuarterAchievement = userQuarterAchievementRepository.findByUserIdAndQuarterAchievementId(user.getId(), quarterId);
        if (userQuarterAchievement == null){
            addUserQuarterAchievement(quarterAchievementService.getQuarterAchievementById(quarterId), user);
        }
        return userQuarterAchievement;
    }

    private void checkUserTest(Test test, User user, UserQuarterAchievement userQuarterAchievement){
        UserTest userTest = userTestService.getUserTestByUserIdAndTestId(user.getId(), test.getId());
        if (userTest != null){
            userTest.setUserQuarterAchievement(userQuarterAchievement);
        } else {
            UserTestRequestDto userTestRequestDto = new UserTestRequestDto();
            userTestRequestDto.setTestId(test.getId());
            userTestRequestDto.setUserId(user.getId());
            userTestService.addUserTest(userTestRequestDto);
        }
    }

}
