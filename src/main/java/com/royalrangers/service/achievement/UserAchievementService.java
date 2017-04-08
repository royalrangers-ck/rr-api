package com.royalrangers.service.achievement;

import com.royalrangers.bean.UserBean;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.User;
import com.royalrangers.model.achievement.UserAchievement;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.royalrangers.service.UserService.buildUserBean;

@Service
public class UserAchievementService {

    public static List<UserBean> getUsersFromUserAchievements(List<? extends UserAchievement> userAchievementList, Long platoonId, String achievementState){
        List<UserBean> resultList = new ArrayList<>();
        userAchievementList.stream()
                .filter(element ->
                        element.getAchievementState().equals(AchievementState.valueOf(achievementState)))
                .forEach(element -> {
                    User user = element.getUser();
                    if (user.getPlatoon().getId().equals(platoonId)){
                        UserBean userBean = buildUserBean(user);
                        resultList.add(userBean);
                    }
                });
        return resultList;
    }
}
