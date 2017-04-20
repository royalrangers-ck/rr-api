package com.royalrangers.model.achievement;

import com.fasterxml.jackson.annotation.JsonView;
import com.royalrangers.enums.achivement.RewardMark;
import com.royalrangers.enums.achivement.RewardType;
import com.royalrangers.model.Views;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
public class Reward extends Achievement {

    @JsonView(Views.AchievementProfile.class)
    @Enumerated
    private RewardMark rewardMark;

    @JsonView(Views.AchievementProfile.class)
    @Enumerated
    private RewardType rewardType;

}
