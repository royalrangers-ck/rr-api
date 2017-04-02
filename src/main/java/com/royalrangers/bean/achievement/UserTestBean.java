package com.royalrangers.bean.achievement;

import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.achievement.Test;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserTestBean {
    private Long id;
    private Date createDate;
    private Date updateDate;
    private AchievementState achievementState;
    private UserAchievementBean user;
    private Test test;
}
