package com.royalrangers.bean.achievement;

import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.YearAchievement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YearAchievementBean {
    private Long id;
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private String userAvatarUrl;
    private Long userPlatoonId;
    private AchievementState achievementState;
    private Long yearAchievementId;
    private String yearAchievementName;
    private String yearAchievementDescription;
    private String yearAchievementLogoUrl;
}
