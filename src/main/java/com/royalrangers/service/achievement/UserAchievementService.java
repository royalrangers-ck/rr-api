package com.royalrangers.service.achievement;

import com.royalrangers.dto.user.UserProfileDto;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.User;
import com.royalrangers.model.achievement.UserAchievement;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.royalrangers.service.UserService.buildUserProfile;

@Service
public class UserAchievementService {

    public static List<UserProfileDto> getUsersFromUserAchievements(List<? extends UserAchievement> userAchievementList, Long platoonId, String achievementState){
        List<UserProfileDto> resultList = new ArrayList<>();
        userAchievementList.stream()
                .filter(element ->
                        element.getAchievementState().equals(AchievementState.valueOf(achievementState)))
                .forEach(element -> {
                    User user = element.getUser();
                    if (user.getPlatoon().getId().equals(platoonId)){
                        UserProfileDto userProfile = buildUserProfile(user);
                        resultList.add(userProfile);
                    }
                });
        return resultList;
    }
}
