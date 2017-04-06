package com.royalrangers.bean.achievement;

import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.Achievement;
import com.royalrangers.model.achievement.QuarterAchievement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuarterAchievementBean {
    private Long id;
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private String userAvatarUrl;
    private Long userPlatoonId;
    private Long quarterAchievementId;
    private String quarterAchievementName;
    private String quarterAchievementDescription;
    private String quarterAchievementLogoUrl;
    private AchievementState achievementState;
}
