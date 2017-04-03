package com.royalrangers.bean.achievement;

import com.royalrangers.model.achievement.Reward;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RewardBean {
    private Long id;
    private AchievementBean user;
    private Reward reward;
}
