package com.royalrangers.bean.achievement;

import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.TwelveYearAchievement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TwelveYearAchievementBean {
    private Long id;
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private String userAvatarUrl;
    private Long userPlatoonId;
    private AchievementState achievementState;
    private Long twelveYearAchievementId;
    private String twelveYearAchievementName;
    private String twelveYearAchievementDescription;
    private String twelveYearAchievementLogoUrl;
}
