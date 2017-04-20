package com.royalrangers.model.achievement;

import com.fasterxml.jackson.annotation.JsonView;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.BaseModel;
import com.royalrangers.model.User;
import com.royalrangers.model.Views;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@Getter
@Setter
@MappedSuperclass
public abstract class UserAchievement extends BaseModel {

    @JsonView(Views.AchievementProfile.class)
    @Enumerated
    private AchievementState achievementState;

    @OneToOne
    private User user;

}
