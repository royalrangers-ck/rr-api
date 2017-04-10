package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.UserAchievementDto;
import com.royalrangers.dto.achievement.UserAchievementRequestDto;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.UserThreeYearAchievement;
import com.royalrangers.dto.achievement.UserThreeYearResponseDto;
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

    public List<UserThreeYearResponseDto> findAllForUser() {
        List<UserThreeYearAchievement> list = userThreeYearAchievementRepository.findByUserId(userService.getAuthenticatedUserId());
        List<UserThreeYearResponseDto> result = new ArrayList<>();
        for (UserThreeYearAchievement item : list) {
            result.add(buildUserAchievementBean(item));
        }
        return result;
    }

    public void addUserThreeYearAchievement(UserAchievementRequestDto params) {
        UserThreeYearAchievement savedUserAchievement = new UserThreeYearAchievement();
        String achievementState = params.getState();
        savedUserAchievement.setAchievementState(AchievementState.valueOf(achievementState));
        savedUserAchievement.setUser(userService.getUserById(userService.getAuthenticatedUserId()));
        Integer threeYearId = params.getId();
        savedUserAchievement.setThreeYearAchievement(threeYearAchievementService.getThreeYearAchievementById(threeYearId.longValue()));
        userThreeYearAchievementRepository.saveAndFlush(savedUserAchievement);
    }

    public UserThreeYearResponseDto getUserThreeYearAchievementById(Long id) {
        UserThreeYearAchievement user = userThreeYearAchievementRepository.findOne(id);
        return buildUserAchievementBean(user);
    }

    public List<UserThreeYearAchievement> getUserThreeYearAchievementByAchievementId(Long achievementId) {
        List<UserThreeYearAchievement> resultList =
                userThreeYearAchievementRepository.findAllByThreeYearAchievement(achievementId);
        return resultList;
    }

    public void deleteUserThreeYearAchievement(Long id) {
        userThreeYearAchievementRepository.delete(id);
    }

    public void editUserThreeYearAchievement(UserAchievementRequestDto params, Long id) {
        UserThreeYearAchievement savedUserAchievement = userThreeYearAchievementRepository.findOne(id);
        savedUserAchievement.setUpdateDate(new Date());
        String achievementState = params.getState();
        savedUserAchievement.setAchievementState(AchievementState.valueOf(achievementState));
        savedUserAchievement.setUser(userService.getUserById(userService.getAuthenticatedUserId()));
        Integer threeYearId = params.getId();
        savedUserAchievement.setThreeYearAchievement(threeYearAchievementService.getThreeYearAchievementById(threeYearId.longValue()));
        userThreeYearAchievementRepository.saveAndFlush(savedUserAchievement);
    }

    private UserThreeYearResponseDto buildUserAchievementBean(UserThreeYearAchievement item) {
        UserThreeYearResponseDto userAchievementBean = new UserThreeYearResponseDto();
        userAchievementBean.setId(item.getId());
        userAchievementBean.setCreateDate(item.getCreateDate());
        userAchievementBean.setUpdateDate(item.getUpdateDate());
        userAchievementBean.setAchievementState(item.getAchievementState());
        UserAchievementDto userBean = UserService.buildUserAchievementBean(item.getUser());
        userAchievementBean.setUser(userBean);
        userAchievementBean.setThreeYearAchievementName(item.getThreeYearAchievement().getName());
        userAchievementBean.setThreeYearAchievementDescription(item.getThreeYearAchievement().getDescription());
        return userAchievementBean;
    }

}
