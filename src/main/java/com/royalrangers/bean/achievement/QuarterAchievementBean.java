package com.royalrangers.bean.achievement;

import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.QuarterAchievement;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class QuarterAchievementBean {
    private Long id;
    private AchievementState achievementState;
    private AchievementBean user;
    private QuarterAchievement quarterAchievement;
}
