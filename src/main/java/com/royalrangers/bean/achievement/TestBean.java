package com.royalrangers.bean.achievement;

import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.Test;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TestBean {
    private Long id;
    private AchievementState achievementState;
    private AchievementBean user;
    private Test test;
}
