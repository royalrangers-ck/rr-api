package com.royalrangers.bean.achievement;

import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.QuarterAchievement;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserQuarterAchievementBean {
    private Long id;
    private Date createDate;
    private Date updateDate;
    private AchievementState achievementState;
    private UserAchievementBean user;
    private QuarterAchievement quarterAchievement;
}