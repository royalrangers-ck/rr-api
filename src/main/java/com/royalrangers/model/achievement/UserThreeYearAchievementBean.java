package com.royalrangers.model.achievement;

import com.royalrangers.bean.UserAchievementBean;
import com.royalrangers.enums.achivement.AchievementStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserThreeYearAchievementBean {
    private Long id;
    private Date createDate;
    private Date updateDate;
    private AchievementStatus achievementStatus;
    private UserAchievementBean user;
    private UserAchievementBean reviewer;
    private ThreeYearAchievement threeYearAchievement;
}