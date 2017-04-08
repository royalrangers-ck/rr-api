package com.royalrangers.model.achievement;

import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.model.BaseModel;
import com.royalrangers.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class UserQuarterAchievement extends UserAchievement {

    @Enumerated
    private AchievementState achievementState;

    @OneToOne
    private User user;

    @OneToOne
    private QuarterAchievement quarterAchievement;

}
