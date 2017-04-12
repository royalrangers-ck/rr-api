package com.royalrangers.dto.achievement;

import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.enums.achivement.AgeCategory;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserThreeYearResponseDto {
    private Long id;
    private Date createDate;
    private Date updateDate;
    private AchievementState achievementState;
    private UserAchievementDto user;
    private String threeYearAchievementName;
    private String threeYearAchievementDescription;
    private String threeYearAchievementLogoUrl;
    private AgeCategory ageCategory;
}
