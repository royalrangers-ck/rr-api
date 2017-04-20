package com.royalrangers.model.achievement;

import com.fasterxml.jackson.annotation.JsonView;
import com.royalrangers.model.Views;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class UserYearAchievement extends UserAchievement {

    @JsonView(Views.AchievementProfile.class)
    @OneToOne
    private YearAchievement yearAchievement;

}
