package com.royalrangers.bean.achievement;

import com.royalrangers.model.achievement.Reward;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserRewardBean {
    private Long id;
    private Date createDate;
    private Date updateDate;
    private UserAchievementBean user;
    private Reward reward;
}
