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
public class UserQuarterAchievement extends UserAchievement {

    @JsonView(Views.Achievement.class)
    @OneToOne
    private QuarterAchievement quarterAchievement;

}
