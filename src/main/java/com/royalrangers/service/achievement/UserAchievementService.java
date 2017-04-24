package com.royalrangers.service.achievement;

import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.User;
import com.royalrangers.model.achievement.UserAchievement;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAchievementService {

    public static List<User> getUsersFromUserAchievements(List<? extends UserAchievement> userAchievementList, Long platoonId, String achievementState){
        List<User> resultList = new ArrayList<>();
        userAchievementList.stream()
                .filter(element ->
                        element.getAchievementState().equals(AchievementState.valueOf(achievementState)))
                .forEach(element -> {
                    User user = element.getUser();
                    if (user.getPlatoon().getId().equals(platoonId)){
                        resultList.add(user);
                    }
                });
        return resultList;
    }
}
