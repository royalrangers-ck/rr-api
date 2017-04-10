package com.royalrangers.dto.achievement;

import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.enums.achivement.TestType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserTestResponseDto {
    private Long id;
    private Date createDate;
    private Date updateDate;
    private AchievementState achievementState;
    private UserAchievementDto user;
    private String testName;
    private String testDescription;
    private TestType testType;
}
