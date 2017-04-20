package com.royalrangers.model.achievement;

import com.fasterxml.jackson.annotation.JsonView;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.BaseModel;
import com.royalrangers.model.User;
import com.royalrangers.model.Views;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class UserTwelveYearAchievement extends UserAchievement {

    @JsonView(Views.AchievementProfile.class)
    @OneToOne
    private TwelveYearAchievement twelveYearAchievement;

}
