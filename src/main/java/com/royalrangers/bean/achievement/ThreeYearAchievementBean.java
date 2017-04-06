package com.royalrangers.bean.achievement;

import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.ThreeYearAchievement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreeYearAchievementBean {
    private Long id;
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private String userAvatarUrl;
    private Long userPlatoonId;
    private AchievementState achievementState;
    private Long threeYearAchievementId;
    private String threeYearAchievementName;
    private String threeYearAchievementDescription;
    private String threeYearAchievementLogoUrl;
}
