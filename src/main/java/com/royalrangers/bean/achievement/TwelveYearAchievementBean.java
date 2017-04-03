package com.royalrangers.bean.achievement;

import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.TwelveYearAchievement;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TwelveYearAchievementBean {
    private Long id;
    private AchievementState achievementState;
    private AchievementBean user;
    private TwelveYearAchievement twelveYearAchievement;
}
