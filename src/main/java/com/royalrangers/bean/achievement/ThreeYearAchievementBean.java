package com.royalrangers.bean.achievement;

import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.ThreeYearAchievement;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ThreeYearAchievementBean {
    private Long id;
    private AchievementState achievementState;
    private AchievementBean user;
    private ThreeYearAchievement threeYearAchievement;
}
