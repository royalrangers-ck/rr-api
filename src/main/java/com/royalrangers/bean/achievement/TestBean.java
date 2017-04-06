package com.royalrangers.bean.achievement;

import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.enums.achivement.TestType;
import com.royalrangers.model.achievement.Test;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestBean {
    private Long id;
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private String userAvatarUrl;
    private Long userPlatoonId;
    private AchievementState achievementState;
    private Long testId;
    private String testName;
    private String testDescription;
    private String testLogoUrl;
    private TestType testType;
}
