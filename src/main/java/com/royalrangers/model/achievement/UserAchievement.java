package com.royalrangers.model.achievement;

import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.BaseModel;
import com.royalrangers.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class UserAchievement extends BaseModel{

    private AchievementState achievementState;

    private User user;

}
