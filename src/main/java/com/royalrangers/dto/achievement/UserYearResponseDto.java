package com.royalrangers.dto.achievement;

import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.YearAchievement;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserYearResponseDto {
    private Long id;
    private Date createDate;
    private Date updateDate;
    private AchievementState achievementState;
    private UserAchievementDto user;
    private String yearAchievementName;
    private String yearAchievementDescription;
    private String yearAchievementLogoUrl;
}
