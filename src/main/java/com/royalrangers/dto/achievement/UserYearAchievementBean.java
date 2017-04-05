package com.royalrangers.dto.achievement;

import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.YearAchievement;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserYearAchievementBean{
    private Long id;
    private Date createDate;
    private Date updateDate;
    private AchievementState achievementState;
    private UserAchievementBean user;
    private YearAchievement yearAchievement;
}
